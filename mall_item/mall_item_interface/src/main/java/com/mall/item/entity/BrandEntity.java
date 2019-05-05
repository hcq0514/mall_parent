package com.mall.item.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;

import java.io.Serializable;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

/**
 * @author hcq
 * @since 2019-04-10
 */
@Data
@TableName("mall_brand")
public class BrandEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = AUTO)
    @ApiModelProperty(hidden = true)
    private Long id;


    @ApiParam("品牌名称")
    private String name;

    @ApiParam("品牌图片")
    private String image;

    @ApiParam("品牌的首字母")
    private String letter;


}
