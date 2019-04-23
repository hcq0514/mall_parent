package com.mall.search;

import com.mall.search.document.Goods;
import com.mall.search.document.Item;
import com.mall.search.repository.GoodsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchApplication.class)
public class SearchApplicationTest {
    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void createIndex(){

        // 创建索引
        elasticsearchTemplate.createIndex(Item.class);
        //写索引字段
        elasticsearchTemplate.putMapping(Item.class);
        //删索引
//        elasticsearchTemplate.deleteIndex("item");
    }
}
