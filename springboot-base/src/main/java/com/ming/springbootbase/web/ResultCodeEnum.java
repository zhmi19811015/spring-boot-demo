package com.ming.springbootbase.web;

import lombok.Getter;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2020/3/19 8:27 下午
 */
@Getter
public enum ResultCodeEnum {
    SUCCESS(true,200,"成功"),
    UNKNOWN_ERROR(false,201,"未知错误"),
    PARAM_ERROR(false,202,"参数错误"),
    NULL_POINT(false,203,"空指针异常"),
    HTTP_CLIENT_ERROR(false,204,"http请求异常");

    // 响应是否成功
    private Boolean success;
    // 响应状态码
    private Integer code;
    // 响应信息
    private String message;

    ResultCodeEnum(boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
