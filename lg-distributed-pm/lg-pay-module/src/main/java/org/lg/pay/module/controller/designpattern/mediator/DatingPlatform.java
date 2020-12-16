package org.lg.pay.module.controller.designpattern.mediator;

import javax.swing.*;
import javax.xml.ws.Service;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DatingPlatform
 * @Deacription TODO 中介者模式--交流平台
 * @Author zlg
 * @Date 2020/3/7 10:23
 * @Version 1.0
 **/
public class DatingPlatform {
    public static void main(String[] args) {
        Medium medium = new EstateMedium();
        Customer buyer, seller;
        buyer = new Buyer("张三（买方）");
        seller = new Seller("李四（卖方）");
        medium.register(buyer);
        medium.register(seller);


    }
}

//抽象中介者：中介公司
interface Medium {
    void register(Customer member);//客户注册

    void replay(String from, String ad);//转发
}

//具体中介者：房地产中介
class EstateMedium implements Medium {

    private List<Customer> members = new ArrayList<>();

    @Override
    public void register(Customer member) {
        if (!members.contains(member)) {
            members.add(member);
            member.setMedium(this);
        }
    }

    @Override
    public void replay(String from, String ad) {
        for (Customer member : members) {
            String name = member.getName();
            if (!name.equals(from)) {
                member.receive(from, ad);
            }
        }

    }
}

//抽象同事类：客户
abstract class Customer extends JFrame implements ActionListener {
    protected Medium medium;
    protected String name;
    JTextField sentText;
    JTextArea receiveArea;

    public Customer(String name) {
        super(name);
        this.name = name;
    }

    void ClientWindow(int x, int y) {
        Container cp;
        JScrollPane sp;
        JPanel p1, p2;
        cp = this.getContentPane();
        sentText = new JTextField(18);
        receiveArea = new JTextArea(10, 18);
        receiveArea.setEditable(false);
        p1 = new JPanel();
        p1.setBorder(BorderFactory.createTitledBorder("接收内容："));
        p1.add(receiveArea);
        sp = new JScrollPane(p1);
        cp.add(sp, BorderLayout.NORTH);

        p2 = new JPanel();
        p2.setBorder(BorderFactory.createTitledBorder("发送内容："));
        p2.add(sentText);
        cp.add(p2, BorderLayout.SOUTH);
        sentText.addActionListener(this);
        this.setLocation(x, y);
        this.setSize(250, 300);
        this.setResizable(false);//窗口大小不可调整
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String tempInfo = sentText.getText().trim();
        sentText.setText("");
        this.send(tempInfo);
    }

    public String getName() {
        return name;
    }

    public Customer setMedium(Medium medium) {
        this.medium = medium;
        return this;
    }

    abstract void send(String ad);

    abstract void receive(String from, String ad);
}

//具体同事类：卖方
class Seller extends Customer {

    public Seller(String name) {
        super(name);
        ClientWindow(50, 100);
    }

    @Override
    void send(String ad) {
        receiveArea.append("我（卖方）说：" + ad + "\n");
        //使滚动条滚动到最底端
        receiveArea.setCaretPosition(receiveArea.getText().length());
        medium.replay(name, ad);
    }

    @Override
    void receive(String from, String ad) {
        receiveArea.append(from + "说：" + ad + "\n");
        //使滚动条滚动到最底端
        receiveArea.setCaretPosition(receiveArea.getText().length());
    }
}

//具体同事类：买方
class Buyer extends Customer {

    public Buyer(String name) {
        super(name);
        ClientWindow(350, 100);
    }

    @Override
    void send(String ad) {
        receiveArea.append("我（买方）说：" + ad + "\n");
        //使滚动条滚动到最底端
        receiveArea.setCaretPosition(receiveArea.getText().length());
        medium.replay(name, ad);
    }

    @Override
    void receive(String from, String ad) {
        receiveArea.append(from + "说：" + ad + "\n");
        //使滚动条滚动到最底端
        receiveArea.setCaretPosition(receiveArea.getText().length());
    }
}