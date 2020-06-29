package com.ming.springbootnettyclient.netty;

import com.ming.springbootnettyclient.model.User;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2020/4/9 5:17 下午
 */
@Service
@ChannelHandler.Sharable
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    @Autowired
    private NettyClient nettyClient;

    /** 循环次数 */
    private int fcount = 1;

    private User getUser(){
        User user = new User();
        user.setAge(32);
        user.setName("张三");
        user.setId(12);
        user.setSex(1);
        return user;
    }

    /**
     * 建立连接时
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("建立连接时：" + new Date());
        ctx.fireChannelActive();
        //发送消息
        ctx.write(getUser());
        ctx.flush();
    }

    /**
     * 关闭连接时
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("关闭连接时：" + new Date());
        final EventLoop eventLoop = ctx.channel().eventLoop();
        nettyClient.doConnect(new Bootstrap(), eventLoop);
        super.channelInactive(ctx);
    }

    /**
     * 心跳请求处理 每4秒发送一次心跳请求;
     *
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object obj) throws Exception {
        System.out.println("循环请求的时间：" + new Date() + "，次数" + fcount);
//        if (obj instanceof IdleStateEvent) {
//            IdleStateEvent event = (IdleStateEvent) obj;
//            if (IdleState.WRITER_IDLE.equals(event.state())) { // 如果写通道处于空闲状态,就发送心跳命令
//                UserMsg.Builder userState = UserMsg.newBuilder().setState(2);
//                ctx.channel().writeAndFlush(userState);
//                fcount++;
//            }
//        }
    }

    /**
     * 业务逻辑处理
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        // 如果不是protobuf类型的数据
//        if (!(msg instanceof UserMsg)) {
//            System.out.println("未知数据!" + msg);
//            return;
//        }
//        try {
//
//            // 得到protobuf的数据
//            UserInfo.UserMsg userMsg = (UserInfo.UserMsg) msg;
//            // 进行相应的业务处理。。。
//            // 这里就从简了，只是打印而已
//            System.out.println(
//                    "客户端接受到的用户信息。编号:" + userMsg.getId() + ",姓名:" + userMsg.getName() + ",年龄:" + userMsg.getAge());
//
//            // 这里返回一个已经接受到数据的状态
//            UserMsg.Builder userState = UserMsg.newBuilder().setState(1);
//            ctx.writeAndFlush(userState);
//            System.out.println("成功发送给服务端!");
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            ReferenceCountUtil.release(msg);
//        }
    }

}
