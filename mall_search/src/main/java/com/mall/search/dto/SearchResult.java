package com.mall.search.dto;

import com.mall.item.entity.BrandEntity;
import com.mall.item.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * @Author: 98050
 * Time: 2018-10-12 21:06
 * Feature: 搜索结果存储对象
 */
public class SearchResult<Goods> extends PageResult<Goods> {

    /**
     * 分类的集合
     */
    private List<CategoryEntity> categories;

    /**
     * 品牌的集合
     */
    private List<BrandEntity> BrandEntitys;

    /**
     * 规格参数的过滤条件
     */
    private List<Map<String, Object>> specs;

    public List<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
    }

    public List<BrandEntity> getBrandEntitys() {
        return BrandEntitys;
    }

    public void setBrandEntitys(List<BrandEntity> BrandEntitys) {
        this.BrandEntitys = BrandEntitys;
    }

    public List<Map<String, Object>> getSpecs() {
        return specs;
    }

    public void setSpecs(List<Map<String, Object>> specs) {
        this.specs = specs;
    }

    public SearchResult(List<CategoryEntity> categories, List<BrandEntity> BrandEntitys, List<Map<String, Object>> specs) {
        this.categories = categories;
        this.BrandEntitys = BrandEntitys;
        this.specs = specs;
    }

    public SearchResult(Long total, List<Goods> item, List<CategoryEntity> categories, List<BrandEntity> BrandEntitys, List<Map<String, Object>> specs) {
        super(total, item);
        this.categories = categories;
        this.BrandEntitys = BrandEntitys;
        this.specs = specs;
    }

    public SearchResult(Long total, Long totalPage, List<Goods> item, List<CategoryEntity> categories, List<BrandEntity> BrandEntitys) {
        super(total, totalPage, item);
        this.categories = categories;
        this.BrandEntitys = BrandEntitys;
    }

    public SearchResult(Long total, Long totalPage, List<Goods> item, List<CategoryEntity> categories,
                        List<BrandEntity> BrandEntitys, List<Map<String, Object>> specs) {
        super(total, totalPage, item);
        this.categories = categories;
        this.BrandEntitys = BrandEntitys;
        this.specs = specs;
    }
}
