package org.lg.pay.module.controller.designpattern.bridge;

//桥接（Bridge）模式：将抽象与实现分离，使它们可以独立变化。它是用组合关系代替继承关系来实现的，从而降低了抽象和实现这两个可变维度的耦合度
public class BridgeTest {
	public static void main(String[] args) {
		Implementor imple = new ConcreteOmplementorA();
		Abstraction abs = new RefinedAbstraction(imple);
		abs.Opereation();
	}

}
