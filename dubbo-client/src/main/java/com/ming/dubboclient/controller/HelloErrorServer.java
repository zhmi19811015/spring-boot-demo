package com.ming.dubboclient.controller;

import com.ming.dubboapi.service.HelloServer;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-09-11 18:43
 */
public class HelloErrorServer implements HelloServer {
    @Override
    public String helloWord() {
        return "服务器端发送异常，服务被降级";
    }
}
