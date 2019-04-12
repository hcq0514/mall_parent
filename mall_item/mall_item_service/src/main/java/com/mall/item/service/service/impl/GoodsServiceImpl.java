package com.mall.item.service.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.item.entity.CategoryEntity;
import com.mall.item.entity.SpuBo;
import com.mall.item.entity.SpuEntity;
import com.mall.item.service.dao.BrandMapper;
import com.mall.item.service.dao.CategoryMapper;
import com.mall.item.service.dao.SpuMapper;
import com.mall.item.service.service.GoodsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hcq
 * @since 2019-04-09
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    SpuMapper spuMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    BrandMapper brandMapper;


    @Override
    public IPage<SpuBo> querySpuByPageAndSort(IPage iPage, String key) {
        IPage spuIpage = spuMapper.selectPage(iPage, null);
        List<SpuBo> spuBos = new ArrayList<>();
        List<SpuEntity> spuEntities = spuIpage.getRecords();
        for (SpuEntity spuEntity : spuEntities) {
            SpuBo spuBo = new SpuBo();
            BeanUtils.copyProperties(spuEntity, spuBo);
            //写入categoryName
            List<CategoryEntity> categoryEntities = categoryMapper.selectBatchIds(Arrays.asList(spuEntity.getCid1(), spuEntity.getCid2(), spuEntity.getCid3()));
            String categoryName = categoryEntities.stream().map(CategoryEntity::getName).collect(Collectors.toList()).toString();
            spuBo.setCategoryName(categoryName);
            //写入brandName
            spuBo.setBrandName(brandMapper.selectById(spuEntity.getBrandId()).getName());
            spuBos.add(spuBo);
        }
        spuIpage.setRecords(spuBos);
        return spuIpage;
    }
}
