package com.mall.search.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.item.entity.SkuEntity;
import com.mall.item.entity.SpuDetailEntity;
import com.mall.item.entity.SpuEntity;
import com.mall.search.client.CategoryClient;
import com.mall.search.client.GoodsClient;
import com.mall.search.document.Goods;
import com.mall.search.dto.PageResult;
import com.mall.search.dto.SearchRequest;
import com.mall.search.repository.GoodsRepository;
import com.mall.search.service.SearchService;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * @Author: 98050
 * Time: 2018-10-11 22:59
 * Feature: 搜索功能
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private CategoryClient categoryClient;

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * 查询商品信息
     * @param spu
     * @return
     * @throws IOException
     */
    @Override
    public Goods buildGoods(SpuEntity spu) throws IOException {
        Goods goods = new Goods();

        //1.查询商品分类名称
        List<String> categoryNames = this.categoryClient.queryNameByIds(Arrays.asList(spu.getCid1(),spu.getCid2(),spu.getCid3())).getBody();
        //2.查询sku
        List<SkuEntity> skus = this.goodsClient.querySkuBySpuId(spu.getId());
        //3.查询详情
        SpuDetailEntity spuDetail = this.goodsClient.querySpuDetailBySpuId(spu.getId());

        //4.处理sku,仅封装id，价格、标题、图片、并获得价格集合
        List<Long> prices = new ArrayList<>();
        List<Map<String,Object>> skuLists = new ArrayList<>();
        skus.forEach(sku -> {
            prices.add(sku.getPrice());
            Map<String,Object> skuMap = new HashMap<>();
            skuMap.put("id",sku.getId());
            skuMap.put("title",sku.getTitle());
            skuMap.put("price",sku.getPrice());
            //取第一张图片
            skuMap.put("image", StringUtils.isBlank(sku.getImages()) ? "" : StringUtils.split(sku.getImages(),",")[0]);
            skuLists.add(skuMap);
        });

        //提取公共属性
        List<Map<String,Object>> genericSpecs = mapper.readValue(spuDetail.getSpecifications(),new TypeReference<List<Map<String,Object>>>(){});
        //提取特有属性
        Map<String,Object> specialSpecs = mapper.readValue(spuDetail.getSpecTemplate(),new TypeReference<Map<String,Object>>(){});

        //过滤规格模板，把所有可搜索的信息保存到Map中
        Map<String,Object> specMap = new HashMap<>();

        String searchable = "searchable";
        String v = "v";
        String k = "k";
        String options = "options";

        genericSpecs.forEach(m -> {
            List<Map<String, Object>> params = (List<Map<String, Object>>) m.get("params");
            params.forEach(spe ->{
                if ((boolean)spe.get(searchable)){
                    if (spe.get(v) != null){
                        specMap.put(spe.get(k).toString(), spe.get(v));
                    }else if (spe.get(options) != null){
                        specMap.put(spe.get(k).toString(), spe.get(options));
                    }
                }
            });
        });
        goods.setId(spu.getId());
        goods.setSubTitle(spu.getSubTitle());
        goods.setBrandId(spu.getBrandId());
        goods.setCid1(spu.getCid1());
        goods.setCid2(spu.getCid2());
        goods.setCid3(spu.getCid3());
        goods.setCreateTime(spu.getCreateTime());
        goods.setAll(spu.getTitle() + " " + StringUtils.join(categoryNames, " "));
        goods.setPrice(prices);
        goods.setSkus(mapper.writeValueAsString(skuLists));
        goods.setSpecs(specMap);
        return goods;
    }

    /**
     * 搜索
     * @param searchRequest
     * @return
     */
    @Override
    public PageResult<Goods> search(SearchRequest searchRequest) {
        String key = searchRequest.getKey();
        // 判断是否有搜索条件，如果没有，直接返回null。不允许搜索全部商品
        if (StringUtils.isBlank(key)) {
            return null;
        }

        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        // 1、对key进行全文检索查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("all", key).operator(Operator.AND));

        // 2、通过sourceFilter设置返回的结果字段,我们只需要id、skus、subTitle
        queryBuilder.withSourceFilter(new FetchSourceFilter(
                new String[]{"id", "skus", "subTitle"}, null));

        // 3、分页
        // 准备分页参数
        int page = searchRequest.getPage();
        int size = searchRequest.getSize();
        queryBuilder.withPageable(PageRequest.of(page - 1, size));
        // 4、排序
        String sortBy = searchRequest.getSortBy();
        Boolean desc = searchRequest.getDescending();
        if (StringUtils.isNotBlank(sortBy)) {
            // 如果不为空，则进行排序
            queryBuilder.withSort(SortBuilders.fieldSort(sortBy).order(desc ? SortOrder.DESC : SortOrder.ASC));
        }

        // 4、查询，获取结果
        Page<Goods> goodsPage = this.goodsRepository.search(queryBuilder.build());
        long l = goodsPage.getTotalPages();

        return new PageResult<Goods>(goodsPage.getTotalElements(), l, goodsPage.getContent());
    }



}
