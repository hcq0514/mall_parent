package com.mall.item.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.item.entity.SpecGroup;
import com.mall.item.entity.SpecificationEntity;
import com.mall.item.dao.SpecificationMapper;
import com.mall.item.service.SpecificationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author hcq
 * @since 2019-04-09
 */
@Service
public class SpecificationServiceImpl extends ServiceImpl<SpecificationMapper, SpecificationEntity> implements SpecificationService {

    @Override
    public List<SpecGroup> querySpecsByCid(Long cid) {
//        // 查询规格组
//        List<SpecGroup> groups = querySpecGroups(cid);
//        SpecParam param = new SpecParam();
//        groups.forEach(g -> {
//            // 查询组内参数
//            g.setParams(this.querySpecParams(g.getId(), null, null, null));
//        });
//        return groups;
        return null;
    }
}
