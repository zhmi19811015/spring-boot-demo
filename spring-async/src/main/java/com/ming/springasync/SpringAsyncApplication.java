package com.ming.springasync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
//启用异步任务
@EnableAsync(proxyTargetClass = true)
public class SpringAsyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAsyncApplication.class, args);
	}

	@Bean
	public Executor createExecutor(){
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(5);
		executor.setMaxPoolSize(10);
		executor.setQueueCapacity(25);
		//当提交的任务个数大于QueueCapacity，就需要设置该参数，但spring提供的都不太满足业务场景，可以自定义一个，也可以注意不要超过QueueCapacity即可
		//taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		executor.setWaitForTasksToCompleteOnShutdown(true);
		executor.setAwaitTerminationSeconds(10);
		executor.setThreadNamePrefix("ES-IMOPRT-");
		executor.initialize();
		return executor;
	}

}
