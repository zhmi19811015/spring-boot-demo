package com.ming.demo.gui;

import java.awt.*;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/7/16 7:12 PM
 */
public class Test {


    public static void main(String[] args) {
        Frame f=new Frame("my awt");
        f.setSize(500,400);
        f.setLocation(300,200);
        f.setLayout(new FlowLayout());
        Button b=new Button("我是一个按钮");
        f.add(b);
        f.addWindowListener(new MyWin());
        f.setVisible(true);
        System.out.println("Hello world!");
    }
}
