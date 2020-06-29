package com.ming.mingspringbootstarter.demo1;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2020/5/29 10:14 下午
 */
@Configuration
@EnableConfigurationProperties(HttpProperties.class)
public class HttpAutoConfiguration {
    @Resource
    private HttpProperties properties; // 使用配置

    // 在Spring上下文中创建一个对象
    //关于@ConditionalOnMissingBean 这个注解，它的意思是在该bean不存在的情况下此方法才会执行，这个相当于开关的角色
    @Bean
    @ConditionalOnMissingBean
    public HttpClient init() {
        HttpClient client = new HttpClient();

        String url = properties.getUrl();
        client.setUrl(url);
        return client;
    }
}

