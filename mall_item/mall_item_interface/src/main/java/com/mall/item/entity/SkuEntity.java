package com.mall.item.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;


import java.io.Serializable;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

/**
 * <p>
 * sku表,该表表示具体的商品实体,如黑色的 64g的iphone 8
 * </p>
 *
 * @author hcq
 * @since 2019-04-12
 */
@Data
@TableName("mall_sku")
public class SkuEntity implements Serializable {


    private static final long serialVersionUID = 1L;

    @TableId(type = AUTO)
    @ApiModelProperty(hidden = true)
    private Long id;


    @ApiParam("spu id")
    private Long spuId;


    @ApiParam("商品标题")
    private String title;

    @ApiParam("商品的图片，多个图片以‘,’分割")
    private String images;


    @ApiParam("销售价格，单位为分")
    private Long price;

    @ApiParam("特有规格属性在spu属性模板中的对应下标组合")
    private String indexes;

    @ApiParam("sku的特有规格参数键值对，json格式，反序列化时请使用linkedHashMap，保证有序")
    private String ownSpec;

    @ApiParam("是否有效，0无效，1有效")
    private Boolean enable;

    @ApiParam("添加时间")
    private LocalDateTime createTime;

    @ApiParam("最后修改时间")
    private LocalDateTime lastUpdateTime;


    @TableField(exist = false)
    private Integer stock;


}
