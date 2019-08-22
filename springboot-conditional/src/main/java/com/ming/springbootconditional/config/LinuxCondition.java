package com.ming.springbootconditional.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Linux操作系统的条件：当在Linux系统下运行的时候，matches方法会返回true,否则返回false.
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-08-06 20:01
 */
public class LinuxCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata metadata) {
        String osName = conditionContext.getEnvironment().getProperty("os.name");
        boolean bol = osName.contains("Linux");
        //判断是否已经包含了amazonTest Bean
        // Boolean bol =  conditionContext.getBeanFactory().containsBean("amazonTest");
        return bol;
    }
}
