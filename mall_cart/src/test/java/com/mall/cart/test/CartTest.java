package com.mall.cart.test;

import com.mall.cart.CartServiceApplication;
import com.mall.cart.entity.CartEntity;
import com.mall.cart.service.CartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CartServiceApplication.class)
public class CartTest {
    @Autowired
    private CartService cartService;

    @Test
    public void addCartTest() {
        CartEntity cartEntity = new CartEntity();
        cartEntity.setSkuId(26652898174l);
        cartEntity.setNum(2);
        cartService.addCart(cartEntity);
    }

}
