package org.lg.pay.module.controller.designpattern.adapter;

//适配器（Adapter）模式：将一个类的接口转换成客户希望的另外一个接口，使得原本由于接口不兼容而不能一起工作的那些类能一起工作。
public class MotorAdapterTest {
	public static void main(String[] args) {
		System.out.println("适配器模式测试：");
		Motor motor = (Motor) ReadXML.getObject();
		motor.drive();
	}

}
