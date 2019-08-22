package com.ming.springbootconditional.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-08-06 20:19
 */
public class MacCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata metadata) {
        String osName = conditionContext.getEnvironment().getProperty("os.name");//Mac OS X
        boolean bol = osName.contains("Mac");
        //判断是否已经包含了amazonTest Bean
        // Boolean bol =  conditionContext.getBeanFactory().containsBean("amazonTest");
        return bol;
    }
}
