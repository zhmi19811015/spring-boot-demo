package com.ming.mingspringbootstarter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/8/2 11:06 AM
 */
@Configuration
//当classpath 下发现该类的情况下进行自动配置
@ConditionalOnClass(MingService.class)

@EnableConfigurationProperties(MingServiceProperties.class)
public class MingAutoConfigure {
    @Autowired
    private MingServiceProperties properties;

    @Bean
    //当spring context 中不存在该bean时
    @ConditionalOnMissingBean
    //当配置文件中ming.service.enabled=true时
    @ConditionalOnProperty(prefix = "ming.service",value = "enabled",havingValue = "true")
    MingService exampleService (){
        return  new MingService(properties.getPrefix(),properties.getSuffix());
    }
}
