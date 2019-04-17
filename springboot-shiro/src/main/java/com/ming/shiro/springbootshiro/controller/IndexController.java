package com.ming.shiro.springbootshiro.controller;

import com.ming.shiro.springbootshiro.result.Result;
import com.ming.shiro.springbootshiro.result.ResultGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/helloworld")
    public Result helloWorld() {

        return ResultGenerator.genSuccessResult("helloworld");
    }
}
