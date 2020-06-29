## dubbo-2.7新功能
1.  配置文件放入配置中心。支持 apollo、nacos、zookeeper(dubbo-admin)
```
#连接配置中心
dubbo.config-center.address=zookeeper://134.175.89.119:2181
dubbo.config-center.app-name=dubbo-server

```

如果使用配置中心，理论上下面的配置就不需要，但dubbo为了保证高可用，下面也必须在application配置
```
dubbo.application.name=dubbo-server
dubbo.registry.address=zookeeper://134.175.89.119:2181
```

2.  元数据中心。作用zookeeper节点的数据简化
```
dubbo.metadata-report.address=zookeeper://134.175.89.119:2181
dubbo.registry.simplified=true
```
zookeeper分为两个节点存储，客户端将分两次查询，相比较老版本就是一个拆分
```
地址节点：dubbo://ip:port/com.xxxx.类名
元数据节点
```