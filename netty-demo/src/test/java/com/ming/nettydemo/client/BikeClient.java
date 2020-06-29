package com.ming.nettydemo.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.util.concurrent.DefaultThreadFactory;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/6/17 12:00 PM
 */
public class BikeClient implements Runnable{
//    final String IP = "134.175.89.119";
    final String IP = "127.0.0.1";
    private String firstMsg;
    private boolean isTimeOut;
    public BikeClient(String firstMsg,boolean isTimeOut){
        this.firstMsg = firstMsg;
        this.isTimeOut = isTimeOut;
    }

    @Override
    public void run() {

        EventLoopGroup group = new NioEventLoopGroup(1,new DefaultThreadFactory("group",true));
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast("byteArrayDecoder", new ByteArrayDecoder());
                            p.addLast("byteArrayEncoder", new ByteArrayEncoder());
//                            p.addLast(new StringDecoder());
//                            p.addLast(new StringEncoder());
                            //p.addLast(new LoggingHandler(LogLevel.INFO));
                            p.addLast(new BikeClientHandler());
                        }
                    });

            // Start the client.
            ChannelFuture f = b.connect(IP, 52018).sync();
            //发送认证信息

            f.channel().writeAndFlush(ByteUtilTo.toBytes(firstMsg));
            System.out.println(Thread.currentThread().getName()+"=="+System.currentTimeMillis());
            Thread.sleep(500);
            while (true){
                f.channel().writeAndFlush(ByteUtilTo.toBytes("ecbe0014010101000075002a7e1f014ba81412020a0d10116568"));
                if (isTimeOut){
                    Thread.sleep(6000);
                }else{
                    Thread.sleep(2000);
                }

            }
            // Wait until the connection is closed.
            //f.channel().closeFuture().sync();
        } catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            // Shut down the event loop to terminate all threads.
            group.shutdownGracefully();
        }
    }
}
