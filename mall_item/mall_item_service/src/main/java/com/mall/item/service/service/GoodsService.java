package com.mall.item.service.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.item.entity.SpuBo;

/**
 *
 * @author hcq
 * @since 2019-04-09
 */
public interface GoodsService  {

    /**
     * 查询
     * @param iPage
     * @param key
     * @return
     */
    IPage<SpuBo> querySpuByPageAndSort(IPage iPage, String key);
}
