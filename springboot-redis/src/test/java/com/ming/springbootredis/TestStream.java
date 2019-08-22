package com.ming.springbootredis;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.cron.CronUtil;
import cn.hutool.cron.task.Task;
import com.alibaba.fastjson.JSON;
import com.ming.springbootredis.config.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestStream {

    @Autowired
    private RedisUtil redisUtil;

    public static void main(String[] args) {
        Stream.of(0,1,2,3,4,5,6,7,8,9)
                .filter(v->v%2==0)
                .forEach(n->print(n));


    }

    String SORT_KEY = "zhangming-sort";
    String MAP_KEY = "zhangming-map";
    @Test
    public void redisMapZsetDel() throws Exception{
        CronUtil.schedule("*/30 * * * * *", new Task() {
            @Override
            public void execute() {
                System.out.println("定时器");
                removeExpireMapKey();
            }
        });
        CronUtil.setMatchSecond(true);
        CronUtil.start();

        int i = 0;
        RedisMapPojo redisMapPojo = null;
        Date startDate = DateUtil.parse("2019-06-20 00:00:00");
        Date endDate = DateUtil.date();
        Date birthDate = null;
        while (i<100000){
            redisMapPojo = new RedisMapPojo();
            redisMapPojo.setUserId(IdUtil.randomUUID());
            redisMapPojo.setUserName("zhangming:"+i);
            birthDate = com.ming.springbootredis.util.DateUtil.randomDate(startDate,endDate);
            redisMapPojo.setBirthDate(birthDate);

            //保存map
            redisUtil.hset(MAP_KEY,redisMapPojo.getUserName(), JSON.toJSONString(redisMapPojo));
            //保存zset 主要用户删除
            redisUtil.zSet(SORT_KEY,redisMapPojo.getUserName(),birthDate.getTime());

            System.out.println("suecces:"+i);
            i++;
            Thread.sleep(2000);
        }
    }


    private void removeExpireMapKey(){
        boolean isHas = redisUtil.hasKey(SORT_KEY);
        if (!isHas){
            return;
        }
        Date endDate = DateUtil.date();
        Date startDate = DateUtil.offsetHour(endDate,-8);
        Set set = redisUtil.zRangeByScore(SORT_KEY,startDate.getTime(),endDate.getTime());

        if(set.size() >0){
            List<String> listStr = new ArrayList<>();
            Iterator<String> it = set.iterator();
            while (it.hasNext()) {


                String key = it.next();
                //删除map_key
                redisUtil.hdel(MAP_KEY,key);
                System.out.println("删除："+key);
                listStr.add(key);
            }
            //删除自己
            redisUtil.zRemove(SORT_KEY,listStr.toArray());

        }

    }

    @Test
    public void thenApply() {
        String result = CompletableFuture.supplyAsync(() ->"hello").thenApply(s -> s + " world").join();
        System.out.println(result);
    }

    public static void print(Object o){
        System.out.println("线程:"+Thread.currentThread().getName()+".数据:"+o);
    }
}
