package com.ming.rabbitmqpdemo.test;

import org.springframework.stereotype.Component;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-12-06 13:40
 */
@Component
public class Test2 implements ITest {
    @Override
    public void test() {
        System.out.println("test2");
    }
}
