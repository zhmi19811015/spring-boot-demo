package com.ming.kafkaproducer;

import cn.hutool.core.util.IdUtil;
import com.ming.kafkaproducer.controller.KafkaController;
import com.ming.kafkaproducer.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaProducerApplicationTests extends AbstractTestNGSpringContextTests {

	@Autowired
	private KafkaController kafkaController;

	@org.testng.annotations.Test(invocationCount = 1000, threadPoolSize = 100)
	public void test1() {
		kafkaController.send("zhangminnggg");


	}

	@org.testng.annotations.Test(invocationCount = 1000, threadPoolSize = 100)
	public void test2() throws Exception {
		User user = new User();
        user.setId(IdUtil.simpleUUID());
        user.setUserName("zhangming");
        user.setSex(2);
        kafkaController.sendSyncUser(user);
        //上文提及了发送消息的时候需要休眠一下，
        // 否则发送时间较长的时候会导致进程提前关闭导致无法调用回调时间。主要是因为KafkaTemplate发送消息是采取异步方式发送的
        Thread.sleep(1000);

	}

}
