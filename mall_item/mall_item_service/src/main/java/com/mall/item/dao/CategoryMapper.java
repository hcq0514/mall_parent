package com.mall.item.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.common.entity.DBPrefix;
import com.mall.item.entity.CategoryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author hcq
 * @since 2019-04-09
 */
@Mapper
public interface CategoryMapper extends BaseMapper<CategoryEntity> {
    String TABLE_NAME = DBPrefix.TABLE_PRE + "category";

    @Select("select * from " + TABLE_NAME + " c " +
            " left join mall_category_brand cb" +
            " on(c.id=cb.category_id)" +
            " where cb.brand_id = #{brandId}")
    List<CategoryEntity> queryByBrandId(Long brandId);

    @Update("update " + TABLE_NAME + " set name = #{name} where id = #{id}")
    void updateNameById(@Param("id") Long id,@Param("name")  String name);
}
