package org.lg.pay.module.controller.designpattern.buildertype;

/*
 * 抽象建造者：装修工人
 */
abstract class Decorator {
	/*
	 * 创建产品对象
	 */
	protected Parlour product = new Parlour();
	public abstract void buildWall();
	public abstract void buildTV();
	public abstract void buildSofa();
	/*
	 * 返回产品对象
	 */
	public Parlour getResult(){
		return product;
	}
}

/*
 * 具体建造者：具体装修工人1
 */
class ConcreteDecorator1 extends Decorator {

	@Override
	public void buildWall() {
		product.setWall("w1");
	}

	@Override
	public void buildTV() {
		product.setTV("TV1");
	}

	@Override
	public void buildSofa() {
		product.setSofa("sf1");
	}

}

/*
 * 具体建造者：具体装修工人2
 */
class ConcreteDecorator2 extends Decorator {

	@Override
	public void buildWall() {
		product.setWall("w2");
	}

	@Override
	public void buildTV() {
		product.setTV("TV2");
	}

	@Override
	public void buildSofa() {
		product.setSofa("sf2");
	}

}
