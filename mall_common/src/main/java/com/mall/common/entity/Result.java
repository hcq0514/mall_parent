package com.mall.common.entity;

import lombok.Data;

@Data
public class Result {
    /**
     * 是否成功
     */
    private boolean flag;
    /**
     * 返回码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 返回数据
     */
    private Object data;

    public Result(boolean flag, Integer code, String message, Object data) {
        super();
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result() {
    }

    public Result(boolean flag, Integer code, String message) {
        super();
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public static Result ok(Object data) {
        return new Result(true,HttpStatusCode.OK,"请求成功",data);
    }

    public static Result error(Object data) {
        return new Result(true,HttpStatusCode.INTERNAL_ERROR,"请求失败",data);
    }

}