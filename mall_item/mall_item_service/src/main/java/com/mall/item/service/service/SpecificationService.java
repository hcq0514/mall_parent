package com.mall.item.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.item.entity.CategoryEntity;
import com.mall.item.entity.SpecGroup;
import com.mall.item.entity.SpecificationEntity;

import java.util.List;

/**
 *
 * @author hcq
 * @since 2019-04-09
 */
public interface SpecificationService extends IService<SpecificationEntity> {

    List<SpecGroup> querySpecsByCid(Long cid);
}
