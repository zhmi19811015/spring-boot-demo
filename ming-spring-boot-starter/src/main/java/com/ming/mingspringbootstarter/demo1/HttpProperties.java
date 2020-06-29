package com.ming.mingspringbootstarter.demo1;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 自动获取配置文件中前缀为http的属性，把值传入对象参数
 *
 * @author zhangming
 * @version 1.0
 * @date 2020/5/29 10:10 下午
 */
@ConfigurationProperties(prefix = "http")
@Data
public class HttpProperties {
    // 如果配置文件中配置了http.url属性，则该默认属性会被覆盖
    //我们可以通过在 application.properties 中添加配置 http.url=https://www.zhihu.com 来覆盖参数的值。
    private String url = "http://www.baidu.com/";
}
