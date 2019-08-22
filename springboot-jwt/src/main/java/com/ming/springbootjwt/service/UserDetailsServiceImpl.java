package com.ming.springbootjwt.service;

import com.ming.springbootjwt.entity.JwtUser;
import com.ming.springbootjwt.entity.User;
import com.ming.springbootjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * springSecurity需要实现UserDetailsService接口供权限框架调用
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 根据用户名去获取用户
     * 
     * @param s 1
     * @return  org.springframework.security.core.userdetails.UserDetails
     * @author  zhangming
     * @date  2019/7/15 3:05 PM
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        return new JwtUser(user);
    }

}
