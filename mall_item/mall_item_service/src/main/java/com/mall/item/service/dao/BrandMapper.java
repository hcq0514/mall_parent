package com.mall.item.service.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.item.entity.BrandEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author hcq
 * @since 2019-04-09
 */
@Mapper
public interface BrandMapper extends BaseMapper<BrandEntity> {

    /**
     * 写入mall_category_brand中间表
     * @param brandId 品牌id
     * @param categoryId 类别id
     */
    @Insert("insert into mall_category_brand values (#{brandId},#{categoryId} )")
    void insertBrandAndCategoryId(@Param("brandId") long brandId, @Param("categoryId") long categoryId);
}
