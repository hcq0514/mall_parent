package com.mall.item.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.item.entity.BrandEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author hcq
 * @since 2019-04-09
 */
@Transactional(rollbackFor = Exception.class)
public interface BrandService extends IService<BrandEntity> {

    void saveBrandAndCategoriesId(BrandEntity brand, List<Long> cids);

    List<BrandEntity> queryBrandByCategoryId(long cid);

    void updateBrand(BrandEntity brand, List<Long> cids);

    void removeBrand(long id);
}
