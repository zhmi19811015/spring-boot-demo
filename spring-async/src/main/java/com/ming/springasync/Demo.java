package com.ming.springasync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2020/5/22 9:51 下午
 */
public class Demo {
    public static void main(String[] args){
        ExecutorService executor = Executors.newFixedThreadPool(10);
        executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("听说我被异步调用了");
            }
        });
    }
}
