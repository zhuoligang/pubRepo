package org.lg.pay.module.controller.designpattern.decorator;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 * 抽象构建角色：莫莉卡
 */
interface Morrigan {
	public void display();
}

/*
 * 具体构建角色：原身
 */
class Original extends JFrame implements Morrigan {

	private static final long serialVersionUID = 1L;
	private String t = "Morrigan0.jpg";

	public Original() {
		super("《恶魔战士》中的莫莉卡·安斯兰");
	}

	public void setImage(String t) {
		this.t = t;
	}

	@Override
	public void display() {
		this.setLayout(new FlowLayout());
		JLabel l1 = new JLabel(new ImageIcon("D:/src/" + t));
		this.add(l1);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}

/*
 * 抽象装饰角色：变形
 */
class Changer implements Morrigan {
	Morrigan m;

	public Changer(Morrigan m) {
		this.m = m;
	}

	@Override
	public void display() {
		m.display();
	}
}

/*
 * 具体装饰角色：女妖
 */
class Succubus extends Changer{

	public Succubus(Morrigan m) {
		super(m);
	}
	public void display(){
		setChanger();
		super.display();
	}
	
	public void setChanger(){
		((Original)super.m).setImage("Morrigan1.jpg");
	}
}

/*
 * 具体装饰角色：少女
 */
class Girl extends Changer{

	public Girl(Morrigan m) {
		super(m);
	}
	public void display(){
		setChanger();
		super.display();
	}
	public void setChanger(){
		((Original)super.m).setImage("Morrigan2.jpg");
	}
	
}
