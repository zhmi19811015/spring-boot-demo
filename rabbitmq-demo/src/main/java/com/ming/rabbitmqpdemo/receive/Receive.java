//package com.ming.rabbitmqpdemo.receive;
//
//import org.springframework.stereotype.Component;
//
//import java.nio.charset.Charset;
//
///**
// * 自定义消息接收类
// * @author lc
// */
//@Component
//public class Receive {
//
//    public void handleMessage(String msg){
//        System.err.println("handleMessage: "+ msg);
//    }
//
//    public void process(byte[] bytes){
//        System.err.println("Process: "+ new String(bytes, Charset.defaultCharset()));
//    }
//}
