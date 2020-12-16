package org.lg.pay.module.controller.designpattern.composite;

import java.util.ArrayList;

/*
 * 组合模式（部分-整体模式）
 */
public class CompositePattern {
	public static void main(String[] args) {
		Component c0 = new Composite();
		Component c1 = new Composite();
		Component l1 = new Leaf("1");
		Component l2 = new Leaf("2");
		Component l3 = new Leaf("3");
		c0.add(l1);
		c0.add(c1);
		c1.add(l2);
		c1.add(l3);
		c0.operation();
	}
}

/*
 * 抽象构件
 */
interface Component {
	public void add(Component c);

	public void remove(Component c);

	public Component getChild(int i);

	public void operation();
}

/*
 * 树叶构件
 */
class Leaf implements Component {
	private String name;
	public Leaf(String name) {
		this.name = name;
	}

	@Override
	public void add(Component c) {
	}

	@Override
	public void remove(Component c) {
	}

	@Override
	public Component getChild(int i) {
		return null;
	}

	@Override
	public void operation() {
		System.out.println("树叶" + name + ":被访问！");
	}

}

/*
 * 树枝构件
 */
class Composite implements Component{
	private ArrayList<Component> children = new ArrayList<>();

	@Override
	public void add(Component c) {
		children.add(c);
	}

	@Override
	public void remove(Component c) {
		children.remove(c);
	}

	@Override
	public Component getChild(int i) {
		return children.get(i);
	}

	@Override
	public void operation() {
		for (Component component : children) {
			component.operation();
		}
		
	}
	
}
