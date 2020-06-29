package com.ming.springioc.core;

/**
 * 统一容器管理
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-11-21 19:40
 */
public interface MyBeanFactory {
    Object getBeanByName(String name) throws Exception;
}
