package com.ming.springbootjwt;

import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpUtil;
import com.ming.springbootjwt.entity.User;

import java.util.HashMap;
import java.util.Map;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/5/7 4:58 PM
 */
public class Test {
    private static final String URL = "http://localhost:8917/";

    public static void main(String[] args) {
        test1();
    }

    public static void test1(){
        User user = new User();
        user.setPassword("123456");
        user.setUsername("andyzhang");
        Map<String,Object> mapParams = new HashMap<>();
        mapParams.put("password","12346");
        mapParams.put("username","andyzhang");
        String result = httpPost(URL+"auth/register",mapParams);
        System.out.println(result);
    }

    private static String httpPost(String url,Map<String,Object> mapParams){
        Console.log(url);
        return HttpUtil.post(url,mapParams);
    }
}
