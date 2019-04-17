/**
 * @Project spring-boot-demo
 * @Package com.ming.component
 * @Description: TODO
 * @author zhangming
 * @date 2019/2/28 15:06
 * @version V1.0
 */
package com.ming.springbootfastdfs.component;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class OrderRunner2 implements CommandLineRunner {
    @Override
    public void run(String... strings) throws Exception {
        System.out.println("OrderRunner2 run");
    }
}
