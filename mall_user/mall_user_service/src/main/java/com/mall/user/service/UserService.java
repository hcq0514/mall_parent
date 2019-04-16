package com.mall.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.user.entity.UserEntity;

/**
 *
 * @author hcq
 * @since 2019-04-09
 */
public interface UserService extends IService<UserEntity> {

    UserEntity queryUser(String username, String password);
}
