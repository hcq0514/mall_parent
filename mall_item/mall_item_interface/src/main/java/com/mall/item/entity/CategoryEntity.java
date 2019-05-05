package com.mall.item.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.io.Serializable;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

/**
 * <p>
 * 'mall.cid3' is not BASE TABLE
 * </p>
 *
 * @author hcq
 * @since 2019-04-09
 */
@Data
@TableName("mall_category")
public class CategoryEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 父类目id,顶级类目填0
     */
    @TableId(type = AUTO)
    @ApiModelProperty(hidden = true)
    private Long id;

    @ApiParam("类目名称")
    private String name;

    @ApiParam("父类目id,顶级类目填0")
    private Long parentId;

    @ApiParam("是否为父节点，0为否，1为是")
    private Boolean isParent;

    @ApiParam("排序指数，越小越靠前")
    private Integer sort;


}
