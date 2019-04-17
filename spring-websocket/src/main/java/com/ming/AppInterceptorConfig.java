package com.ming;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Description: 类作用描述
 * @Author: zhangming
 * @CreateDate: 2019/4/10 11:06 PM
 * @Version: 1.0
 */
@Configuration
public class AppInterceptorConfig extends WebMvcConfigurationSupport {

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController()
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/test/**").addResourceLocations("classpath:/test/");
    }
}
