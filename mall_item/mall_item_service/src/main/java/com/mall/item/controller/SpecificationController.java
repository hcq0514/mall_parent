package com.mall.item.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mall.item.entity.SpecificationEntity;
import com.mall.item.service.SpecificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author hcq
 * @since 2019-04-09
 */
@Api("规格参数模版")
@RestController
@RequestMapping("spec")
public class SpecificationController {

    @Autowired
    SpecificationService specificationService;

    @ApiOperation("根据类别id查询参数模版")
    @GetMapping("{cid}")
    public ResponseEntity querySpecByCid(@PathVariable("cid") long cid){
        SpecificationEntity specificationEntity = new SpecificationEntity();
        specificationEntity.setCategoryId(cid);
        SpecificationEntity spec= specificationService.getOne(new QueryWrapper(specificationEntity));
        if (spec == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(spec.getSpecifications());
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity saveOrUpdateSpec(SpecificationEntity brand) {
        specificationService.saveOrUpdate(brand);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
