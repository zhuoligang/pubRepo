package org.lg.pay.module.controller.designpattern.factorymethod;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Test {

	public static void main(String[] args) {
		try {
			Animal a;
			AnimalFarm af;
			af = (AnimalFarm) ReadXML2.getObject();
			a = af.newAnimal();
			a.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class ReadXML2 {
	
	public static Object getObject(){
		try {
			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dFactory.newDocumentBuilder();
			Document doc;
			doc = builder.parse(new File("D:/src/config2.xml"));
			NodeList nl = doc.getElementsByTagName("className");
			Node classNode = nl.item(0).getFirstChild();
			String cName = "org.lg.pay.module.controller.designPattern.factorymethod." + classNode.getNodeValue();
			System.out.println("新类名：" + cName);
			Class<?> c = Class.forName(cName);
			Object obj = c.newInstance();
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}