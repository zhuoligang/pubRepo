package org.lg.pay.module.controller.designpattern.proxy;
/**
 *
* @ClassName: Specialty
* @Description: TODO(抽象主题：特产)
* @author zlg
* @date 2020年1月6日下午2:14:07
*
 */
interface Specialty {
	void display();
}

/*
 * 代理
 */
class SgProxy implements Specialty{

	private WySpecialty realSubject = new WySpecialty();

	@Override
	public void display() {
		postRequest();
		realSubject.display();
		preRequest();
	}

	private void postRequest() {
		System.out.println("韶关代理婺源特产之前的预处理。");

	}

	private void preRequest() {
		System.out.println("韶关代理婺源特产之后的后续处理。");

	}

}
