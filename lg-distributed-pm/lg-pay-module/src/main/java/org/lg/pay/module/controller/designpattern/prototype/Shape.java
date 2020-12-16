package org.lg.pay.module.controller.designpattern.prototype;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * 原型模式--具体原型类
 */
class Realizetype implements Cloneable {
	
	Realizetype() {
		System.out.println("具体原型创建成功！");
	}

	public Object clone() throws CloneNotSupportedException{
		System.out.println("具体原型复制成功！");
		return (Realizetype)super.clone();
	}
}

/*
 * 奖状类
 */
class Citation implements Cloneable{
	public String name;
	public String info;
	public String college;
	
	public Citation(String name, String info, String college) {
		super();
		this.name = name;
		this.info = info;
		this.college = college;
		System.out.println("奖状创建成功！");
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void display(){
		System.out.println(name + info + college);
	}
	
	public Object clone() throws CloneNotSupportedException{
		System.out.println("奖状拷贝成功！");
		return (Citation)super.clone();
	}

}

interface Shape extends Cloneable{
	//克隆方法
	public Object clone();
	//计算面积
	public void countArea();
}

class Circle implements Shape{
	
	public Object clone(){
		Circle c =null;
		try {
			c = (Circle)super.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println("拷贝圆失败!");
		}
		return c;
	}
	
	public void countArea(){
		int r = 0;
		System.out.println("这是一个圆，请输入圆的半径：");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		r = input.nextInt();
		System.out.println("该圆的面积为：" + Math.PI*(Math.pow(r, 2)) + "\n");
	}

}

class Square implements Shape{
	
	public Object clone(){
		Square s = null;
		try {
			s = (Square)super.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println("拷贝正方形失败！");
		}
		return s;
	}
	
	public void countArea(){
		int l = 0;
		System.out.println("请输入正方形的边长：");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		l = input.nextInt();
		System.out.println("该正方形的面积为：" + Math.pow(l, 2) + "\n");
		
	}

}

class ProtoTypeManager {
	
	private Map<String,Shape> map = new HashMap<>();

	public ProtoTypeManager() {
		map.put("Circle", new Circle());
		map.put("Square", new Square());
	}
	
	public void addShape(String key,Shape obj){
		map.put(key, obj);
	}
	
	public Shape getShape(String key){
		Shape s = map.get(key);
		return (Shape)s.clone();
	}

}

