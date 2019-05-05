package com.mall.item.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mall.item.entity.CategoryEntity;
import com.mall.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

//    @GetMapping("list")
//    public Result list(@RequestParam("pid") long pid) {
//        CategoryEntity categoryEntity = new CategoryEntity();
//        categoryEntity.setParentId(pid);
//        List list = categoryService.list(new QueryWrapper(categoryEntity));
//        return Result.ok(list);
//    }

    @GetMapping("/list")
    public ResponseEntity<List<CategoryEntity>> queryCategoryByPid(@RequestParam("pid") Long pid) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setParentId(pid);
        List<CategoryEntity> list = categoryService.list(new QueryWrapper(categoryEntity));
        return ResponseEntity.ok(list);
    }

    /**
     * 根据商品分类id查询名称
     * @param ids 要查询的分类id集合
     * @return 多个名称的集合
     */
    @GetMapping("names")
    public ResponseEntity<List<String>> queryNameByIds(@RequestParam("ids") List<Long> ids){
        List<String > list = categoryService.queryNameByIds(ids);
        if (list == null || list.size() < 1) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);
    }
}
