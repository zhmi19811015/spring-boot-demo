package com.ming.springbootredis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestStream {
    public static void main(String[] args) {
        Stream.of(0,1,2,3,4,5,6,7,8,9)
                .filter(v->v%2==0)
                .forEach(n->print(n));


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
