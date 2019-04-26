package com.mall.goods.web.controller;

import com.mall.goods.web.client.BrandClient;
import com.mall.goods.web.service.GoodsWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("item")
public class ItemController {
    @Autowired
    private GoodsWebService goodsWebService;

    @Autowired
    private BrandClient brandClient;
    /**
     * 跳转到商品详情页
     * @param model
     * @param id
     * @return
     */
    @GetMapping("{id}.html")
    public String toItemPage(Model model, @PathVariable("id")Long id){
        // 加载所需的数据
        Map<String, Object> modelMap = goodsWebService.loadModel(id);
        // 放入模型
        model.addAllAttributes(modelMap);
        return "item";
    }
}
