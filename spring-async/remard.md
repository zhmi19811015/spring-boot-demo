@EnableAsync与@Async注解
我们在使用多线程的时候，往往需要创建Thread类，或者实现Runnable接口，如果要使用到线程池，我们还需要创建
Executors，在使用spring中，已经给我们做了很好的支持。只要@EnableAsync就可以使用多线程。使用@Async就可以
定义一个线程任务。通过spring给我们提供ThreadPoolTaskExecutor就可以使用线程池

```
# Spring 执行器配置，对应 TaskExecutionProperties 配置类。对于 Spring 异步任务，会使用该执行器。
# 线程池的线程名的前缀。默认为 task- ，建议根据自己应用来设置
spring.task.execution.thread-name-prefix=task-
# 核心线程数，线程池创建时候初始化的线程数。默认为 8
spring.task.execution.pool.core-size=8
# 最大线程数，线程池最大的线程数，只有在缓冲队列满了之后，才会申请超过核心线程数的线程。默认为 Integer.MAX_VALUE
spring.task.execution.pool.max-size=20
# 允许线程的空闲时间，当超过了核心线程之外的线程，在空闲时间到达之后会被销毁。默认为 60 秒
spring.task.execution.pool.keep-alive=60s
# 缓冲队列大小，用来缓冲执行任务的队列的大小。默认为 Integer.MAX_VALUE 。
spring.task.execution.pool.queue-capacity=200
# 是否允许核心线程超时，即开启线程池的动态增长和缩小。默认为 true 。
spring.task.execution.pool.allow-core-thread-timeout=true
# 应用关闭时，是否等待定时任务执行完成。默认为 false ，建议设置为 true
spring.task.execution.shutdown.await-termination=true
# 等待任务完成的最大时长，单位为秒。默认为 0 ，根据自己应用来设置
spring.task.execution.shutdown.await-termination-period=60
```

* 在 spring.task.execution 配置项，Spring Task 调度任务的配置，对应 TaskExecutionProperties 配置类。
* Spring Boot TaskExecutionAutoConfiguration 自动化配置类，实现 Spring Task 的自动配置，创建 ThreadPoolTaskExecutor 基于线程池的任务执行器。本质上，ThreadPoolTaskExecutor 是基于 ThreadPoolExecutor 的封装，主要增加提交任务，返回 ListenableFuture 对象的功能。

注意，spring.task.execution.shutdown 配置项，是为了实现 Spring Task 异步任务的优雅关闭。我们想象一下，如果异步任务在执行的过程中，如果应用开始关闭，把异步任务需要使用到的 Spring Bean 进行销毁，例如说数据库连接池，那么此时异步任务还在执行中，一旦需要访问数据库，可能会导致报错。

* 所以，通过配置 await-termination = true ，实现应用关闭时，等待异步任务执行完成。这样，应用在关闭的时，Spring 会优先等待 ThreadPoolTaskScheduler 执行完任务之后，再开始 Spring Bean 的销毁。
* 同时，又考虑到我们不可能无限等待异步任务全部执行结束，因此可以配置 await-termination-period = 60 ，等待任务完成的最大时长，单位为秒。具体设置多少的等待时长，可以根据自己应用的需要。

