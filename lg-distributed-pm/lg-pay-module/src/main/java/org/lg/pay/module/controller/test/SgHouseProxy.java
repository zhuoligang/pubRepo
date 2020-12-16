package org.lg.pay.module.controller.test;

import javax.swing.*;
import java.awt.*;


/**
 * @Description 代理模式--模拟房屋中介
 * @ClassName SgHouseProxy
 * @Author zlg
 * @date 2020.03.26 14:08
 */
public class SgHouseProxy {
    public static void main(String[] args) {
        HouseProxy proxy = new HouseProxy();
        proxy.display();
    }
}

//抽象主题：房主
interface HouseOwner {
    void display();
}

//真实主题：韶关碧桂园
class SgBiguiyuan extends JFrame implements HouseOwner {
    SgBiguiyuan() {
        super("房产中介代售韶关碧桂园房子");
    }

    @Override
    public void display() {
        this.setLayout(new GridLayout(1, 1));
        JLabel l1 = new JLabel(new ImageIcon("D:/src/SgBiguiyuan.jpg"));
        this.add(l1);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

//代理：房产中介
class HouseProxy implements HouseOwner{
    private SgBiguiyuan realSubject = new SgBiguiyuan();
    public void preRequest(){
        System.out.println("房产中介介绍韶关碧桂园房子。");
    }
    public void postRequest(){
        System.out.println("房产中介登记购房者信息。");
    }
    @Override
    public void display() {
        preRequest();
        realSubject.display();
        postRequest();
    }
}
