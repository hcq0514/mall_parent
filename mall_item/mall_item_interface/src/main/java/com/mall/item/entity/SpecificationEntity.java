package com.mall.item.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import static com.baomidou.mybatisplus.annotation.IdType.NONE;

/**
 *
 * @author hcq
 * @since 2019-04-12
 */

@Data
@TableName("mall_specification")
public class SpecificationEntity {
    @TableId(type = NONE)
    @ApiParam("类别ID")
    private Long categoryId;
    private String specifications;
}


