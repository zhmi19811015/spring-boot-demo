package com.ming.springbootresourcesfile.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * WebMvcConfigurerAdapter是Spring提供的一个配置mvc的适配器，
 * 里面有很多配置的方法，addResourceHandlers就是专门处理静态资源的方法，其他方法后续我们还会讲到
 *
 *  http://localhost:8080/image/spring.jpg
 * @author zhangming
 * @version 1.0
 * @date 2019-11-18 20:47
 */
@Configuration
public class ImageMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**")
                .addResourceLocations("classpath:/images/");
    }
}
