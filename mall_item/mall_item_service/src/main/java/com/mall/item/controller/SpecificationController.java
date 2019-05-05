package com.mall.item.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mall.item.entity.SpecificationEntity;
import com.mall.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author hcq
 * @since 2019-04-09
 */
@RestController
@RequestMapping("spec")
public class SpecificationController {

    @Autowired
    SpecificationService specificationService;

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
