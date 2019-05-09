package com.mall.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.user.entity.UserEntity;

/**
 *
 * @author hcq
 * @since 2019-04-09
 */
public interface UserService extends IService<UserEntity> {
    /**
     * 检查用户名和手机号是否可用
     * @param data
     * @param type
     * @return
     */
    Boolean checkData(String data, Integer type);

    UserEntity queryUser(String username, String password);

    void sendVerifyCode(String phone);

    Boolean register(UserEntity user, String code);
}
