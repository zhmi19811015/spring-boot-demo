package com.ming.springbootnettyclient.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2020/4/9 5:17 下午
 */
@Component
public class NettyClientFilter extends ChannelInitializer<SocketChannel> {

    @Autowired
    private NettyClientHandler nettyClientHandler;


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline ph = ch.pipeline();
        /*
         * 解码和编码，应和服务端一致
         * */
        //入参说明: 读超时时间、写超时时间、所有类型的超时时间、时间格式
        ph.addLast(new IdleStateHandler(0, 4, 0, TimeUnit.SECONDS));
        //添加POJO对象解码器 禁止缓存类加载器
        ph.addLast(new ObjectDecoder(1024, ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
        //设置发送消息编码器
        ph.addLast(new ObjectEncoder());

//        //传输的协议 Protobuf
//        ph.addLast(new ProtobufVarint32FrameDecoder());
//        ph.addLast(new ProtobufDecoder(UserMsg.getDefaultInstance()));
//        ph.addLast(new ProtobufVarint32LengthFieldPrepender());
//        ph.addLast(new ProtobufEncoder());

        //业务逻辑实现类
        ph.addLast("nettyClientHandler",nettyClientHandler);

    }
}
