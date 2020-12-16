package org.lg.pay.module.controller.designpattern.abstractfactory;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/*
 * 抽象产品：植物类
 */
interface Plant {
	void show();
}

/*
 * 具体产品类：水果
 */
class Fruitage implements Plant{
	
	JScrollPane sp;
	JFrame jf = new JFrame("抽象工厂模式测试");

	public Fruitage() {
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(1, 1));
		p1.setBorder(BorderFactory.createTitledBorder("植物：水果"));
		JLabel l1 = new JLabel(new ImageIcon("D:/src/shuiguo.jpg"));
		p1.add(l1);
		sp = new JScrollPane(p1);
		
		Container contentPane = jf.getContentPane();
		contentPane.add(sp,BorderLayout.CENTER);
		
		jf.pack();
		jf.setVisible(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}



	@Override
	public void show() {
		jf.setVisible(true);
	}

}

/*
 * 具体产品类：蔬菜
 */
class Vegetables implements Plant{
	
	JScrollPane sp;
	JFrame jf = new JFrame("抽象工厂模式测试");
	
	public Vegetables() {
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(1, 1));
		p1.setBorder(BorderFactory.createTitledBorder("植物：蔬菜"));
		JLabel l1 = new JLabel(new ImageIcon("D:/src/shucai.jpg"));
		p1.add(l1);
		sp = new JScrollPane(p1);
		
		Container contentPane = jf.getContentPane();
		contentPane.add(sp,BorderLayout.CENTER);
	
		jf.pack();
		jf.setVisible(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void show() {
		jf.setVisible(true);
	}

}
