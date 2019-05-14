package com.mall.item.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.item.dao.BrandMapper;
import com.mall.item.dao.CategoryMapper;
import com.mall.item.entity.BrandEntity;
import com.mall.item.service.BrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author hcq
 * @since 2019-04-09
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, BrandEntity> implements BrandService {

    protected static final Logger logger = LoggerFactory.getLogger(BrandServiceImpl.class);
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBrand(BrandEntity brand, List<Long> cids) {
        try {
            brandMapper.updateById(brand);
            //删除掉mall_category_brand表中的数据
            brandMapper.deleteBrandCategoriesByBrandId(brand.getId());
        } catch (Exception e) {
            logger.error("更新品牌信息失败，错误原因为:[{}]", e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeBrand(long id) {
        try {
            brandMapper.deleteById(id);
            //删除掉mall_category_brand表中的数据
            brandMapper.deleteBrandCategoriesByBrandId(id);
        } catch (Exception e) {
            logger.error("删除品牌信息失败，错误原因为:[{}]", e.getMessage());
        }
    }
}
