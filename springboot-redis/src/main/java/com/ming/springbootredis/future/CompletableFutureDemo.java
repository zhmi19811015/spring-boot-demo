package com.ming.springbootredis.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Future 线程阻塞
 * 调用get方法时，处在阻塞状态，等待线程返回结果
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws Exception{
        System.out.println("begin");
        Future<Double> futurePrice =getPriceAsync("ss");
        System.out.println("doSomething");
        System.out.println(futurePrice.get());
        System.out.println("end");
    }

    public static Future<Double> getPriceAsync(String product){
        //创建CompletableFuture对象
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();

        new Thread (()->{
            try {
                //在另一个线程中执行计算
                double price = getPrice(product);
                //需要长时间计算的任务结束并得出结果时，设置future的返回值
                futurePrice.complete(price);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        return futurePrice;
    }

    public static double getPrice(String product) throws InterruptedException {
        //查询商品的数据库，或链接其他外部服务获取折扣
        Thread.sleep(1000);
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }
}
