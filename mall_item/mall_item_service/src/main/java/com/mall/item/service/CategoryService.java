package com.mall.item.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.item.entity.CategoryEntity;

import java.util.List;

/**
 *
 * @author hcq
 * @since 2019-04-09
 */
public interface CategoryService extends IService<CategoryEntity> {

    List<String> queryNameByIds(List<Long> ids);

    List<CategoryEntity> queryByBrandId(Long brandId);

    void updateNameById(Long id, String name);
}
