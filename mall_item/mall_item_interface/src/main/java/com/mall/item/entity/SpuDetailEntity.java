package com.mall.item.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hcq
 * @since 2019-04-12
 */
@Data
@TableName("mall_spu_detail")
public class SpuDetailEntity implements Serializable {


    private static final long serialVersionUID = 1L;

    @ApiParam("spuId")
    private Long spuId;

    @ApiParam("商品描述信息")
    private String description;

    @ApiParam("全部规格参数数据")
    private String specifications;

    @ApiParam("特有规格参数及可选值信息，json格式")
    private String specTemplate;

    @ApiParam("包装清单")
    private String packingList;

    @ApiParam("售后服务")
    private String afterService;


}
