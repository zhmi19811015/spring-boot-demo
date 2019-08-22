package com.ming.nettydemo.netty;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class EbikeServer implements CommandLineRunner{

	@Override
	public void run(String... args) throws Exception {
		startServer(null);
	}

	public static void main(String[] args) {
		new EbikeServer().startServer(null);
	}

	
	/**
	 * start server
	 * @param port
	 */
	public void startServer(Integer port) {
		if (null == port) {
			port = 52018;
		}
		// boss线程池 接受传入连接
		//EventLoopGroup bossGroup = new NioEventLoopGroup(1,new DefaultThreadFactory("group",true));
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		// work线程池
		//EventLoopGroup worerGroup = new NioEventLoopGroup(0,new DefaultThreadFactory("work",true));
		EventLoopGroup worerGroup = new NioEventLoopGroup();
		try {
			// 服务启动器
			ServerBootstrap bootstrap = new ServerBootstrap();
			// 指定Netty的Boss线程和work线程
			bootstrap.group(bossGroup, worerGroup)
					.channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, 128)
					.childOption(ChannelOption.SO_KEEPALIVE, true);
			//注意这里的方法，是每个客户端连接都调用一次
			bootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {// 设置处理器
				@Override
				protected void initChannel(NioSocketChannel channel) throws Exception {
					ChannelPipeline pipeline = channel.pipeline();
					//心跳机制 参数:1.读空闲超时时间 2.写空闲超时时间 3.所有类型的空闲超时时间(读、写) 4.时间单位
					//在Handler需要实现userEventTriggered方法，在出现超时事件时会被触发
					pipeline.addLast(new IdleStateHandler(180, 0, 0, TimeUnit.SECONDS));
					// 以("\n")为结尾分割的 解码器，用于消息识别
					// channel.pipeline().addLast("split",
					// new DelimiterBasedFrameDecoder(1000, Delimiters.lineDelimiter()));
					//解决tcp粘包  拆包isTime =false;
					//1、限制长度，这里限制为10字节
					//pipeline.addLast(new FixedLengthFrameDecoder(26));
					//2、在包尾增加回车换行符进行分割，例如FTP协议
					//channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
					//3、DelimiterBasedFrameDecoder是分隔符解码器，用户可以指定消息结束的分隔符，它可以自动完成以分隔符作为码流结束标识的消息的解码
					//channel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer("68".getBytes())));

					//过去 可以运行
//					pipeline.addLast( new ByteArrayDecoder());
//					pipeline.addLast(new ByteArrayEncoder());
					pipeline.addLast(new StringEncoder(Charset.forName("utf-8")));
					pipeline.addLast(new StringDecoder(Charset.forName("utf-8")));

//					channel.pipeline().addLast("hander", new EbikeServerHandler());// 自定义的处理器

					//解决tcp粘包  拆包

//					//设置解码器
//					channel.pipeline().addLast(new MessageProtocolDecoder());//new ByteArrayDecoder());//new FixedLengthFrameDecoder(4));
//					//设置编码器
//					channel.pipeline().addLast(new MessageProtocolEncoder());
					//设置自定义ChannelHandler
					pipeline.addLast (new EbikeServerStrHandler());




				}
			});

			// 设置绑定的端口 因为绑定端口是耗时工作，所以这里同步
			ChannelFuture f = bootstrap.bind(port).sync();
			log.info("bind port");
			//监听服务器关闭
			f.channel().closeFuture().sync();
			log.info("start server");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			worerGroup.shutdownGracefully();
		}
	}


}
