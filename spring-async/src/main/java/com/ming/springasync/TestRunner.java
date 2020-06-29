package com.ming.springasync;

import com.ming.springasync.service.AsyncTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/8/2 3:07 PM
 */
@Component
public class TestRunner implements CommandLineRunner {

    @Autowired
    private AsyncTaskService asyncTaskService;

    @Override
    public void run(String... args) throws Exception {
//        Console.log("TestRunner:"+Thread.currentThread().getName()+"  "+ UUID.randomUUID().toString());
//        for (int i = 0; i < 10; i++) {
//            asyncTaskService.asyncTask1(); // 执行异步任务
//            Console.log("\n");
//            asyncTaskService.asyncTask2();
//            Console.log("\n");
//            asyncTaskService.test();
//            Console.log("\n");
//        }
    }
}
