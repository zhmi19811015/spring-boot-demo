package com.ming.demo.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/7/16 7:26 PM
 */
public class MyWin extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
        // TODO Auto-generated method stub
        //System.out.println("Window closing"+e.toString());
        System.out.println("我关了");
        System.exit(0);
    }
    @Override
    public void windowActivated(WindowEvent e) {
        //每次获得焦点 就会触发
        System.out.println("我活了");
        //super.windowActivated(e);
    }
    @Override
    public void windowOpened(WindowEvent e) {
        // TODO Auto-generated method stub
        System.out.println("我开了");
        //super.windowOpened(e);
    }
}
