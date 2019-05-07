package com.mall.item.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.item.entity.SkuEntity;
import com.mall.item.entity.SpuDetailEntity;
import com.mall.item.entity.SpuEntity;
import com.mall.item.entity.bo.SpuBo;
import com.mall.item.service.GoodsService;
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
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;


    @GetMapping("/spu/page")
    public ResponseEntity<IPage<SpuBo>> querySpuByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "key", required = false) String key) {

        IPage<SpuBo> result = goodsService.querySpuByPageAndSort(new Page(page, rows), key);
        if (result == null || result.getRecords().size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    /**
     * 新增商品
     *
     * @param spuBo
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> saveGoods(@RequestBody SpuBo spuBo) {
        //todo skus多选时会存在转换问题。
        try {
            goodsService.save(spuBo);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("spu/detail/{spuDeailId}")
    public ResponseEntity<SpuDetailEntity> querySpuDetailBySpuId(@PathVariable long spuDeailId) {
        SpuDetailEntity spuDetailEntity = goodsService.querySpuDetailBySpuId(spuDeailId);
        if (spuDetailEntity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(spuDetailEntity);
    }

    /**
     * 根据spu的id查询sku
     * @param spuId
     * @return
     */
    @GetMapping("sku/list")
    List<SkuEntity> querySkuBySpuId(@RequestParam("id") long spuId) {
        return goodsService.querySkuBySpuId(spuId);
    }

    @GetMapping("spu/{id}")
    public ResponseEntity<SpuEntity> querySpuById(@PathVariable("id") Long id) {
        SpuEntity spu = goodsService.querySpuById(id);
        if (spu == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(spu);
    }

    @GetMapping("sku/{id}")
    public ResponseEntity<SkuEntity> querySkuBySkuId(@PathVariable long id) {
        SkuEntity skuEntity = goodsService.queryskuBySkuId(id);
        if (skuEntity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(skuEntity);
    }

}
