package com.ming.rabbitmqproduction;


import com.ming.rabbitmqproduction.dead.DeadController;
import com.ming.rabbitmqproduction.topic.TopIcSendAck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqProductionApplicationTests extends AbstractTestNGSpringContextTests {

	@Autowired
	private DeadController deadController;
	@Autowired
	private TopIcSendAck topIcSendAck;


	@Test
	public void contextLoads() throws InterruptedException {
		for (int i= 0;i<100;i++){
			deadController.deadLetter("fwfwfwe:"+i);
			Thread.sleep(1000);
		}

	}

	@Test
	public void test1(){
		topIcSendAck.sendMsg();
	}

	@Test(invocationCount = 1010, threadPoolSize = 50)
	public void test2(){
		topIcSendAck.sendUser();
	}

	@Test
	public void testPriority() throws InterruptedException {
		for (int i = 0;i<102;i++){
			topIcSendAck.sendUserPriority();
			Thread.sleep(1000);
		}

	}

}
