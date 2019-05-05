package com.mall.item.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.item.entity.SkuEntity;
import com.mall.item.entity.StockEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 * @author hcq
 * @since 2019-04-09
 */
@Mapper
public interface StockMapper extends BaseMapper<StockEntity> {

}
