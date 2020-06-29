package com.ming.springbootredis.config;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.ming.springbootredis.pojo.UserPojo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2020/5/27 7:11 下午
 */
@Component
@Slf4j
public class TestAutoRun implements CommandLineRunner {
    @Autowired
    private RedisUtil redisUtil;


    @Override
    public void run(String... args) throws Exception {
        UserPojo userPojo = new UserPojo();
        while (true) {
            String uid = IdUtil.simpleUUID();
            userPojo.setId(uid);
            userPojo.setId("名" + uid);
            redisUtil.hset("zhangming_key", uid, JSON.toJSONString(userPojo) );
            Thread.sleep(10);
            //log.info("保存redis==" + uid);
        }
    }
}
