package com.mall.item.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

//@TableName("tb_spec_group")
public class SpecGroup {

//    @TableId(type = AUTO)
    private Long id;

    private Long cid;

    private String name;

    private List<SpecPara> params; // 该组下的所有规格参数集合
}