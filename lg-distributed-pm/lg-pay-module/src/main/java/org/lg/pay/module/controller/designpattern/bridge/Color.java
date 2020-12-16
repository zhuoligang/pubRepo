package org.lg.pay.module.controller.designpattern.bridge;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * 实现化角色：颜色
 */
interface Color {
	String getColor();
}

/*
 * 具体实现化角色：黄色
 */
class Yellow implements Color {
	public String getColor() {
		return "yellow";
	}
}

/*
 * 具体实现化角色：红色
 */
class Red implements Color {
	@Override
	public String getColor() {
		return "red";
	}
}

/*
 * 抽象化角色：包
 */
abstract class Bag {
	protected Color color;

	public void setColor(Color color) {
		this.color = color;
	}

	public abstract void show();
}

/*
 * 扩展抽象化角色：挎包
 */
class HandBag extends Bag {
	public String getName() {
		return color.getColor() + "_handBag";
	}

	@Override
	public void show() {
		JFrame jf = new JFrame("桥接模式测试");
		Container contentPane = jf.getContentPane();
		JPanel p = new JPanel();
		JLabel l = new JLabel(new ImageIcon("D:/src/" + getName() + ".jpg"));
		p.setLayout(new GridLayout(1, 1));
		p.setBorder(BorderFactory.createTitledBorder("女士挎包"));
		p.add(l);
		contentPane.add(p, BorderLayout.CENTER);
		jf.pack();
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

/*
 * 扩展抽象化角色：钱包
 */
class Wallet extends Bag {
	public String getName() {
		return color.getColor() + "_wallet";
	}

	@Override
	public void show() {
		JFrame jf = new JFrame("桥接模式测试");
		Container contentPane = jf.getContentPane();
		JPanel p = new JPanel();
		JLabel l = new JLabel(new ImageIcon("D:/src/" + getName() + ".jpg"));
		p.setLayout(new GridLayout(1, 1));
		p.setBorder(BorderFactory.createTitledBorder("女士钱包"));
		p.add(l);
		contentPane.add(p, BorderLayout.CENTER);
		jf.pack();
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
