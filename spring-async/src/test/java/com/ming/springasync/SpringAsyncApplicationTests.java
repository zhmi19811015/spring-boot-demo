package com.ming.springasync;

import com.ming.springasync.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.SuccessCallback;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringAsyncApplication.class)
public class SpringAsyncApplicationTests {

	@Autowired
	private DemoService demoService;

	//同步执行
	@Test
	public void task01() {
		long now = System.currentTimeMillis();
		log.info("[task01][开始执行]");

		demoService.execute01();
		demoService.execute02();
		//
		log.info("同步执行[task01][结束执行，消耗时长 {} 毫秒]", System.currentTimeMillis() - now);
	}

	@Test
	public void task02() {
		long now = System.currentTimeMillis();
		log.info("[task02][开始执行]");

		demoService.execute01Async();
		demoService.execute02Async();

		log.info("异步执行[task02][结束执行，消耗时长 {} 毫秒]", System.currentTimeMillis() - now);
	}

	@Test
	public void task03() throws ExecutionException, InterruptedException {
		long now = System.currentTimeMillis();
		log.info("[task03][开始执行]");

		// <1> 执行任务
		Future<Integer> execute01Result = demoService.execute01AsyncWithFuture();
		Future<Integer> execute02Result = demoService.execute02AsyncWithFuture();
		// <2> 阻塞等待结果
		execute01Result.get();
		execute02Result.get();

		log.info("异步执行等待返回[task03][结束执行，消耗时长 {} 毫秒]", System.currentTimeMillis() - now);
	}


	@Test
	public void task04() throws ExecutionException, InterruptedException{
		long now = System.currentTimeMillis();
		log.info("[task04][开始执行]");

		//执行任务
		ListenableFuture<Integer> execute01Result = demoService.execute01AsyncWithListenableFuture();
		log.info("[task04][execute01Result 的类型是：({})]",execute01Result.getClass().getSimpleName());
		execute01Result.addCallback(new SuccessCallback<Integer>() {
			@Override
			public void onSuccess(Integer result) {
				log.info("[onSuccess][result: {}]", result);
			}
		}, new FailureCallback() {
			@Override
			public void onFailure(Throwable ex) {
				log.info("[onFailure][发生异常]", ex);
			}
		});

		//增加成功和失败的统一回调
		execute01Result.addCallback(new ListenableFutureCallback<Integer>() {
			@Override
			public void onFailure(Throwable ex) {
				log.info("[onFailure][发生异常]", ex);

			}

			@Override
			public void onSuccess(Integer result) {
				log.info("[onSuccess][result: {}]", result);
			}
		});

		// <3> 阻塞等待结果
		execute01Result.get();
		log.info("[task04][结束执行，消耗时长 {} 毫秒]", System.currentTimeMillis() - now);

	}

	@Test
	public void testZhaoDaoNvPengYou() throws InterruptedException {
		demoService.zhaoDaoNvPengYou(1, 2);

		// sleep 1 秒，保证异步调用的执行
		Thread.sleep(1000);
	}

}
