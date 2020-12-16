package org.lg.pay.module.controller.designpattern.abstractfactory;

import org.lg.pay.module.controller.designpattern.factorymethod.Animal;
import org.lg.pay.module.controller.designpattern.factorymethod.Cattle;
import org.lg.pay.module.controller.designpattern.factorymethod.Horse;

/*
 * 抽象工厂--农场类
 */
interface Farm {
	Animal newAnimal();
	Plant newPlat();
}

/*
 * 具体工厂：1号工厂
 */
class FarmOne implements Farm {

	@Override
	public Animal newAnimal() {
		System.out.println("小牛出生啦。。");
		return new Cattle();
	}

	@Override
	public Plant newPlat() {
		System.out.println("蔬菜成熟啦。。");
		return new Vegetables();
	}

}

/*
 * 具体工厂：2号工厂
 */
class FarmTwo implements Farm {

	@Override
	public Animal newAnimal() {
		System.out.println("小马出生啦。。");
		return new Horse();
	}

	@Override
	public Plant newPlat() {
		System.out.println("水果成熟啦。。");
		return new Fruitage();
	}

}


