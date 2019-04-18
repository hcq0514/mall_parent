package com.mall.sms.com.mall.sms.demo;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqDemo {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("testMQ")
    public void testSend() throws InterruptedException {
        String msg = "hello, Spring boot amqp";
        rabbitTemplate.convertAndSend("spring.test.exchange","spring.test.queue", msg);
//         等待10秒后再结束
//        Thread.sleep(10000);
    }
}