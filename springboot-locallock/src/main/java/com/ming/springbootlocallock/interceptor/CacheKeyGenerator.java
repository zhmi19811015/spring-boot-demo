package com.ming.springbootlocallock.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 创建一个 CacheKeyGenerator 具体实现由使用者自己去注入
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/7/16 3:30 PM
 */
public interface CacheKeyGenerator {
    /**
     * 获取AOP参数,生成指定缓存Key
     *
     * @param pjp PJP
     * @return 缓存KEY
     */
    String getLockKey(ProceedingJoinPoint pjp);
}

