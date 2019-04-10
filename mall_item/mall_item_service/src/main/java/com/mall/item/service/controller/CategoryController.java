package com.mall.item.service.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mall.common.entity.Result;
import com.mall.item.entity.CategoryEntity;
import com.mall.item.service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hcq
 * @since 2019-04-09
 */
@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("list")
    public Result list(@RequestParam("pid") long pid) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setParentId(pid);
        List list = categoryService.list(new QueryWrapper(categoryEntity));
        return Result.ok(list);
    }
}
