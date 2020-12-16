package org.lg.pay.module.controller.designpattern.flyweight;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

//享元（Flyweight）模式：运用共享技术来有效地支持大量细粒度对象的复用。
public class WzqGame {
	public static void main(String[] args) {
		 new Chessboard();
	}
}

/*
 * 抽象享元角色：棋子
 */
interface ChessPieces {
	// 下子
	public void DownPieces(Graphics g, Point pt);
}

/*
 * 具体享元角色：白子
 */
class WhitePieces implements ChessPieces {

	@Override
	public void DownPieces(Graphics g, Point pt) {
		g.setColor(Color.WHITE);
		g.fillOval(pt.x, pt.y, 30, 30);
	}
}

/*
 * 具体享元角色：黑子
 */
class BlackPieces implements ChessPieces {

	@Override
	public void DownPieces(Graphics g, Point pt) {
		g.setColor(Color.BLACK);
		g.fillOval(pt.x, pt.y, 30, 30);
	}
}

/*
 * 享元工厂角色
 */
class WeiqiFactory {
	private ArrayList<ChessPieces> qz;

	public WeiqiFactory() {
		qz = new ArrayList<>();
		ChessPieces w = new WhitePieces();
		qz.add(w);
		ChessPieces b = new BlackPieces();
		qz.add(b);
	}

	public ChessPieces getChessPieces(String type) {
		if ("w".equalsIgnoreCase(type)) {
			return qz.get(0);
		} else if ("b".equalsIgnoreCase(type)) {
			return qz.get(1);
		} else {
			return null;
		}
	}
}

/*
 * 棋盘
 */
class Chessboard extends MouseAdapter {
	WeiqiFactory wf;
	JFrame f;
	Graphics g;
	JRadioButton wz;
	JRadioButton bz;
	private final int x = 50;
	private final int y = 50;
	// 小方格宽度和高度
	private final int w = 40;
	// 棋盘宽度和高度
	private final int rw = 400;

	public Chessboard() {
		wf = new WeiqiFactory();
		f = new JFrame("享元模式在五子棋游戏中的应用");
		f.setBounds(100, 100, 500, 550);
		f.setVisible(true);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel southJP = new JPanel();
		f.add("South", southJP);

		JPanel centerJP = new JPanel();
		centerJP.setLayout(null);
		centerJP.setSize(500, 500);
		centerJP.addMouseListener(this);
		f.add("Center", centerJP);

		wz = new JRadioButton("白子");
		bz = new JRadioButton("黑子", true);
		ButtonGroup group = new ButtonGroup();
		group.add(wz);
		group.add(bz);
		southJP.add(wz);
		southJP.add(bz);

		try {
			Thread.sleep(500);
		} catch (Exception e) {
			e.printStackTrace();
		}

		g = centerJP.getGraphics();
		g.setColor(Color.BLUE);
		g.drawRect(x, y, rw, rw);
		for (int i = 1; i < 10; i++) {
			// 绘制第i条竖线
			g.drawLine(x + (i * w), y, x + (i * w), y + rw);
			// 绘制第i条水平线
			g.drawLine(x, y + (i * w), x + rw, y + (i * w));
		}
	}

	public void mouseClicked(MouseEvent e) {
		Point pt = new Point(e.getX() - 15, e.getY() - 15);
		if(wz.isSelected()){
			ChessPieces c1 = wf.getChessPieces("w");
			c1.DownPieces(g, pt);
		}else if (bz.isSelected()){
			ChessPieces c2 = wf.getChessPieces("b");
			c2.DownPieces(g, pt);
		}
	}
}