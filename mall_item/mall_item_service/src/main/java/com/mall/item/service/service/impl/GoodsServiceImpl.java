package com.mall.item.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.item.entity.*;
import com.mall.item.entity.bo.SpuBo;
import com.mall.item.service.dao.*;
import com.mall.item.service.service.GoodsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    @Autowired
    SkuMapper skuMapper;

    @Autowired
    SpuDetailMapper spuDetailMapper;

    @Autowired
    StockMapper stockMapper;


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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(SpuBo spu) {
        // 保存spu
        spu.setSaleable(true);
        spu.setValid(true);
        spu.setCreateTime(LocalDateTime.now());
        spu.setLastUpdateTime(spu.getCreateTime());
        spuMapper.insert(spu);
        // 保存spu详情
        spu.getSpuDetail().setSpuId(spu.getId());
        spuDetailMapper.insert(spu.getSpuDetail());

        // 保存sku和库存信息
        saveSkuAndStock(spu.getSkus(), spu.getId());
    }

    private void saveSkuAndStock(List<SkuEntity> skus, Long spuId) {
        for (SkuEntity sku : skus) {
            if (!sku.getEnable()) {
                continue;
            }
            // 保存sku
            sku.setSpuId(spuId);
            // 默认不参与任何促销
            sku.setCreateTime(LocalDateTime.now());
            sku.setLastUpdateTime(sku.getCreateTime());
            this.skuMapper.insert(sku);

            // 保存库存信息
            StockEntity stock = new StockEntity();
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());
            this.stockMapper.insert(stock);
        }
    }

    @Override
    public SpuDetailEntity querySpuDetailBySpuId(long spu_id) {
        return spuDetailMapper.selectSpuDetailBySpuId(spu_id);
    }

    @Override
    public List<SkuEntity> querySkuBySpuId(long spuId) {
        SkuEntity skuEntity = new SkuEntity();
        skuEntity.setSpuId(spuId);
        return skuMapper.selectList(new QueryWrapper(skuEntity));
    }
}
