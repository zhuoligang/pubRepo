package org.lg.pay.module.controller.designpattern.templatemethod;

//模板方法模式:
public class TemplateMethodPattern {
	public static void main(String[] args) {
		AbstractClass ac = new ConcreteClass();
		ac.templateMethod();
	}
}

/*
 * 抽象类
 */
abstract class AbstractClass {
	// 模板方法
	void templateMethod() {
		specificMethod();
		abstractMethod1();
		abstractMethod2();
	}

	// 具体方法
	void specificMethod() {
		System.out.println("抽象类中的具体方法被调用、、、");
	}

	// 抽象方法1
	abstract void abstractMethod1();

	// 抽象方法2
	abstract void abstractMethod2();

}

/*
 * 具体子类
 */
class ConcreteClass extends AbstractClass{

	@Override
	void abstractMethod1() {
		System.out.println("抽象方法1的实现被调用。。。");
	}

	@Override
	void abstractMethod2() {
		System.out.println("抽象方法2的实现被调用。。。");
	}
	
}

