package com.ming.demo.conditional;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @Description: 在 Spring 里可以很方便的编写你自己的条件类，所要做的就是实现 Condition 接口，并覆盖它的 matches()方法。
 * @Author: zhangming
 * @CreateDate: 2019/4/13 10:33 PM
 * @Version: 1.0
 */
public class ConditionalTest implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        return true;
    }
}
