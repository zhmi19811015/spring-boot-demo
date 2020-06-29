package com.ming.rabbitmqpdemo;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.ming.rabbitmqpdemo.util.RabbitUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.ming.rabbitmqpdemo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//testng extends AbstractTestNGSpringContextTests
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqDemoApplicationTests {
	@Autowired
	private RabbitUtil rabbitUtil;
//	@Autowired
//	public void setRabbitSendUtil(RabbitUtil rabbitSendUtil) {
//		this.rabbitUtil = rabbitSendUtil;
//	}

	@Test
	public void test1(){
		User user = new User();
		int i = 1;
		while (true){
			user.setUserName("zhangming_"+i);
			user.setSex(i);
			user.setBirDate(DateUtil.now());
			user.setId(IdUtil.simpleUUID());
			rabbitUtil.sendMessage("ming-routingkey-user", JSONObject.toJSONString(user));
			i++;
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	@Test
	public void contextLoads() throws InterruptedException {



	}


}
