package com.mall.search;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.item.entity.bo.SpuBo;
import com.mall.search.client.GoodsClient;
import com.mall.search.document.Goods;
import com.mall.search.repository.GoodsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchApplication.class)
public class GoodsTest {
    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private SearchService searchService;

    @Test
    public void createIndex() {
        // 创建索引
        elasticsearchTemplate.createIndex(Goods.class);
        //写索引字段
        elasticsearchTemplate.putMapping(Goods.class);
    }
    @Test
    public void loadData(){
        // 创建索引
        this.elasticsearchTemplate.createIndex(Goods.class);
        // 配置映射
        this.elasticsearchTemplate.putMapping(Goods.class);
        int page = 1;
        int rows = 100;
        int size = 0;
        do {
            // 查询分页数据
            ResponseEntity<Page<SpuBo>> iPageResponseEntity = goodsClient.querySpuByPage(page, rows, "");
            List<SpuBo> spus = iPageResponseEntity.getBody().getRecords();
            size = spus.size();
            // 创建Goods集合
            List<Goods> goodsList = new ArrayList<>();
            // 遍历spu
            for (SpuBo spu : spus) {
                try {
                    Goods goods =searchService.buildGoods(spu);
                    goodsList.add(goods);
                } catch (Exception e) {
                    break;
                }
            }
            this.goodsRepository.saveAll(goodsList);
            page++;
        } while (size == 100);
    }
}
