package org.lg.pay.module.controller.designpattern.command;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

//命令模式--烹饪
public class CookingCommand {
	public static void main(String[] args) {
		Breakfast f1 = new Changfen();
		Breakfast f2 = new Huntun();
		Breakfast f3 = new Hefen();
		Waiter w = new Waiter();
		w.setChangFen(f1);//设置肠粉菜单
		w.setHunTun(f2);//设置馄饨菜单
		w.setHeFen(f3);//设置河粉菜单
		
		w.chooseChangFen();//选择肠粉
		w.chooseHunTun();//选择馄饨
		w.chooseHeFen();//选择河粉
	}
}

// 调用者：服务员
class Waiter {
	private Breakfast changFen, hunTun, heFen;

	public void setChangFen(Breakfast f) {
		this.changFen = f;
	}

	public void chooseChangFen() {
		this.changFen.cooking();
	}

	public void setHunTun(Breakfast hunTun) {
		this.hunTun = hunTun;
	}

	public void chooseHunTun() {
		this.hunTun.cooking();
	}

	public void setHeFen(Breakfast heFen) {
		this.heFen = heFen;
	}

	public void chooseHeFen() {
		this.heFen.cooking();
	}
}

// 抽象命令：早餐
interface Breakfast {
	abstract void cooking();
}

// 具体命令：肠粉
class Changfen implements Breakfast {
	private ChangfenChef receiver = new ChangfenChef();

	@Override
	public void cooking() {
		this.receiver.cooking();
	}
}

// 具体命令：馄饨
class Huntun implements Breakfast {
	private HunTunChef receiver = new HunTunChef();

	@Override
	public void cooking() {
		this.receiver.cooking();
	}

}

// 具体命令：河粉
class Hefen implements Breakfast {
	private HeFenChef receiver = new HeFenChef();

	@Override
	public void cooking() {
		this.receiver.cooking();
	}
}

// 接收者：肠粉厨师
class ChangfenChef extends JFrame {

	private static final long serialVersionUID = 1L;
	JLabel l = new JLabel();

	public ChangfenChef() {
		super("煮肠粉");
		l.setIcon(new ImageIcon("D:/src/ChangFen.jpg"));
		this.add(l);
		this.setLocation(30, 30);
		this.pack();
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void cooking() {
		this.setVisible(true);
	}
}

// 接收者：馄饨厨师
class HunTunChef extends JFrame {

	private static final long serialVersionUID = 1L;

	JLabel l = new JLabel();

	public HunTunChef() {
		super("煮馄饨");
		l.setIcon(new ImageIcon("D:/src/HunTun.jpg"));
		this.add(l);
		this.setLocation(350, 50);
		this.pack();
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void cooking() {
		this.setVisible(true);
	}
}

// 接收者：河粉厨师
class HeFenChef extends JFrame {

	private static final long serialVersionUID = 1L;
	JLabel l = new JLabel();

	public HeFenChef() {
		super("煮河粉");
		l.setIcon(new ImageIcon("D:/src/HeFen.jpg"));
		this.add(l);
		this.setLocation(200, 200);
		this.pack();
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void cooking() {
		this.setVisible(true);
	}
}
