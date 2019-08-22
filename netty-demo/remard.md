EventLoopGroup bossGroup = new NioEventLoopGroup(1); 
它代表着只起一个线程去处理客户端请求。
如果不写呢？Netty会分配多少个线程数呢？
我们看一下NioEventLoopGroup(),可以看到有个构造参数int nThreads，它代表着你指定的线程数，当然我们没有指定，那么就会执行NioEventLoopGroup的父类MultithreadEventLoopGroup构造函数
// 如果nThreads为0，线程数量就为CPU的超核数*2
protected MultithreadEventLoopGroup(int nThreads, Executor executor, Object... args) {
        super(nThreads == 0 ? DEFAULT_EVENT_LOOP_THREADS : nThreads, executor, args);
    }
    
我们知道，在一个应用中，如果cpu计算的时间为Tcpu，io操作的时间为Tio，系统的cpu核数为Ncpu，线程个数为Nthread， 
那么理论上线程个数满足Nthread = (1+Tio/Tcpu)*Ncpu，应用的性能达到最优