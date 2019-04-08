package com.mall.common.entity;

import java.net.HttpURLConnection;

/**
 * 状态码实体类
 */
public class HttpStatusCode {
    /**
     * 200
     * 成功
     */
    public static final int OK = HttpURLConnection.HTTP_OK;
    /**
     * 201
     * 创建
     */
    public static final int CREATED = HttpURLConnection.HTTP_CREATED;
    /**
     * 204
     * 请求正确，但是没有需要返回的内容。比如说，我们请求删除某个用户，删除成功可以返回 204。
     */
    public static final int DELETE_SUCCESS = HttpURLConnection.HTTP_NO_CONTENT;
    /**
     * 500
     * 服务器错误。没法明确定义的服务器错误都可以返回这个。
     */
    public static final int INTERNAL_ERROR = HttpURLConnection.HTTP_INTERNAL_ERROR;

    public static final int LOGINERROR = 20002;//用户名或密码错误
    public static final int ACCESSERROR = 20003;//权限不足
    public static final int REMOTEERROR = 20004;//远程调用失败
    public static final int REPERROR = 20005;//重复操作
}
