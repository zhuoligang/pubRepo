package org.lg.pay.module.controller.designpattern.prototype;

/**
 *
 * @ClassName: PrototypeTest
 * @Description: TODO(原型模式--测试类)
 * @author zlg
 * @date 2019年12月31日下午2:29:45
 *
 */
public class PrototypeTest {

	public static void main(String[] args) throws CloneNotSupportedException {
		Realizetype obj1 = new Realizetype();
		Realizetype obj2 = (Realizetype) obj1.clone();
		System.out.println("obj1 == obj2 ?" + (obj1 == obj2));

		Citation obj3 = new Citation("张小飞", "同学在2019年第一学期中表现优异，被评为三好学生。", "--清华大学");
		obj3.display();
		Citation obj4 = (Citation) obj3.clone();
		obj4.setName("卓别林");
		obj4.display();

		ProtoTypeManager p = new ProtoTypeManager();
		Shape c = p.getShape("Circle");
		c.countArea();

		Shape s = p.getShape("Square");
		s.countArea();
	}
}
