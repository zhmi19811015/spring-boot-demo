package com.ming.demo;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Description: 类作用描述
 * @Author: zhangming
 * @CreateDate: 2019/4/18 9:11 PM
 * @Version: 1.0
 */
public class BeanUtil implements ApplicationContextAware{
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (applicationContext == null){
            this.applicationContext = applicationContext;
        }
    }


}
