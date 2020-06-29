package com.ming.springasync.global;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;

/**
 * 配置异常处理器
 *
 * @author zhangming
 * @version 1.0
 * @date 2020/5/23 9:43 上午
 */
@Configuration
@EnableAsync  //如果在appction类中引用 这里可以不引用
public class AsyncConfig implements AsyncConfigurer {
    @Autowired
    private GlobalAsyncExceptionHandler exceptionHandler;

    /**
     * 实现 #getAsyncExecutor() 方法，返回 Spring Task 异步任务的默认执行器。
     * 这里，我们返回了 null ，并未定义默认执行器。所以最终会使用 TaskExecutionAutoConfiguration
     * 自动化配置类创建出来的 ThreadPoolTaskExecutor 任务执行器，作为默认执行器
     * @return
     */
    @Override
    public Executor getAsyncExecutor() {
        return null;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return exceptionHandler;
    }
}
