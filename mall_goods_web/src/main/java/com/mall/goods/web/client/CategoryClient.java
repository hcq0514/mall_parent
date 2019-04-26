package com.mall.goods.web.client;

import com.mall.item.api.CategoryApi;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient("item-service")
public interface CategoryClient extends CategoryApi {
}