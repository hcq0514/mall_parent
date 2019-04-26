package com.mall.goods.web.service;

import com.mall.item.api.BrandApi;
import com.mall.item.api.CategoryApi;
import com.mall.item.api.GoodsApi;
import com.mall.item.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsWebService {

    @Autowired
    private GoodsApi goodsApi;

    @Autowired
    private BrandApi brandApi;

    @Autowired
    private CategoryApi categoryApi;

    private static final Logger logger = LoggerFactory.getLogger(GoodsWebService.class);

    public Map<String, Object> loadModel(long spuId){
        try {
            // 查询spu
            SpuEntity spu = goodsApi.querySpuById(spuId).getBody();

            // 查询spu详情
            SpuDetailEntity spuDetail = goodsApi.querySpuDetailBySpuId(spuId).getBody();

            // 查询sku
            List<SkuEntity> skus = goodsApi.querySkuBySpuId(spuId);

            // 查询品牌
            List<BrandEntity> brands = brandApi.queryBrandByIds(Arrays.asList(spu.getBrandId()));

            // 查询分类
            List<CategoryEntity> categories = getCategories(spu);
//
//            // 查询组内参数
//            List<SpecGroup> specGroups = specificationClient.querySpecsByCid(spu.getCid3());
//
//            // 查询所有特有规格参数
////            List specParams = specificationClient.querySpecParam(null, spu.getCid3(), null, false);
////            // 处理规格参数
            Map<Long, String> paramMap = new HashMap<>();
//            specParams.forEach(param -> {
//////                paramMap.put(param.getId(), param.getName());
////            });
//
            Map<String, Object> map = new HashMap<>();
            map.put("spu", spu);
            map.put("spuDetail", spuDetail);
            map.put("skus", skus);
            map.put("brand", brands.get(0));
            map.put("categories", categories);
//            map.put("groups", specGroups);
            map.put("params", paramMap);
            return map;
        } catch (Exception e) {
            logger.error("加载商品数据出错,spuId:{}", spuId, e);
        }
        return null;
    }

    private List<CategoryEntity> getCategories(SpuEntity spu) {
        try {
            List<String> names = categoryApi.queryNameByIds(
                    Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3())).getBody();

            CategoryEntity c1 = new CategoryEntity();
            c1.setName(names.get(0));
            c1.setId(spu.getCid1());

            CategoryEntity c2 = new CategoryEntity();
            c2.setName(names.get(1));
            c2.setId(spu.getCid2());

            CategoryEntity c3 = new CategoryEntity();
            c3.setName(names.get(2));
            c3.setId(spu.getCid3());
            return Arrays.asList(c1, c2, c3);
        } catch (Exception e) {
            logger.error("查询商品分类出错，spuId：{}", spu.getId(), e);
        }
        return null;
    }

}
