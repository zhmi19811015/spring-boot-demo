package com.ming.springbootlocallock.annotation;

import java.lang.annotation.*;

/**
 * 锁的参数
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/7/16 3:29 PM
 */
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheParam {
    /**
     * 字段名称
     *
     * @return String
     */
    String name() default "";
}
