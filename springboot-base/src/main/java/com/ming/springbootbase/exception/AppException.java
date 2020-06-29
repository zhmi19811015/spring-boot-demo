package com.ming.springbootbase.exception;

import com.ming.springbootbase.web.ResultCodeEnum;
import lombok.Data;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2020/3/19 8:34 下午
 */
@Data
public class AppException extends RuntimeException {
    private Integer code;

    public AppException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public AppException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "AppException{" + "code=" + code + ", message=" + this.getMessage() + '}';
    }
}
