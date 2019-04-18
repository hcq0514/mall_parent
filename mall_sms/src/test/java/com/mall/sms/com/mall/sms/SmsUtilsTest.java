package com.mall.sms.com.mall.sms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mall.sms.SmsApplication;
import com.mall.sms.com.mall.sms.util.SmsUtils;
import org.apache.commons.collections.MapUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
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

    @Test
    public void testSms(){
//        String property = environment.getProperty("sms.aliyun.regionId");
        Map map = new HashMap();
        map.put("code","1442");
        String s1 = JSONObject.toJSONString(map);
        smsUtils.sendSms("","","",s1);
    }

}
