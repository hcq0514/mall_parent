package com.mall.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.common.entity.RabbitMQCode;
import com.mall.common.entity.RedisPrefix;
import com.mall.common.util.CommonUtil;
import com.mall.user.dao.UserMapper;
import com.mall.user.entity.UserEntity;
import com.mall.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author hcq
 * @since 2019-04-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    protected static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserMapper userMapper;
//    @Autowired
//    BCryptPasswordEncoder encoder;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public UserEntity queryUser(String username, String password) {
//        UserEntity userEntity = userMapper.selectByName(username);
//        if (userEntity != null && encoder.encode(password).equals(userEntity.getPassword())) {
//            return userEntity;
//        }
        return null;
    }

    @Override
    public void sendVerifyCode(String phone) {
        String verifyCode = CommonUtil.generateCode(6);
        Map<String, String> map = new HashMap<>(3);
        map.put("code", verifyCode);
        map.put("phone", phone);
        try {
            rabbitTemplate.convertAndSend(RabbitMQCode.USER_EXCHANGE, RabbitMQCode.USER_VERIFICATION_CODE_ROUTING_KEY, map);
            redisTemplate.opsForValue().set(RedisPrefix.VERIFY_CODE + phone, verifyCode, 5, TimeUnit.MINUTES);
        } catch (Exception e) {
            logger.error("发送短信失败，号码为:[{}],验证码为:[{}],错误信息为:" );
        }
    }

}
