package com.ming.springbootmybatis.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * 通过AOP设置全局事务，拦截service包下面Impl类所有方法
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/7/15 10:05 AM
 */
@Aspect
@Configuration
public class TransactionManagerConfig {
    /**  切面扫包 .*/
    private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.ming.springbootmybatis.service..*.*(..))";
    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Bean
    public TransactionInterceptor txAdvice(){
        DefaultTransactionAttribute txAttr_REQUIRED = new DefaultTransactionAttribute();
        txAttr_REQUIRED.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        DefaultTransactionAttribute txAttr_REQUIRED_READONLY = new DefaultTransactionAttribute();
        txAttr_REQUIRED_READONLY.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        txAttr_REQUIRED_READONLY.setReadOnly(true);

        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        source.addTransactionalMethod("add*", txAttr_REQUIRED);
        source.addTransactionalMethod("append*", txAttr_REQUIRED);
        source.addTransactionalMethod("insert*", txAttr_REQUIRED);
        source.addTransactionalMethod("save*", txAttr_REQUIRED);
        source.addTransactionalMethod("update*", txAttr_REQUIRED);
        source.addTransactionalMethod("modify*", txAttr_REQUIRED);
        source.addTransactionalMethod("edit*", txAttr_REQUIRED);
        source.addTransactionalMethod("delete*", txAttr_REQUIRED);
        source.addTransactionalMethod("remove*", txAttr_REQUIRED);
        source.addTransactionalMethod("repair", txAttr_REQUIRED);
        source.addTransactionalMethod("delAndRepair", txAttr_REQUIRED);


        source.addTransactionalMethod("get*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("find*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("load*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("search*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("datagrid*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("*", txAttr_REQUIRED_READONLY);
        return new TransactionInterceptor(platformTransactionManager, source);

    }

    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }

}
