package com.ming.springbootlimiter;

import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpUtil;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-11-25 11:52
 */
public class Test {
    public static void main(String[] args) {
        for (int i = 1;i<=20;i++){
            String str = HttpUtil.get("http://localhost:8044/test");
            Console.log("访问次数:"+i+"。返回值:"+str);
        }

    }
}
