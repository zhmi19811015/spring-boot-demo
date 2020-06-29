基本使用

nacos提供两种方式访问和改变配置信息
1.  open api
2.  sdk



nacos思考问题：
1.  nacos server中的配置是如何存储
2.  客户端是如何获取到远程服务的数据
3.  怎么动态获取

## 自己实现配置中心
- 服务器端的配置保存：数据库
- 服务器端提供API：RPC http
- 数据变化如何通知客户端：push（服务端主动推送） pull(客户端主动去拉取)  
- 客户端如何获得服务的数据
- 刷盘（本地客户端缓存）

## 源码解析
```
 Class<?> driverImplClass = Class.forName("com.alibaba.nacos.client.config.NacosConfigService");
 Constructor constructor = driverImplClass.getConstructor(Properties.class);
 ConfigService vendorImpl = (ConfigService)constructor.newInstance(properties);
```
下面代码2件事：
-   创建一个httpAgent代理->发起http请求
-   创建clientWorker -> 异步线程（定时线程）
```
public NacosConfigService(Properties properties) throws NacosException {
        String encodeTmp = properties.getProperty("encode");
        if (StringUtils.isBlank(encodeTmp)) {
            this.encode = "UTF-8";
        } else {
            this.encode = encodeTmp.trim();
        }

        this.initNamespace(properties);
        // agent代理
        // http的方式发起请求
        // 装饰模式
        this.agent = new MetricsHttpAgent(new ServerHttpAgent(properties));
        this.agent.start();
        this.worker = new ClientWorker(this.agent, this.configFilterChainManager, properties);
    }
```
每10ms判断是否变化
```
this.executor.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                try {
                    ClientWorker.this.checkConfigInfo();
                } catch (Throwable var2) {
                    ClientWorker.LOGGER.error("[" + agent.getName() + "] [sub-check] rotate check error", var2);
                }

            }
        }, 1L, 10L, TimeUnit.MILLISECONDS);
```
数据量大，分批
```
public void checkConfigInfo() {
        //分任务
        int listenerSize = ((Map)this.cacheMap.get()).size();
        //向上取整数为批数
        int longingTaskCount = (int)Math.ceil((double)listenerSize / ParamUtil.getPerTaskConfigSize());
        if ((double)longingTaskCount > this.currentLongingTaskCount) {
            for(int i = (int)this.currentLongingTaskCount; i < longingTaskCount; ++i) {
                //判断任务是否在执行
                this.executorService.execute(new ClientWorker.LongPollingRunnable(i));
            }

            this.currentLongingTaskCount = (double)longingTaskCount;
        }

    }
```