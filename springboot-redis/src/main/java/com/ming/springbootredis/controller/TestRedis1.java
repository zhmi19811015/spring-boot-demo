package com.ming.springbootredis.controller;

import cn.hutool.core.lang.Console;
import com.ming.springbootredis.config.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/7/12 4:34 PM
 */
@RestController
@Slf4j
@RequestMapping("redis1")
public class TestRedis1 {
    @Autowired
    private RedisUtil redisUtil;
    private final static String REDIS_KEY_DEL_GET = "zhangming_del_get";

    @GetMapping("/set")
    public void set() throws InterruptedException {
        while (true){

            redisUtil.del(REDIS_KEY_DEL_GET);
            boolean res = redisUtil.set(REDIS_KEY_DEL_GET,"ZHANGMINGJIJI");
            Console.log(Thread.currentThread().getName()+"======="+res);
            Thread.sleep(300);
        }
    }

    @GetMapping("/set1")
    public void set1() throws InterruptedException {
        redisUtil.del(REDIS_KEY_DEL_GET);
        boolean res = redisUtil.set(REDIS_KEY_DEL_GET,"ZHANGMINGJIJI");
        Console.log(Thread.currentThread().getName()+"======="+res);
    }

    @GetMapping("/get")
    public void get() throws InterruptedException {
        Object obj = null;
        Thread.sleep(300);
        while (true){
            obj = redisUtil.get(REDIS_KEY_DEL_GET);
            Console.log(Thread.currentThread().getName()+"==="+obj);
            if (obj == null){
                throw new RuntimeException("redis没有获取到值");
            }
            Thread.sleep(300);
        }
    }
}

