package com.ming.springbootlocallock.annotation;

import java.lang.annotation.*;

/**
 * 锁的注解
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/7/16 3:20 PM
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LocalLock {
    /**
     * @author fly
     */
    String key() default "";

    /**
     * 过期时间 TODO 由于用的 guava 暂时就忽略这属性吧 集成 redis 需要用到
     *
     * @author fly
     */
    int expire() default 5;
}
