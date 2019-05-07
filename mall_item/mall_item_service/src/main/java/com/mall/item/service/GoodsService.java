package com.mall.item.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.item.entity.SkuEntity;
import com.mall.item.entity.SpuDetailEntity;
import com.mall.item.entity.SpuEntity;
import com.mall.item.entity.bo.SpuBo;

import java.util.List;

/**
 *
 * @author hcq
 * @since 2019-04-09
 */
public interface GoodsService {

    /**
     * 查询
     *
     * @param iPage
     * @param key
     * @return
     */
    IPage<SpuBo> querySpuByPageAndSort(IPage iPage, String key);

    /**
     * 保存
     * @param spu
     */
    void save(SpuBo spu);

    SpuDetailEntity querySpuDetailBySpuId(long id);

    List<SkuEntity> querySkuBySpuId(long spuId);

    SpuEntity querySpuById(Long id);

    SkuEntity queryskuBySkuId(Long skuId);
}
