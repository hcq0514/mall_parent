package com.mall.item.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mall.item.entity.CategoryEntity;
import com.mall.item.service.CategoryService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "商品种类增加")
    @PostMapping
    public ResponseEntity save(CategoryEntity categoryEntity) {
        categoryService.save(categoryEntity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "商品种类名称修改")
    @PutMapping
    public ResponseEntity updateNameById(@RequestParam("id") Long id, @RequestParam("name") String name) {
        categoryService.updateNameById(id, name);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiOperation(value = "商品种类删除")
    @DeleteMapping("confirm/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        categoryService.removeById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

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
    public ResponseEntity<List<String>> queryNameByIds(@RequestParam("ids") List<Long> ids) {
        List<String> list = categoryService.queryNameByIds(ids);
        if (list == null || list.size() < 1) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);
    }


    @GetMapping("bid/{brandId}")
    @ApiOperation(value = "查询商品类别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "brandId", value = "品牌id"),
    })
    public ResponseEntity<List<CategoryEntity>> queryCategoriesByBrandId(@PathVariable("brandId") Long brandId) {
        List<CategoryEntity> list = categoryService.queryByBrandId(brandId);
        if (list == null || list.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(list);
    }

//    @GetMapping("bid/{bid}")
//    public ResponseEntity<List<Category>> queryByBrandId(@PathVariable("bid") Long bid){
//        List<Category> list = this.categoryService.queryByBrandId(bid);
//        if(list == null || list.size() < 1){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        return ResponseEntity.ok(list);
//    }
}
