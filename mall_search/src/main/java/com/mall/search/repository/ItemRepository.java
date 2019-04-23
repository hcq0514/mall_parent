package com.mall.search.repository;


import com.mall.search.document.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ItemRepository extends ElasticsearchRepository<Item, Long> {
}
