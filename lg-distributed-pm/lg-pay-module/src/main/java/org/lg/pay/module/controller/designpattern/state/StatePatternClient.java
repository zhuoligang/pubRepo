package org.lg.pay.module.controller.designpattern.state;

//状态模式
public class StatePatternClient {
	public static void main(String[] args) {
		//创建环境
		Context context = new Context();
		context.handle();//处理请求
		context.handle();
		context.handle();
	}
}

//环境类
class Context{
	private State state;
	public Context() {
		this.state = new ConcreteStateA();
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	public void handle(){
		this.state.handle(this);
	}
}

//抽象状态类
abstract class State{
	abstract void handle(Context context);
}

//具体状态类A
class ConcreteStateA extends State{

	@Override
	void handle(Context context) {
		System.out.println("当前状态是A。");
		context.setState(new ConcreteStateB());
	}
}

//具体状态类B
class ConcreteStateB extends State{

	@Override
	void handle(Context context) {
		System.out.println("当前状态是B。");
		context.setState(new ConcreteStateA());
	}
}