package com.ming.nettydemo.netty;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EbikeServer implements CommandLineRunner{

	@Override
	public void run(String... args) throws Exception {
		startServer(null);
	}

	
	/**
	 * start server
	 * @param port
	 */
	public void startServer(Integer port) {
		if (null == port) {
			port = 52018;
		}
		EventLoopGroup bossGroup = new NioEventLoopGroup();// boss线程池
		EventLoopGroup worerGroup = new NioEventLoopGroup();// work线程池
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();// 服务启动器
			bootstrap.group(bossGroup, worerGroup);// 指定Netty的Boss线程和work线程
			bootstrap.channel(NioServerSocketChannel.class);// 设置服务器通道类
			bootstrap.option(ChannelOption.SO_BACKLOG, 128);
			bootstrap.option(ChannelOption.SO_LINGER, 0);
			bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);


			bootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {// 设置处理器
				@Override
				protected void initChannel(NioSocketChannel channel) throws Exception {
					// 以("\n")为结尾分割的 解码器，用于消息识别
					// channel.pipeline().addLast("split",
					// new DelimiterBasedFrameDecoder(1000, Delimiters.lineDelimiter()));
					channel.pipeline().addLast("byteArrayDecoder", new ByteArrayDecoder());
					channel.pipeline().addLast("byteArrayEncoder", new ByteArrayEncoder());
					channel.pipeline().addLast("hander", new EbikeServerHandler());// 自定义的处理器

				}
			});
			bootstrap.bind(port).sync();// 设置绑定的端口
			// future.channel().closeFuture().sync();
			log.info("start server");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			//bossGroup.shutdownGracefully();
			//worerGroup.shutdownGracefully();
		}
	}


}
