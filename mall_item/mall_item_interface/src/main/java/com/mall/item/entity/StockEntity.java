package com.mall.item.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

/**
 *
 * @author hcq
 * @since 2019-04-15
 */
@Data
@TableName("mall_stock")
public class StockEntity implements Serializable {


    private static final long serialVersionUID = 1L;

    @TableId(type = AUTO)
    private Long id;

    /**
     * 库存对应的商品sku id
     */
    private Long skuId;

    /**
     * 可秒杀库存
     */
    private Integer seckillStock;

    /**
     * 秒杀总数量
     */
    private Integer seckillTotal;

    /**
     * 库存数量
     */
    private Integer stock;

}
