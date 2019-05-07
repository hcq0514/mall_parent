package com.mall.cart.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mall.cart.client.GoodsClient;
import com.mall.cart.entity.CartEntity;
import com.mall.common.entity.RedisPrefix;
import com.mall.item.entity.SkuEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private GoodsClient goodsClient;

    static final Logger logger = LoggerFactory.getLogger(CartService.class);

    public void addCart(CartEntity cart) {
        // 获取登录用户
//        UserInfo user = LoginInterceptor.getLoginUser();
        // Redis的key
//        String key = RedisPrefix.USER_CART + user.getId();
        String key = RedisPrefix.USER_CART + "temp_user";
        // 获取hash操作对象
        BoundHashOperations<String, Object, Object> hashOps = redisTemplate.boundHashOps(key);
        // 查询是否存在
        Long skuId = cart.getSkuId();
        Integer num = cart.getNum();
        boolean boo = hashOps.hasKey(skuId.toString());
        if (boo) {
            // 存在，获取购物车数据
            String json = hashOps.get(skuId.toString()).toString();
            cart = JSON.parseObject(json,CartEntity.class);
            cart.setNum(cart.getNum() + num);
        } else {
            // 不存在，新增购物车数据
            cart.setUserId(10086L);
            // 其它商品信息， 需要查询商品服务
            SkuEntity sku = goodsClient.querySkuBySkuId(skuId).getBody();
//            cart.setImage(StringUtils.isBlank(sku.getImages()) ? "" : StringUtils.split(sku.getImages(), ",")[0]);
            cart.setPrice(sku.getPrice());
            cart.setTitle(sku.getTitle());
            cart.setOwnSpec(sku.getOwnSpec());
        }
        // 将购物车数据写入redis
        hashOps.put(cart.getSkuId().toString(), JSONObject.toJSON(cart));
    }
}
