package com.ming.mingspringbootstarter.service;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/8/2 11:04 AM
 */
public class MingService {
    private String prefix;
    private String suffix;

    public MingService(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }
    public String wrap(String word) {
        return prefix + word + suffix;
    }
}
