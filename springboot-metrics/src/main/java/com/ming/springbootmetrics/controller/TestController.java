package com.ming.springbootmetrics.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/7/30 7:49 PM
 */
@RestController
@RequestMapping("test")
public class TestController {
    private static final Counter EXCEPTION_COUNTER = Metrics.counter("test.total");

    @GetMapping("/test1")
    public String test1(String name) {
        EXCEPTION_COUNTER.increment();
        System.out.println("++++++" + EXCEPTION_COUNTER.count());
        return name;
    }
}
