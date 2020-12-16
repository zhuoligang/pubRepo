package org.lg.pay.module.controller.designpattern.proxy;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 
* @ClassName: WySpecialty
* @Description: TODO(真实主题：婺源特产)
* @author zlg
* @date 2020年1月6日下午2:15:24
*
 */
public class WySpecialty extends JFrame implements Specialty{

	private static final long serialVersionUID = 1L;

	public WySpecialty() {
		super("韶关代理婺源特产测试");
		this.setLayout(new GridLayout(1, 1));
		JLabel l1 = new JLabel(new ImageIcon("D:/src/WuyuanSpecialty.jpg"));
		this.add(l1);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void display() {
		this.setVisible(true);
	}

}
