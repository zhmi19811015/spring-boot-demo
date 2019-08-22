**分布式限流**

单机版中我们了解到 AtomicInteger、RateLimiter、Semaphore 这几种解决方案，
但它们也仅仅是单机的解决手段，在集群环境下就透心凉了，后面又讲述了 Nginx 的限流手段，
可它又属于网关层面的策略之一，并不能解决所有问题。例如供短信接口，你无法保证消费方是否会做好限流控制，
所以自己在应用层实现限流还是很有必要的。