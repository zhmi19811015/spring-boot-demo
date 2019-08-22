package com.ming.springbootredis;

import cn.hutool.core.lang.Console;
import com.ming.springbootredis.config.MyRedisTemplate;
import com.ming.springbootredis.service.RedisLock;
import com.ming.springbootredis.service.RedisService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisApplicationTests extends AbstractTestNGSpringContextTests {

	@Autowired
	private RedisService redisService;

	@Autowired
	private MyRedisTemplate redisTemplate;

	@Before
	public void before(){
		System.out.println("before");
	}

	@After
	public void after(){
		System.out.println("after");
	}

	@Test
	public void saveRedis() throws InterruptedException {
		redisService.save();
	}

	//@org.testng.annotations.Test(invocationCount = 10000, threadPoolSize = 100)
	@Test
	public void saverRedisUuid(){
		long begin = System.currentTimeMillis();
		redisService.saveUuid();

		Console.log("time:"+(System.currentTimeMillis()-begin));
	}

	@Test
	public void getCount(){
		Console.log(redisService.count("zhangmingmap"));
	}

	@Test
	public void getMapValue(){
		redisService.getCursor("zhangmingmap");
	}

	@org.testng.annotations.Test(invocationCount = 100, threadPoolSize = 50)
	public void redisLock(){
		RedisLock lock = new RedisLock(redisTemplate, "zhangmingtest", 10000, 20000);
		try {
			if(lock.lock()) {
				//需要加锁的代码
				Console.log("获取到锁，执行业务流程:"+Thread.currentThread().getId()+".time="+System.currentTimeMillis());
				Thread.sleep(200);
			}else{
				System.err.println("没有获取到锁："+Thread.currentThread().getId()+".time="+System.currentTimeMillis());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			//为了让分布式锁的算法更稳键些，持有锁的客户端在解锁之前应该再检查一次自己的锁是否已经超时，再去做DEL操作，因为可能客户端因为某个耗时的操作而挂起，
			//操作完的时候锁因为超时已经被别人获得，这时就不必解锁了。 ————这里没有做
			lock.unlock();
		}
	}


}
