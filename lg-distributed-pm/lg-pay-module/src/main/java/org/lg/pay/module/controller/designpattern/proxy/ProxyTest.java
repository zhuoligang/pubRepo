package org.lg.pay.module.controller.designpattern.proxy;

//代理（Proxy）模式：为某对象提供一种代理以控制对该对象的访问。即客户端通过代理间接地访问该对象，从而限制、增强或修改该对象的一些特性。
public class ProxyTest {
	public static void main(String[] args) {
		SgProxy proxy = new SgProxy();
		proxy.display();
	}

}
