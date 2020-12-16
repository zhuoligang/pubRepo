package org.lg.pay.module.controller.designpattern.memento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ClassName DatingGame
 * @Deacription TODO 快照模式--相亲游戏
 * @Author zlg
 * @Date 2020/3/18 15:31
 * @Version 1.0
 **/
public class DatingGame {
    public static void main(String[] args) {
        new DatingGameWin();
    }
}

//客户窗体类
class DatingGameWin extends JFrame implements ActionListener {
    JPanel centerJP, eastJP;
    JRadioButton g1, g2, g3, g4;
    JButton b1, b2;
    String fileName;
    JLabel g;
    You you;
    GirlStack girls;

    public DatingGameWin() throws HeadlessException {
        super("利用备忘录（快照）模式设计相亲游戏");
        this.setBounds(0, 0, 900, 380);
        this.setResizable(false);
        fileName = "D:/src/Photo/四大美女.jpg";
        g = new JLabel(new ImageIcon(fileName), JLabel.CENTER);
        centerJP = new JPanel();
        centerJP.setLayout(new GridLayout(1, 4));
        centerJP.setBorder(BorderFactory.createTitledBorder("四大美女如下："));
        centerJP.add(g);
        this.add("Center", centerJP);

        eastJP = new JPanel();
        eastJP.setLayout(new GridLayout(1, 1));
        eastJP.setBorder(BorderFactory.createTitledBorder("您选择的爱人是："));
        this.add("East", eastJP);

        JPanel southJP = new JPanel();
        JLabel info = new JLabel("四大美女有“沉鱼落雁之容、闭月羞花之貌”，你选择谁？");
        g1 = new JRadioButton("西施", true);
        g2 = new JRadioButton("貂蝉");
        g3 = new JRadioButton("王昭君");
        g4 = new JRadioButton("杨玉环");
        b1 = new JButton("确定");
        b2 = new JButton("返回");
        ButtonGroup group = new ButtonGroup();
        group.add(g1);
        group.add(g2);
        group.add(g3);
        group.add(g4);
        southJP.add(info);
        southJP.add(g1);
        southJP.add(g2);
        southJP.add(g3);
        southJP.add(g4);
        southJP.add(b1);
        southJP.add(b2);
        b1.addActionListener(this);
        b2.addActionListener(this);
        this.add("South", southJP);
        showPicture("空白");
        you = new You();
        you.setWife("空白");
        //保存状态
        girls = new GirlStack();
        girls.push(you.createMemento());
    }

    //显示图片
    void showPicture(String name) {
        eastJP.removeAll();//清除面板内容
        eastJP.repaint();//刷新屏幕
        fileName = "D:/src/Photo/" + name + ".jpg";
        g = new JLabel(new ImageIcon(fileName), JLabel.CENTER);
        eastJP.add(g);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean bool = false;
        if (e.getSource() == b1) {
            //保存状态
            bool = girls.push(you.createMemento());
            //保存状态
            if (bool && g1.isSelected()) {
                showPicture("西施");
            } else if (bool && g2.isSelected()) {
                showPicture("貂蝉");
            } else if (bool && g3.isSelected()) {
                showPicture("王昭君");
            } else if (bool && g4.isSelected()) {
                showPicture("杨玉环");
            }
        }else if (e.getSource() == b2) {
            //恢复状态
            you.restoreMemento(girls.pop());
            showPicture(you.getWife());
        }
    }
}

//备忘录：美女
class Girl {
    private String name;

    Girl(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Girl setName(String name) {
        this.name = name;
        return this;
    }
}

/**
 * 发起人：您
 */
class You {
    /**
     * 妻子
     */
    private String wifeName;

    public String getWife() {
        return wifeName;
    }

    public You setWife(String wife) {
        this.wifeName = wife;
        return this;
    }

    public Girl createMemento() {
        return new Girl(wifeName);
    }

    public void restoreMemento(Girl g) {
        setWife(g.getName());
    }
}

/**
 * 管理者：美女栈
 */
class GirlStack {
    private Girl girl[];
    private int top;

    GirlStack() {
        girl = new Girl[5];
        top = -1;
    }

    public boolean push(Girl g) {
        if (top >= 4) {
            System.out.println("你太花心了，变来变去的！");
            return false;
        } else {
            girl[++top] = g;
            return true;
        }
    }

    public Girl pop() {
        if (top <= 0) {
            System.out.println("美女栈空了！");
            return girl[0];
        } else {
            return girl[top--];
        }
    }
}