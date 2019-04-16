package com.mall.item.service.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.item.entity.SpuDetailEntity;
import com.mall.item.entity.SpuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author hcq
 * @since 2019-04-09
 */
@Mapper
public interface SpuDetailMapper extends BaseMapper<SpuDetailEntity> {

    @Select("select *  from mall_spu_detail where spu_id  = #{spu_id}")
    SpuDetailEntity selectSpuDetailBySpuId(long spu_id);
}
