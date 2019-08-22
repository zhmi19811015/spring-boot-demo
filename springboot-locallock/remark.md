本地锁

应用场景：
1、表单重复提交

**Lock 拦截器（AOP）**

首先通过 CacheBuilder.newBuilder() 构建出缓存对象，设置好过期时间；
其目的就是为了防止因程序崩溃锁得不到释放（当然如果单机这种方式程序都炸了，锁早没了；但这不妨碍我们写好点）

在具体的 interceptor() 方法上采用的是 Around（环绕增强） ，所有带 LocalLock 注解的都将被切面处理；

如果想更为灵活，key 的生成规则可以定义成接口形式（可以参考：org.springframework.cache.interceptor.KeyGenerator）


**单机版中我们用的是Guava Cache，但是这玩意存在集群的时候就凉了，所以我们还是要借助类似Redis、ZooKeeper 之类的中间件实现分布式锁。**