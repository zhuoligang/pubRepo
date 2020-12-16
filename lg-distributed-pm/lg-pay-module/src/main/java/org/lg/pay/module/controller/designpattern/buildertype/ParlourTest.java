package org.lg.pay.module.controller.designpattern.buildertype;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ParlourTest {
	public static void main(String[] args) {
		try {
			Decorator d = (Decorator) ReadXML.getObject();
			ProjectManager m = new ProjectManager(d);
			Parlour p = m.decorate();
			p.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
class ReadXML {
	public static Object getObject(){
		try {
			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dFactory.newDocumentBuilder();
			Document doc;
			doc = builder.parse(new File("D:/src/builder_config.xml"));
			NodeList nl = doc.getElementsByTagName("className");
			Node classNode = nl.item(0).getFirstChild();
			String cName = "org.lg.pay.module.controller.designPattern.buildertype." + classNode.getNodeValue();
			System.out.println("新类名：" + cName);
			Class<?> c = Class.forName(cName);
			Object obj = c.newInstance();
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
