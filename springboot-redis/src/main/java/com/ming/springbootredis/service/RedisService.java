package com.ming.springbootredis.service;

import cn.hutool.core.lang.Console;
import com.ming.springbootredis.config.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RedisService {
    @Autowired
    private RedisUtil redisUtil;

    public boolean save() throws InterruptedException {
        System.out.println("start save ...");
        long begin = System.currentTimeMillis();
        for (int i = 1;i<=20*10000;i++){
            boolean res= redisUtil.hset("zhangmingmap",String.valueOf(i),"zhangming"+i);
            Console.log("====="+res);
            Thread.sleep(1000);
        }
        long end = System.currentTimeMillis();
        System.out.println("end save:"+(end-begin));
        return true;
    }

    public boolean saveUuid(){
        String uuid = UUID.randomUUID().toString();
        redisUtil.hset("zhangming_andy", uuid,uuid);
        return true;
    }

    public long count(String key){
        return redisUtil.hmget(key).size();//.size(key);
    }

    public void getCursor(String key){
//        //匹配获取键值对，ScanOptions.NONE为获取全部键对，ScanOptions.scanOptions().match("map1").build()     匹配获取键位map1的键值对,不能模糊匹配
//        Cursor<Map.Entry<Object,Object>> cursor = redisTemplate.opsForHash().scan(key, ScanOptions.NONE);
//
//        long begin = System.currentTimeMillis();
//        int i = 0;
//        while (cursor.hasNext()){
//            Map.Entry<Object,Object> entry = cursor.next();
//            System.out.println("通过scan(H key, ScanOptions options)方法获取匹配键值对:" + entry.getKey() + "---->" + entry.getValue());
//            i++;
//        }
//        long end = System.currentTimeMillis();
//        System.out.println("获取完毕:"+(end-begin)+"。总数："+i);
    }
}
