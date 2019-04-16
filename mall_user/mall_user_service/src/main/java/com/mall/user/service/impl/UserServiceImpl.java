package com.mall.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.user.dao.UserMapper;
import com.mall.user.entity.UserEntity;
import com.mall.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author hcq
 * @since 2019-04-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Override
    public UserEntity queryUser(String username, String password) {
        UserEntity userEntity = userMapper.selectByName(username);
        if (userEntity != null && encoder.encode(password).equals(userEntity.getPassword())) {
            return userEntity;
        }
        return null;
    }

}
