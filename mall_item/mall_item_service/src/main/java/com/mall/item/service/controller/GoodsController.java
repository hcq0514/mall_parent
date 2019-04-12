package com.mall.item.service.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.item.entity.SpuBo;
import com.mall.item.service.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hcq
 * @since 2019-04-09
 */
@RestController
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @GetMapping("/spu/page")
    public ResponseEntity<IPage<SpuBo>> querySpuByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "key", required = false) String key) {

        IPage<SpuBo> result = goodsService.querySpuByPageAndSort(new Page(page, rows),  key);
        if (result == null || result.getRecords().size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

}
