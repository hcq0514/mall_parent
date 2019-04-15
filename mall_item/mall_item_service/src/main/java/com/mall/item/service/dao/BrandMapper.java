package com.mall.item.service.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.item.entity.BrandEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author hcq
 * @since 2019-04-09
 */
@Mapper
public interface BrandMapper extends BaseMapper<BrandEntity> {

    /**
     * 写入mall_category_brand中间表
     *
     * @param brandId    品牌id
     * @param categoryId 类别id
     */
    @Insert("insert into mall_category_brand(category_id,brand_id) values (#{categoryId},#{brandId} )")
    void insertCategoryIdAndBrandId(@Param("categoryId") long categoryId, @Param("brandId") long brandId);

    /**
     *  根据category_id查询所有对应的brand
     * @param cid 类别id
     * @return
     */
    @Select("SELECT b.* from mall_brand b " +
            "INNER JOIN mall_category_brand cb ON(b.id = cb.brand_id) " +
            "where cb.category_id = #{cid}")
    List<BrandEntity> selectBrandByCategoryId(long cid);
}
