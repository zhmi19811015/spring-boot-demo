package com.ming.springbootbean;

import com.ming.springbootbean.bean.InitBeanAndDestroyBean;
import com.ming.springbootbean.bean.MyConfig;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootBeanApplicationTests {

//	@Autowired
//	private CmdService linuxCmdService;

	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

	@Test
	public void contextLoads() {
//		String str = linuxCmdService.print();
//		Console.log(str);
		System.out.println(context.getBean(InitBeanAndDestroyBean.class));

	}

	@After
	public void after(){
		context.close();
	}

}
