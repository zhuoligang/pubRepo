package org.lg.pay.module.controller.designpattern.decorator;

/*
 * 抽象构建角色
 */
interface Component {
	public void operation();
}

/*
 * 具体构建角色
 */
class ConcreteComponent implements Component{
	 public ConcreteComponent() {
		 System.out.println("创建具体构建角色");
	}
	
	@Override
	public void operation() {
		System.out.println("调用具体构建角色的方法operation()");
	}
	
}

/*
 * 抽象装饰角色
 */
class Decorator implements Component{
	private Component component;
	public Decorator (Component component){
		this.component = component;
	}

	@Override
	public void operation() {
		component.operation();
	}
	
}

/*
 * 具体装饰角色
 */
class ConcreteDecorator extends Decorator{

	public ConcreteDecorator(Component component) {
		super(component);
	}
	public void operation(){
		super.operation();
		addFunction();
	}
	public void addFunction(){
		System.out.println("为具体角色增加的功能addFunction()");
	}
	
}



