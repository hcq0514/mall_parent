package com.mall.item.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

/**
 * @author hcq
 * @since 2019-04-12
 */
@Data
@TableName("mall_spu_detail")
public class SpuDetailEntity implements Serializable {


    private static final long serialVersionUID = 1L;

    private Long spuId;

    /**
     * 商品描述信息
     */
    private String description;

    /**
     * 全部规格参数数据
     */
    private String specifications;

    /**
     * 特有规格参数及可选值信息，json格式
     */
    private String specTemplate;

    /**
     * 包装清单
     */
    private String packingList;

    /**
     * 售后服务
     */
    private String afterService;


}
