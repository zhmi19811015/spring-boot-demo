package com.ming.nettydemo.client;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/6/17 1:36 PM
 */

public class ClientTest {

    public static void main(String[] args) {
        int size = 10;
        CountDownLatch countDownLatch = new CountDownLatch(size);
        ExecutorService executorService = Executors.newFixedThreadPool(size);
        boolean isTime = true;
        for (int i =1;i<=size;i++){
            if (i % 2 ==0){
                isTime =false;
            }else{
                isTime =true;
            }
            executorService.execute(new BikeClient(setMsg(i),isTime));
            countDownLatch.countDown();;

        }

        System.out.println("等待");

        try {
           countDownLatch.await();
            System.out.println("执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//         long MIN_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(1);
//        System.out.println(MIN_TIMEOUT_NANOS);
    }

    //ec01123410000000000268
    private static String setMsg(int i ){
        if (i<10){
            return "ec01123410000000000"+i+"68";
        }
        if (i<100){
            return "ec0112341000000000"+i+"68";
        }
        return "ec011234100000000"+i+"68";
    }




}
