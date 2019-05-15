//package com.ming.nettydemo;
//
//import io.netty.bootstrap.Bootstrap;
//import io.netty.channel.*;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioSocketChannel;
//import io.netty.handler.codec.serialization.ClassResolvers;
//import io.netty.handler.codec.serialization.ObjectDecoder;
//import io.netty.handler.codec.serialization.ObjectEncoder;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
//
///**
// * 类作用描述
// *
// * @author zhangming
// * @version 1.0
// * @date 2019/5/9 10:08 PM
// */
//@SpringBootTest
//public class NettyClientTest extends AbstractTestNGSpringContextTests {
//    @org.testng.annotations.Test(invocationCount = 500, threadPoolSize = 500)
//    public void test() {
//        String ip = "134.175.89.109";
//        int port = 52018;
//        //设置一个多线程循环器
//        EventLoopGroup boosGroup = new NioEventLoopGroup();
//        try{
//            Bootstrap bootstrap = new Bootstrap();
//            bootstrap.group(boosGroup);
//            bootstrap.channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY,true).handler(new ChannelInitializer<SocketChannel>(){
//                @Override
//                protected void initChannel(SocketChannel socketChannel) throws Exception {
//                    //业务代码
//                    ChannelPipeline pipeline = socketChannel.pipeline();
////                        //关注handler
////                            pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4));
////                            pipeline.addLast(new LengthFieldPrepender(4));
//                    pipeline.addLast(new ObjectEncoder());
//                    pipeline.addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
//                    //业务handler
//                   // pipeline.addLast(rpcProxyHandler);
//                }
//            });
//
//            //连接服务器
//            ChannelFuture future = bootstrap.connect(ip,port).sync();
//            String str = "ec01123410000000000268";
//
//            //发送注册信息
//            future.channel().writeAndFlush(str.getBytes());
//            str = "ecbe0014010101000075002a7e1f014ba81412020a0d10116568";
//            for (int i = 0;i<1000;i++){
////                //连接服务器
////                ChannelFuture future = bootstrap.connect(ip,port).sync();
//                //将封装的对象写
//                future.channel().writeAndFlush(str.getBytes());
//                System.out.println("===="+i+"===="+Thread.currentThread().getId());
//                //future.channel().closeFuture().sync();
//            }
//            //future.channel().closeFuture().sync();
//
//
//
//            //return rpcProxyHandler.getResponese();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        finally {
//            boosGroup.shutdownGracefully();
//            System.out.println("cleint thread close");
//        }
//        //return null;
//
//
//
////        //启动附注类
////        Bootstrap bootstrap = new Bootstrap();
////        bootstrap.group(workerGroup);
////        //指定所使用的NIO传输channel
////        bootstrap.channel(NioSocketChannel.class);
////        //指定客户端初始化处理
////        bootstrap.handler(new ClientIniterHandler());
////        try {
////            //连接服务
////            Channel channel = bootstrap.connect(ip, port).sync().channel();
////            while (true) {
////                //向服务端发送内容
////                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
////                String content = reader.readLine();
////                if (StringUtils.isNotEmpty(content)) {
////                    if (StringUtils.equalsIgnoreCase(content, "q")) {
////                        System.exit(1);
////                    }
////                    channel.writeAndFlush(content);
////                }
////            }
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////            System.exit(1);
////        } finally {
////            workerGroup.shutdownGracefully();
////        }
//    }
//}
