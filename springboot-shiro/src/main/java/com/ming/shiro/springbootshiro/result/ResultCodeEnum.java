package com.ming.shiro.springbootshiro.result;

/**
 * 响应码 枚举，参考HTTP状态码的语义
 *
 * Created by zhaod on 2018/1/24 17:42
 */
public enum ResultCodeEnum {
    /** 成功 .*/
    SUCCESS(200),
    /** 失败 .*/
    FAIL(400),
    /** 未认证（签名错误） .*/
    UNAUTHORIZED(401),
    /** 接口不存在 .*/
    NOT_FOUND(404),
    /** 服务器内部错误 .*/
    INTERNAL_SERVER_ERROR(500);

    public int code;

    ResultCodeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
