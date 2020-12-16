package org.lg.pay.module.controller.designpattern.decorator;

//装饰（Decorator）模式：动态地给对象增加一些职责，即增加其额外的功能。
public class Test {
	public static void main(String[] args) {
		Component component = new ConcreteComponent();
		component.operation();
		System.out.println("-------------------------------------");
		Decorator decorator = new ConcreteDecorator(component);
		decorator.operation();
		
		
		Morrigan m0 = new Original();
		m0.display();
		Morrigan m1 = new Succubus(m0);
		m1.display();
		Morrigan m2 = new Girl(m0);
		m2.display();
	}

}
