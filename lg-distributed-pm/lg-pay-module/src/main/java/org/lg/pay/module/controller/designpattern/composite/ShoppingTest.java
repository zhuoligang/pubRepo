package org.lg.pay.module.controller.designpattern.composite;

import java.util.ArrayList;

//组合（Composite）模式：将对象组合成树状层次结构，使用户对单个对象和组合对象具有一致的访问性。
public class ShoppingTest {
	public static void main(String[] args) {
		float s = 0;
		Bags bigBag, mediumBag, smallWhiteBag, smallRedBag;
		Goods sp;
		bigBag = new Bags("大袋子");
		mediumBag = new Bags("中袋子");
		smallRedBag = new Bags("红色小袋子");
		smallWhiteBag = new Bags("白色小袋子");

		sp = new Goods("成都特产", 2, 7.9f);
		smallRedBag.add(sp);
		sp = new Goods("成都地图", 1, 9.9f);
		smallRedBag.add(sp);

		sp = new Goods("韶关香菇", 2, 68);
		smallWhiteBag.add(sp);
		sp = new Goods("韶关红茶", 3, 180);
		smallWhiteBag.add(sp);

		sp = new Goods("景德镇瓷器", 1, 380);
		mediumBag.add(sp);
		mediumBag.add(smallRedBag);

		sp = new Goods("李宁牌运动鞋", 1, 198);
		bigBag.add(sp);
		bigBag.add(smallWhiteBag);
		bigBag.add(mediumBag);

		System.out.println("您选购的商品清单：");
		bigBag.show();
		s = bigBag.calculation();
		System.out.println("请支付总计：" + s + "元");

	}

}

/*
 * 抽象构件：物品
 */
interface Articles {
	// 计算
	public float calculation();

	public void show();
}

/*
 * 树叶构件：商品
 */
class Goods implements Articles {
	private String name;
	// 数量
	private int quantity;
	// 单价
	private float unitPrice;

	public Goods(String name, int quantity, float unitPrice) {
		this.name = name;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}

	@Override
	public float calculation() {
		return quantity * unitPrice;
	}

	@Override
	public void show() {
		System.out.println(name + "(数量：" + quantity + ",单价：" + unitPrice + "元)");
	}

}

/*
 * 树枝构件：袋子
 */
class Bags implements Articles {
	// 名字
	private String name;
	private ArrayList<Articles> bags = new ArrayList<>();

	public Bags(String name) {
		super();
		this.name = name;
	}

	public void add(Articles c) {
		bags.add(c);
	}

	public void remove(Articles c) {
		bags.remove(c);
	}

	public Articles getChild(int i) {
		return bags.get(i);
	}

	@Override
	public float calculation() {
		float s = 0;
		for (Articles articles : bags) {
			s += articles.calculation();
		}
		return s;
	}

	@Override
	public void show() {
		System.out.println(name +"中包含以下商品：");
		for (Articles articles : bags) {
			articles.show();
		}
	}

}
