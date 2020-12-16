package org.lg.pay.module.controller.designpattern.factorymethod;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Horse implements Animal{

	JScrollPane sp;
	JFrame jf = new JFrame("工厂方法模式测试");
	
	public Horse(){
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(1,1));
		p1.setBorder(BorderFactory.createTitledBorder("动物：马"));
		JLabel l1 = new JLabel(new ImageIcon("D:/src/ma.jpg"));
		p1.add(l1);
		sp = new JScrollPane(p1);
		
		Container contentPane = jf.getContentPane();
		contentPane.add(sp,BorderLayout.CENTER);
		jf.pack();
		jf.setVisible(false);
		//用户点击窗口关闭
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void show() {
		jf.setVisible(true);
	}

}
