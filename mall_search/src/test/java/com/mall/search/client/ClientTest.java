package com.mall.search.client;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.item.entity.bo.SpuBo;
import com.mall.search.SearchApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchApplication.class)
public class ClientTest {

    @Autowired
    private CategoryClient categoryClient;
    @Autowired
    private GoodsClient goodsClient;

    @Test
    public void testQueryCategories() {
        List<String> names = categoryClient.queryNameByIds(Arrays.asList(1L, 2L, 3L)).getBody();
        names.forEach(System.out::println);
    }
    @Test
    public void testGoodClient() {
//        SpuDetailEntity spuDetailEntity = goodsClient.querySpuDetailBySpuId(2l);
//        System.out.println(spuDetailEntity);
        ResponseEntity<Page<SpuBo>> iPageResponseEntity = goodsClient.querySpuByPage(1, 1, "");
        System.out.println(iPageResponseEntity);

    }
}