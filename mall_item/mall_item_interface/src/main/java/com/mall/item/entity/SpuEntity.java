package com.mall.item.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

/**
 * <p>
 * spu表，该表描述的是一个抽象性的商品，比如 iphone8
 * </p>
 *
 * @author hcq
 * @since 2019-04-12
 */
@Data
@TableName("mall_spu")
public class SpuEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = AUTO)
    @ApiModelProperty(hidden = true)
    private Long id;

    @ApiParam("标题")
    private String title;

    @ApiParam("子标题")
    private String subTitle;

    @ApiParam("1级类目id")
    private Long cid1;

    @ApiParam("2级类目id")
    private Long cid2;

    @ApiParam("3级类目id")
    private Long cid3;

    @ApiParam("商品所属品牌id")
    private Long brandId;

    @ApiParam("是否上架，0下架，1上架")
    private Boolean saleable;

    @ApiParam("是否有效，0已删除，1有效")
    private Boolean valid;

    @ApiParam("添加时间")
    private LocalDateTime createTime;

    @ApiParam("最后修改时间")
    private LocalDateTime lastUpdateTime;


}
