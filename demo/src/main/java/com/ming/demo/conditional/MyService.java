package com.ming.demo.conditional;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

/**
 * @Description: 类作用描述
 * @Author: zhangming
 * @CreateDate: 2019/4/13 10:36 PM
 * @Version: 1.0
 */
//只有当 ConditionalTest 类的条件成立时才会创建 MyService 这个 bean。
@Conditional(ConditionalTest.class)
@Service
public class MyService {
    public String test(){
        return "zhangming";
    }
}
