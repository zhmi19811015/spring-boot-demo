package com.ming.springbootbase.controller;

import com.ming.springbootbase.web.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2020/3/19 8:31 下午
 */
@RestController
@RequestMapping("/api/v1/users")
public class TestController {
    @GetMapping("/test")
    public R list() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        return R.ok().data("itms", list).message("测试");
    }
}
