package org.lg.pay.module.controller.designpattern.buildertype;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import lombok.Data;

/**
 * 
* @ClassName: Parlour
* @Description: TODO(产品：客厅)
* @author zlg
* @date 2020年1月6日上午9:52:38
*
 */
@Data
public class Parlour {
	/*
	 * 墙
	 */
	private String wall;
	/*
	 * 电视
	 */
	private String TV;
	/*
	 * 沙发
	 */
	private String sofa;
	
	public void show(){
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1,1));
		p.setBorder(BorderFactory.createTitledBorder("客厅"));
		JLabel l = new JLabel(new ImageIcon("D:/src/" + wall + TV + sofa + ".jpg"));
		p.add(l);
		JScrollPane sp = new JScrollPane(p);
		
		JFrame jf = new JFrame("建造者模式测试");
		Container contentPane = jf.getContentPane();
		contentPane.add(sp, BorderLayout.CENTER);
		jf.pack();
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}

/*
 * 指挥者：项目经理
 */
class ProjectManager {
	private Decorator builder;
	public ProjectManager(Decorator builder){
		this.builder = builder;
	}
	/*
	 * 产品构建与组装方法
	 */
	public Parlour decorate(){
		builder.buildWall();
		builder.buildTV();
		builder.buildSofa();
		return builder.getResult();
	}
	
}
