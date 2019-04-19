package com.mall.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        UserEntity user = userMapper.selectOne(new QueryWrapper<>(userEntity));
        //检验用户是否存在
        if (user == null) {
            return  null;
        }
        //检验密码是否正确
//        if (user.getPassword())
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
            logger.error("发送短信失败，号码为:[{}],验证码为:[{}],错误信息为");
        }
    }

    @Override
    public Boolean register(UserEntity user, String code) {
        String key = RedisPrefix.VERIFY_CODE + user.getPhone();
        // 从redis取出验证码
        String codeCache = (String) redisTemplate.opsForValue().get(key);
        // 检查验证码是否正确
        if (!code.equals(codeCache)) {
            // 不正确，返回
            return false;
        }
        user.setId(null);
        user.setCreated(LocalDateTime.now());
//        // 生成盐
//        String salt = CodecUtils.generateSalt();
//        user.setSalt(salt);
//        // 对密码进行加密
//        user.setPassword(CodecUtils.md5Hex(user.getPassword(), salt));
        // 写入数据库
        boolean boo = userMapper.insert(user) == 1;

        // 如果注册成功，删除redis中的code
        if (boo) {
            try {
                this.redisTemplate.delete(key);
            } catch (Exception e) {
                logger.error("删除缓存验证码失败，code：{}", code, e);
            }
        }
        return boo;
    }

}
