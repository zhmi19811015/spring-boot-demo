package com.ming.rabbitmqpdemo;


import com.ming.rabbitmqpdemo.dead.DeadController;
import com.ming.rabbitmqpdemo.topic.TopIcSendAck;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//testng extends AbstractTestNGSpringContextTests
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqProductionApplicationTests  {

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

	@Test//(invocationCount = 10, threadPoolSize = 10)
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
