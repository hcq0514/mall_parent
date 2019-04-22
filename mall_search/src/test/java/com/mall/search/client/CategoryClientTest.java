package com.mall.search.client;

import com.mall.search.SearchApplication;
import com.mall.search.document.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchApplication.class)
public class CategoryClientTest {

    @Autowired
    private CategoryClient categoryClient;

    @Test
    public void testQueryCategories() {
        List<String> names = categoryClient.queryNameByIds(Arrays.asList(1L, 2L, 3L)).getBody();
        names.forEach(System.out::println);
    }
}