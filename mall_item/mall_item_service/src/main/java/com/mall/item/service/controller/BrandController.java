package com.mall.item.service.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.item.entity.BrandEntity;
import com.mall.item.service.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * @author hcq
 * @since 2019-04-09
 */
@RestController
@RequestMapping("brand")
public class BrandController {

    @Autowired
    BrandService brandService;

    @GetMapping("page")
    public ResponseEntity queryBrandByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "key", required = false) String key) {
        IPage<BrandEntity> page1 = brandService.page(new Page<>(page, rows));
        return ResponseEntity.ok(page1);
    }

    @PostMapping("save")
    public ResponseEntity saveBrand(BrandEntity brand, @RequestParam("cids") List<Long> cids) {
        brandService.saveBrandAndCategoriesId(brand, cids);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("cid/{cid}")
    public ResponseEntity queryBrandByCategoryId(@PathVariable("cid") long cid) {
        List<BrandEntity> brandEntities = brandService.queryBrandByCategoryId(cid);
        if (brandEntities == null) {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(brandEntities);
    }

    @GetMapping("list")
    List<BrandEntity> queryBrandByIds(@RequestParam("ids") List<Long> ids){
         return (List<BrandEntity>) brandService.listByIds(ids);
    };
}
