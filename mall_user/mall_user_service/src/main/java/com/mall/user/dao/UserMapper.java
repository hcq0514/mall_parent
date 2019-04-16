package com.mall.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.user.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 *
 * @author hcq
 * @since 2019-04-09
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {


    @Select("select * from mall_user where username =#{username}")
    UserEntity selectByName(String username);
}
