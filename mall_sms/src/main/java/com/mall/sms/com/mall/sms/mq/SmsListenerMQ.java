package com.mall.sms.com.mall.sms.mq;

import com.alibaba.fastjson.JSON;
import com.mall.common.entity.RabbitMQCode;
import com.mall.sms.com.mall.sms.config.SmsConfig;
import com.mall.sms.com.mall.sms.config.SmsTemplateCode;
import com.mall.sms.com.mall.sms.util.SmsUtils;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hcq
 */
@Component
public class SmsListenerMQ {
    @Autowired
    SmsUtils smsUtils;

    /**
     * 验证码发送
     *
     * @param map
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMQCode.USER_QUEUE, durable = "true"),
            exchange = @Exchange(
                    value = RabbitMQCode.USER_EXCHANGE,
                    ignoreDeclarationExceptions = "true"
            ),
            key = RabbitMQCode.USER_VERIFICATION_CODE_ROUTING_KEY
    ))
    public void sendVerificationCode(Map map) {
        String phone = (String) map.get("phone");
        Map<String, Object> para = new HashMap<>(3);
        para.put("code", map.get("code"));
        smsUtils.sendSms(phone, SmsConfig.SIGN_NAME, SmsTemplateCode.VERIFICATION_CODE, JSON.toJSONString(para));
    }
}
