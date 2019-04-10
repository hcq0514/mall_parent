package com.mall.item.service.controller;

import com.mall.common.entity.HttpStatusCode;
import com.mall.common.entity.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: hcq
 * @Date: 2019/3/23
 * 统一异常处理类
 */
@ControllerAdvice
public class ItemExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return new Result(false, HttpStatusCode.INTERNAL_ERROR, e.getMessage());
    }
}
