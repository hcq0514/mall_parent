package com.mall.item.service.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mall.item.entity.CategoryEntity;
import com.mall.item.service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author hcq
 * @since 2019-04-09
 */
@RestController
@RequestMapping("item/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("list/{pid}")
    public List list(@PathVariable long pid){
        CategoryEntity  categoryEntity = new CategoryEntity();
        categoryEntity.setParentId(pid);
        return  categoryService.list(new QueryWrapper(categoryEntity));
    }
}
