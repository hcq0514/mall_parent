package com.mall.item.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.item.entity.BrandEntity;
import com.mall.item.service.dao.BrandMapper;
import com.mall.item.service.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hcq
 * @since 2019-04-09
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, BrandEntity> implements BrandService {

    @Autowired
    BrandMapper brandMapper;

    @Override
    public void saveBrandAndCategoriesId(BrandEntity brand, List<Long> cids) {
        brandMapper.insert(brand);
        for (long cid : cids) {
            brandMapper.insertCategoryIdAndBrandId(cid, brand.getId());
        }
    }

    @Override
    public List<BrandEntity> queryBrandByCategoryId(long cid) {
        return brandMapper.selectBrandByCategoryId(cid);
    }
}
