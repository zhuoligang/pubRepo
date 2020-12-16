package org.lg.pay.module.controller.designpattern.bridge;

/*
 * 实现化角色
 */
 interface Implementor {
	 public void OperationImp();
}

 /*
  * 具体实现化角色
  */
 class ConcreteOmplementorA implements Implementor{

	@Override
	public void OperationImp() {
		System.out.println("具体实现化（Concrete Implementor）角色被访问 ");
	}
 }
 
 /*
  * 抽象化角色
  */
 abstract class Abstraction{
	 protected Implementor imple;
	 protected Abstraction(Implementor imple){
		 this.imple = imple;
	 }
	 public abstract void Opereation();
 }
 
 /*
  * 扩展抽象化角色
  */
 class RefinedAbstraction extends Abstraction{

	protected RefinedAbstraction(Implementor imple) {
		super(imple);
	}

	@Override
	public void Opereation() {
		System.out.println("扩展抽象化（Refiend Abstraction）角色被访问");
		imple.OperationImp();
	}
 }