package org.lg.pay.module.controller.designpattern.singleton;

/**
 * 
 * @ClassName: HungrySingleton
 * @Description: TODO(单例模式--饿汉)
 * @author zlg
 * @date 2019年12月31日下午2:19:23
 *
 */
public class HungrySingleton {
	/*
	 * 该模式的特点是类一旦加载就创建一个单例，保证在调用 getInstance 方法之前单例已经存在了。
	 */
	private static final HungrySingleton instance = new HungrySingleton();

	private HungrySingleton() {
	}

	public static HungrySingleton getInstance() {
		return instance;
	}

}
