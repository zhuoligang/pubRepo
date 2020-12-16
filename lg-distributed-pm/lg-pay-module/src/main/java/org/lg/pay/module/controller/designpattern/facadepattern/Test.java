package org.lg.pay.module.controller.designpattern.facadepattern;

//外观（Facade）模式：为多个复杂的子系统提供一个一致的接口，使这些子系统更加容易被访问
public class Test {
	public static void main(String[] args) {
		Facade f = new Facade();
		f.method();
	}

}
