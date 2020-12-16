package org.lg.pay.module.controller.designpattern.bridge;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BagManage {
	public static void main(String[] args) {
		Color color;
		Bag bag;
		color = (Color) ReadXML.getObject("color");
		bag = (Bag) ReadXML.getObject("bag");
		bag.setColor(color);
		bag.show();
	}
	
	

}

class ReadXML{
	public static Object getObject(String args){
		try {
			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dFactory.newDocumentBuilder();
			Document doc = builder.parse(new File("D:/src/bridge_config.xml"));
			NodeList nl = doc.getElementsByTagName("className");
			Node classNode = null;
			if("color".equals(args)){
				classNode = nl.item(0).getFirstChild();
			}else if("bag".equals(args)){
				classNode = nl.item(1).getFirstChild();
			}
			String cName = "org.lg.pay.module.controller.designPattern.bridge." + classNode.getNodeValue();
			Class<?> forName = Class.forName(cName);
			Object boj = forName.newInstance();
			return boj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}