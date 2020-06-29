package com.ming.springasync.service;

import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.UUID;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.Future;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/8/2 2:56 PM
 */
@Service
public class AsyncTaskService {
    
    /**
     * 这里进行注解表示是一个异步任务，在执行此方法的时候，会单独开启线程来执行
     *
     * @author  zhangming
     * @date  2019/8/2 2:57 PM
     */
    @Async
    public void asyncTask1(){
        Console.log("methodName:asyncTask1---线程名称:"+Thread.currentThread().getName()+"  "+ UUID.randomUUID().toString());
        try {
            Thread.sleep(new Random().nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Integer test01(){
        return 3;
    }

    /**
     * 异步方法等待
     * @return
     */
    @Async
    public Future test01AsyncWithFuture(){
        return AsyncResult.forValue(this.test01());

    }

    @Async
    public void asyncTask2(){
        Console.log("methodName:asyncTask2---线程名称:"+Thread.currentThread().getName()+"  "+ UUID.randomUUID().toString());
        try {
            Thread.sleep(new Random().nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void test(){
        Console.log("methodName:test---线程名称:"+Thread.currentThread().getName()+"  "+ UUID.randomUUID().toString());
        try {
            Thread.sleep(new Random().nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
