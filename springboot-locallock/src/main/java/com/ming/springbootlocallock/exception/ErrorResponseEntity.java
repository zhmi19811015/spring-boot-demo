package com.ming.springbootlocallock.exception;

import lombok.Data;

/**
 * 定义返回的异常信息的格式，这样异常信息风格更为统一
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/7/16 2:48 PM
 */
@Data
public class ErrorResponseEntity {
    private int code;
    private String message;

    public ErrorResponseEntity(int code, String message){
        this.code = code;
        this.message = message;
    }
}
