package com.ming.springbootjwt.controller;

import cn.hutool.core.lang.Console;
import com.ming.springbootjwt.entity.User;
import com.ming.springbootjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by echisan on 2018/6/23
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public String registerUser(User user){
        Console.log(user);
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        User save = userRepository.save(user);
        return save.toString();
    }
}
