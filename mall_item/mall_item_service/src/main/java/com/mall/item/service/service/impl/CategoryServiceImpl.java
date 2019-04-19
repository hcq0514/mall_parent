package com.mall.item.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.item.entity.CategoryEntity;
import com.mall.item.service.dao.CategoryMapper;
import com.mall.item.service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author hcq
 * @since 2019-04-09
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryEntity> implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<String> queryNameByIds(List<Long> ids) {
        List<String> names = categoryMapper.selectBatchIds(ids).stream().map(x -> x.getName()).collect(Collectors.toList());
        return names;
    }
}
