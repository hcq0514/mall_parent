package com.mall.item.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.item.entity.CategoryEntity;
import com.mall.item.service.dao.CategoryMapper;
import com.mall.item.service.service.CategoryService;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcq
 * @since 2019-04-09
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryEntity> implements CategoryService {

}
