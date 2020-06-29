package com.ming.springsecurityoauth2.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-08-27 13:35
 */
@RestController
@RequestMapping("/api")
public class TestController {
    @RequestMapping("/hello/{id}")
    public String helloOath2(@PathVariable long id) {
        System.out.println("请求的ID编码为：" + id);
        return "helloOath2";
    }
}
