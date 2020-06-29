package com.ming.springasync.global;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 全局统一的异步调用异常的处理器
 * 注意，AsyncUncaughtExceptionHandler 只能拦截返回类型非 Future 的异步调用方法
 * @author zhangming
 * @version 1.0
 * @date 2020/5/23 9:37 上午
 */
@Component
@Slf4j
public class GlobalAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        log.error("[handleUncaughtException][method({}) params({}) 发生异常]",
                method, params, ex);

    }
}
