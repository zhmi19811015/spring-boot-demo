package com.ming.springbootbean;

import cn.hutool.core.lang.Console;
import com.ming.springbootbean.service.CmdService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootBeanApplicationTests {

	@Autowired
	private CmdService linuxCmdService;

	@Test
	public void contextLoads() {
		String str = linuxCmdService.print();
		Console.log(str);
	}

}
