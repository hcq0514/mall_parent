package com.mall.user.mq;

import com.mall.common.entity.RabbitMQCode;
import com.mall.user.UserApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class UserSenderMQ {

    protected static final Logger logger = LoggerFactory.getLogger(UserSenderMQ.class);


    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendVeritificationCode(String phone, String code) {
        Map map = new HashMap<>(8);
        map.put("phone", phone);
        map.put("code", code);
        try {
            rabbitTemplate.convertAndSend(RabbitMQCode.USER_EXCHANGE, RabbitMQCode.USER_VERIFICATION_CODE_QUEUE, map);
        } catch (Exception e) {
            logger.error("发送验证码发生错误，错误信息为:" + e.getMessage(), e);
        }
    }

}
