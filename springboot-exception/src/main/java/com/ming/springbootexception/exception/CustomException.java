package com.ming.springbootexception.exception;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/7/16 2:48 PM
 */
public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 4564124491192825748L;

    private int code;

    public CustomException() {
        super();
    }

    public CustomException(int code, String message) {
        super(message);
        this.setCode(code);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
