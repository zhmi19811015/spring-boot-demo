package com.ming.dubboserver.server;

import com.ming.dubboapi.service.HelloServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-09-05 20:06
 */
@Slf4j
@Service(loadbalance = "random")
public class HelloServiceImpl implements HelloServer {
    @Override
    public String helloWord() {
        log.info("hello word");
        return "hello word";
    }
}
