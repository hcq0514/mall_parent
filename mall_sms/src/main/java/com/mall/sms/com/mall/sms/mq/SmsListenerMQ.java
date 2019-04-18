package com.mall.sms.com.mall.sms.mq;

import com.alibaba.fastjson.JSON;
import com.mall.common.entity.RabbitMQCode;
import com.mall.sms.com.mall.sms.config.SmsConfig;
import com.mall.sms.com.mall.sms.config.SmsTemplateCode;
import com.mall.sms.com.mall.sms.util.SmsUtils;
import com.rabbitmq.tools.json.JSONUtil;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hcq
 */
@Component
public class SmsListenerMQ {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMQCode.USER_VERIFICATION_CODE_QUEUE, durable = "true"),
            exchange = @Exchange(
                    value = RabbitMQCode.USER_EXCHANGE,
                    ignoreDeclarationExceptions = "true"
            )
    ))
    public void sendVerificationCode(Map map) {
        String phone = (String) map.get("phone");
        Map<String,Object> para = new HashMap<>(3);
        para.put("code",map.get("code"));
        SmsUtils.sendSms(phone, SmsConfig.SIGN_NAME, SmsTemplateCode.VERIFICATION_CODE,JSON.toJSONString(para));
    }
}
