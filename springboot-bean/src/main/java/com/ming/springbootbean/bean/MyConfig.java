package com.ming.springbootbean.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-09-02 20:44
 */
@Configuration
public class MyConfig {
    // 测试Bean的几种初始化和销毁方式，和执行顺序
    @Bean(initMethod="initMethod", destroyMethod="destroyMethod")
    public InitBeanAndDestroyBean initBeanAndDestroyBean() {
        return new InitBeanAndDestroyBean();
    }
}
