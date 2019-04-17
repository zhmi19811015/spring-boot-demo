package com.ming.springbootredis;

import com.ming.springbootredis.service.RedisService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisApplicationTests {

	@Autowired
	private RedisService redisService;

	@Before
	public void before(){
		System.out.println("before");
	}

	@After
	public void after(){
		System.out.println("after");
	}

	@Test
	public void saveRedis(){
		redisService.save();
	}

	@Test
	public void getCount(){
		System.out.println(redisService.count("zhangmingmap"));
	}

	@Test
	public void getMapValue(){
		redisService.getCursor("zhangmingmap");
	}
}
