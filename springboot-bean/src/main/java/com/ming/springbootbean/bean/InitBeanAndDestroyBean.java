package com.ming.springbootbean.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-09-02 20:43
 */
public class InitBeanAndDestroyBean implements InitializingBean, DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("接口-执行InitBeanAndDestroyBeanTest：destroy方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("接口-执行InitBeanAndDestroyBeanTest：afterPropertiesSet方法-3");
    }

    public String say() {
        return "Hello!" + this.getClass().getName();
    }

    public InitBeanAndDestroyBean() {
        System.out.println("执行InitBeanAndDestroyBean构造方法-1");
    }

    @PostConstruct
    public void postConstructstroy() {
        System.out.println("注解-执行InitBeanAndDestroyBeanTest：postConstructstroy方法-2");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("注解--执行InitBeanAndDestroyBeanTest：preDestroy方法");
    }

    public void initMethod() {
        System.out.println("XML配置-执行InitBeanAndDestroyBeanTest：init-method方法-4");
    }

    public void destroyMethod() {
        System.out.println("XML配置-执行InitBeanAndDestroyBeanTest：destroy-method方法");
    }
}
