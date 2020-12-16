package org.lg.pay.module.controller.designpattern.adapter;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
 * 目标：发动机
 */
interface Motor {
	void drive();
}

/*
 * 适配者1：电能发动机
 */
class ElectricMotor {
	public void electricDrive() {
		System.out.println("电能发动机驱动汽车！");
	}
}

/*
 * 适配者2：光能发动机
 */
class OpticalMotor {
	public void opticalDrive() {
		System.out.println("光能发动机驱动汽车！");
	}
}

/*
 * 电能适配器
 */
class ElectricAdapter implements Motor {
	private ElectricMotor emotor;

	public ElectricAdapter() {
		emotor = new ElectricMotor();
	}

	@Override
	public void drive() {
		emotor.electricDrive();
	}

}

/*
 * 光能适配器
 */
class OpticalAdapter implements Motor{
	
	private OpticalMotor omotor;
	
	public OpticalAdapter() {
		omotor = new OpticalMotor();
	}

	@Override
	public void drive() {
		omotor.opticalDrive();
	}
	
}

class ReadXML{
	public static Object getObject(){
		try {
			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dFactory.newDocumentBuilder();
			Document doc = builder.parse(new File("D:/src/adapter_config.xml"));
			NodeList nl = doc.getElementsByTagName("className");
			Node firstChild = nl.item(0).getFirstChild();
			String cName = "org.lg.pay.module.controller.designPattern.adapter." + firstChild.getNodeValue();
			Class<?> forName = Class.forName(cName);
			Object obj = forName.newInstance();
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
}

