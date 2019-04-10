package com.mall.item.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

import static com.baomidou.mybatisplus.annotation.IdType.ID_WORKER_STR;

/**
 *
 * @author hcq
 * @since 2019-04-10
 */
@Data
@TableName("mall_brand")
public class BrandEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 品牌名称
     */
    @TableId(type = ID_WORKER_STR)
    private Long id;

    /**
     * 品牌名称
     */
    private String name;

    /**
     * 品牌图片地址
     */
    private String image;

    /**
     * 品牌的首字母
     */
    private String letter;


}
