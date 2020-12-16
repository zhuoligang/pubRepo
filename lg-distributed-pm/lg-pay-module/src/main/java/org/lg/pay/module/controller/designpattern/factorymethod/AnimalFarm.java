package org.lg.pay.module.controller.designpattern.factorymethod;

/*
 * 抽象工厂：畜牧场
 */
interface AnimalFarm {

	public Animal newAnimal();
}

/*
 * 具体工厂：养牛场
 */
class CattleFarm implements AnimalFarm {

	@Override
	public Animal newAnimal() {
		System.out.println("新牛出生啦。。。");
		return new Cattle();
	}

}

/*
 * 具体工厂：养马场
 */
class HorseFarm implements AnimalFarm {

	@Override
	public Animal newAnimal() {
		System.out.println("新马出生啦。。。");
		return new Horse();
	}

}