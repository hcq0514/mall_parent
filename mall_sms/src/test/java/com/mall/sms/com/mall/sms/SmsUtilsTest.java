package com.mall.sms.com.mall.sms;

import com.mall.common.entity.RabbitMQCode;
import com.mall.sms.SmsApplication;
import com.mall.sms.com.mall.sms.util.SmsUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SmsApplication.class,SmsUtilsTest.class})
public class SmsUtilsTest {
    @Autowired
    SmsUtils smsUtils;
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void testSms() throws InterruptedException {
        Map map = new HashMap();
        map.put("code","1442");
        map.put("phone","131130501027");
        rabbitTemplate.convertAndSend(RabbitMQCode.USER_EXCHANGE,RabbitMQCode.USER_VERIFICATION_CODE_ROUTING_KEY,map);
        Thread.sleep(10000);
        System.out.println("发送ok");
//        String property = environment.getProperty("sms.aliyun.regionId");

//        smsUtils.sendSms("","","",s1);
    }

}
