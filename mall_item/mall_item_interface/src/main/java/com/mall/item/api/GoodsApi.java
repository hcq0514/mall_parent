package com.mall.item.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.item.entity.SkuEntity;
import com.mall.item.entity.SpuDetailEntity;
import com.mall.item.entity.SpuEntity;
import com.mall.item.entity.bo.SpuBo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/goods")
public interface GoodsApi {

    @GetMapping("/spu/page")
    ResponseEntity<Page<SpuBo>> querySpuByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "key", required = false) String key);

    /**
     * 根据spu商品id查询详情
     * @return
     */
    @GetMapping("/spu/detail/{spuDeailId}")
    ResponseEntity<SpuDetailEntity> querySpuDetailBySpuId(@PathVariable("spuDeailId") Long spuDeailId);

    /**
     * 根据spu的id查询sku
     * @param id
     * @return
     */
    @GetMapping("sku/list")
    List<SkuEntity> querySkuBySpuId(@RequestParam("id") Long id);

    /**
     * 根据spu的id查询spu
     * @param id
     * @return
     */
    @GetMapping("spu/{id}")
    ResponseEntity<SpuEntity> querySpuById(@PathVariable("id") Long id);
}