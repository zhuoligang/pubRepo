package org.lg.pay.module.controller.designpattern.singleton;

/**
 * 
 * @ClassName: LazySingleton
 * @Description: TODO(单例模式--懒汉)
 * @author zlg
 * @date 2019年12月31日下午2:13:06
 *
 */
public class LazySingleton {
	/*
	 * 该模式的特点是类加载时没有生成单例，只有当第一次调用 getlnstance 方法时才去创建这个单例。
	 */
	// 保证instance在所有线程中同步
	private static volatile LazySingleton instance = null;

	// private 避免被外部实例化
	private LazySingleton() {
	}

	public static synchronized LazySingleton getInstance() {
		// getInstance 方法前加同步
		if (instance == null) {
			instance = new LazySingleton();
		}
		return instance;
	}

}
