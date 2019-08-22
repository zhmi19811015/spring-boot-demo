package com.ming.mingspringbootstarter.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 前缀、后缀通过读取application.properties(yml)内的参数获得
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/8/2 11:04 AM
 */
@ConfigurationProperties("ming.service")
public class MingServiceProperties {
    private String prefix;
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
