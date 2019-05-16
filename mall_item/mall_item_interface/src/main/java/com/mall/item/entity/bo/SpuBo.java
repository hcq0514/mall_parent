package com.mall.item.entity.bo;

import com.mall.item.entity.SkuEntity;
import com.mall.item.entity.SpuDetailEntity;
import com.mall.item.entity.SpuEntity;
import lombok.Data;

import java.util.List;

@Data
public class SpuBo extends SpuEntity {

    /**
     *  商品分类名称
     */
    String categoryName;

    /**
     *  品牌名称
     */
    String brandName;

    /**
     * 商品详情
     */
    private SpuDetailEntity spuDetail;

    /**
     * sku列表
     */
    private List<SkuEntity> skus;


}
