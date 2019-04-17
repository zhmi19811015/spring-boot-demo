package com.ming.springbootredis.future;

import java.util.Random;

public class Shop {
    public double getPrice(String product) throws InterruptedException {
        //查询商品的数据库，或链接其他外部服务获取折扣
        Thread.sleep(1000);
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }
}
