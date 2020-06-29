package com.ming.dubboclient.controller;

import com.ming.dubboapi.service.HelloServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-09-05 20:12
 */
@RestController
@Slf4j
public class HelloController {

    //mock 服务降级、熔断 ，只能在客户端处理
    //timeout 超时时间 单位ms
    // check ?

    @Reference(timeout = 1,check = false,cluster = "failfast",mock = "com.ming.dubboclient.controller.HelloErrorServer")
    private HelloServer helloServer;

    @GetMapping("/hello")
    public String hello(){
        String str =  helloServer.helloWord();
        log.info("fwefewfewf");
        return str;
    }
}
