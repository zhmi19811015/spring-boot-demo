package com.ming.springbootjwt;

import com.ming.springbootjwt.controller.AuthController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-10-10 20:33
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MockMvcTest {
    //mock api 模拟http请求
    private MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(new AuthController()).build();
    }

    @Test
    public void save() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .param("username","zhangming123")
                .param("password","123456"));
    }
}
