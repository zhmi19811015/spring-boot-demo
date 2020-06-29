package com.ming.springbootfastdfs;

import cn.hutool.core.lang.Console;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootFastdfsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SpringbootFastdfsApplicationTests {

	@Test
	public void contextLoads() {
		Map<String,Object> param = new HashMap<>();
		String fielStr = "/Users/zhangiming/Desktop/123.png";
		Console.log(new File(fielStr).exists());
	}

}
