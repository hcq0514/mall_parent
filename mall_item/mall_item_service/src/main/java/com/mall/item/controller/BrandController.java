package com.mall.item.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.item.entity.BrandEntity;
import com.mall.item.service.BrandService;
import io.swagger.annotations.Api;
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
@Api("品牌接口")
@RestController
@RequestMapping("brand")
public class BrandController {

    @Autowired
    BrandService brandService;

    @GetMapping("page")
    @ApiOperation(value = "查询品牌列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码"),
            @ApiImplicitParam(name = "rows", value = "每页显示条数"),
            @ApiImplicitParam(name = "sortBy", value = "排序字段"),
            @ApiImplicitParam(name = "desc", value = "是否为从大到小排序"),
            @ApiImplicitParam(name = "key", value = "搜索字段"),
    })
    public ResponseEntity queryBrandByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "key", required = false) String key) {
        IPage<BrandEntity> page1 = brandService.page(new Page<>(page, rows));
        return ResponseEntity.ok(page1);
    }

    @GetMapping("bid/{bid}")
    @ApiOperation(value = "查询品牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bid", value = "品牌id"),
    })
    public ResponseEntity queryBrandById(@PathVariable("bid") long bid) {
        BrandEntity brand = brandService.getById(bid);
        return ResponseEntity.ok(brand);
    }

    @PostMapping
    @ApiOperation(value = "创建品牌", notes = "创建品牌接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cids", value = "品牌所属的种类数组"),
    })
    public ResponseEntity saveBrand(BrandEntity brand, @RequestParam("cids") List<Long> cids) {
        brandService.saveBrandAndCategoriesId(brand, cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    @ApiOperation(value = "品牌信息修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cids", value = "品牌所属的种类数组"),
    })
    public ResponseEntity update(BrandEntity brand, @RequestParam("cids") List<Long> cids) {
        brandService.updateBrand(brand, cids);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiOperation(value = "品牌删除")
    @ApiImplicitParam(name = "bid", value = "品牌id")
    @DeleteMapping()
    public ResponseEntity deleteBrandById(@RequestParam("bid") long bid) {
        brandService.removeBrand(bid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "品牌查询")
    @GetMapping("{id}")
    public ResponseEntity<BrandEntity> getById(@PathVariable("id") long id) {
        BrandEntity brandEntity = brandService.getById(id);
        return ResponseEntity.ok(brandEntity);
    }

    @ApiOperation(value = "根据品类获取品牌", notes = "根据品类获取品牌")
    @ApiImplicitParam(name = "cid", value = "品类id")
    @GetMapping("cid/{cid}")
    public ResponseEntity queryBrandByCategoryId(@PathVariable("cid") long cid) {
        List<BrandEntity> brandEntities = brandService.queryBrandByCategoryId(cid);
        if (brandEntities == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(brandEntities);
    }
}
