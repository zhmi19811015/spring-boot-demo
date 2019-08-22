package com.ming.nettydemo.message;

import com.ming.nettydemo.common.constant.NettyConstant;
import lombok.Data;

/**
 * 自定义协议<br/>
 * 数据包格式
 * 协议开始标识|长度|数据|结束标识
 * 1、协议开始标识
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/5/30 3:48 PM
 */
@Data
public class MessageProtocol {
    /** 消息头 .*/
    private int head_data = NettyConstant.MsgType.HEAD_DATA;
    /** 消息长度 .*/
    private int contentLength;
    /** 消息的内容 .*/
    private byte[] content;
    /** 消息尾部 .*/
    private int end_data = 68;

    public MessageProtocol(int contentLength, byte[] content){
        this.contentLength = contentLength;
        this.content = content;
    }
}
