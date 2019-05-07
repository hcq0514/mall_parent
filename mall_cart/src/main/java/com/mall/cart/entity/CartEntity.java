package com.mall.cart.entity;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class CartEntity {
    @ApiParam("用户id")
    private Long userId;

    @ApiParam("商品id")
    private Long skuId;

    @ApiParam("标题")
    private String title;

    @ApiParam("图片")
    private String image;

    @ApiParam("加入购物车时的价格")
    private Long price;

    @ApiParam("购买数量")
    private Integer num;

    @ApiParam("商品规格参数")
    private String ownSpec;
}
