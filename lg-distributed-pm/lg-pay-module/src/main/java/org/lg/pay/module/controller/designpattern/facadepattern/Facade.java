package org.lg.pay.module.controller.designpattern.facadepattern;

/*
 * 外观角色
 */
class Facade {
	private SubSystem01 obj1 = new SubSystem01();
	private SubSystem02 obj2 = new SubSystem02();
	private SubSystem03 obj3 = new SubSystem03();
	public void method(){
		obj1.method1();
		obj2.method2();
		obj3.method3();
	}
	
}

/*
 * 子系统角色1
 */
class SubSystem01{
	public void method1(){
		System.out.println("子系统01的method1()方法被调用");
	}
	
}
/*
 * 子系统角色2
 */
class SubSystem02{
	public void method2(){
		System.out.println("子系统02的method2()方法被调用");
	}
	
}
/*
 * 子系统角色3
 */
class SubSystem03{
	public void method3(){
		System.out.println("子系统03的method3()方法被调用");
	}
	
}
