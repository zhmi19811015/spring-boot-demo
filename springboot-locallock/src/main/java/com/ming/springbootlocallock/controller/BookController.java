package com.ming.springbootlocallock.controller;

import cn.hutool.core.lang.Console;
import com.ming.springbootlocallock.annotation.CacheLock;
import com.ming.springbootlocallock.annotation.CacheParam;
import com.ming.springbootlocallock.annotation.LocalLock;
import com.ming.springbootlocallock.dto.UserDto;
import org.springframework.web.bind.annotation.*;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/7/16 3:25 PM
 */
@RestController
@RequestMapping("/books")
public class BookController {
    @LocalLock(key = "userDto.id")
    @PostMapping("/query")
    public String query(UserDto userDto) {
        Console.log(userDto);
        return "success - " + userDto.getId();
    }

    @CacheLock(prefix = "books")
    @GetMapping("/query1")
    public String query1(@CacheParam(name = "token") @RequestParam String token) {
        return "success - " + token;
    }
}
