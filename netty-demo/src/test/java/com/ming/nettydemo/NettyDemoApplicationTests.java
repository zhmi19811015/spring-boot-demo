package com.ming.nettydemo;

import org.junit.Test;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.time.LocalTime;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class NettyDemoApplicationTests extends AbstractTestNGSpringContextTests {
	String a = LocalTime.now().toString();
	@Test(timeout = 30000)
	public void contextLoads() {
		System.out.println(a);
		System.out.println(LocalTime.now().toString());
	}

}
