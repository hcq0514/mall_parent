package com.mall.cart.client;

import com.mall.item.api.CategoryApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "item-service")
public interface CategoryClient extends CategoryApi {
}