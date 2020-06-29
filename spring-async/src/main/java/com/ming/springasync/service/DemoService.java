package com.ming.springasync.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Future;

/**
 * @Async异步执行
 * Future 异步执行等待返回
 *
 * @author zhangming
 * @version 1.0
 * @date 2020/5/23 9:11 上午
 */
@Service
@Slf4j
public class DemoService {

    @Async
    public Integer zhaoDaoNvPengYou(Integer a, Integer b) {
        throw new RuntimeException("程序员不需要女朋友");
    }

    //异步回调
    @Async
    public ListenableFuture<Integer> execute01AsyncWithListenableFuture() {
        try {
            return AsyncResult.forValue(this.execute02());
        } catch (Throwable ex) {
            return AsyncResult.forExecutionException(ex);
        }
    }

    @Async
    public Future<Integer> execute01AsyncWithFuture() {
        return AsyncResult.forValue(this.execute01());
    }

    @Async
    public Future<Integer> execute02AsyncWithFuture() {
        return AsyncResult.forValue(this.execute02());
    }

    @Async
    public Integer execute01Async() {
        return this.execute01();
    }

    @Async
    public Integer execute02Async() {
        return this.execute02();
    }

    public Integer execute01() {
        log.info("[execute01]");
        sleep(10);
        return 1;
    }

    public Integer execute02() {
        log.info("[execute02]");
        sleep(5);
        return 2;
    }

    private static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
