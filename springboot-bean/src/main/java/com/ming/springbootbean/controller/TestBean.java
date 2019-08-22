package com.ming.springbootbean.controller;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/7/12 3:06 PM
 */
@RestController
public class TestBean {
    @PostConstruct
    public void init(){
        System.out.println("PostConstruct初始化加载");
    }
}
