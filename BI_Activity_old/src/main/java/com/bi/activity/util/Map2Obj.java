/**
 * Map2Obj.java com.ejie.nbrs.util
 * 
 * Function： TODO
 * 
 * ver date author ────────────────────────────────── ver1.0 2017年11月20日 zlg
 * 
 * Copyright (c) 2017, EJie All Rights Reserved.
 */

package com.bi.activity.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cglib.beans.BeanMap;

/**
 * ClassName:Map2Obj（Describe this Class）
 * 
 * @author zlg
 * @version Ver 1.0
 * @Date 2017年11月20日 下午3:45:45
 * @see
 */
public class Map2Obj {

  /**
   * 将map装换为javabean对象
   * 
   * @param map
   * @param bean
   * @return
   */
  public static <T> T mapToBean(Map<String, Object> map, T bean) {
    BeanMap beanMap = BeanMap.create(bean);
    beanMap.putAll(map);
    return bean;
  }

  public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
    if (map == null)
      return null;

    Object obj = beanClass.newInstance();

    Field[] fields = obj.getClass().getDeclaredFields();
    for (Field field : fields) {
      int mod = field.getModifiers();
      if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
        continue;
      }

      field.setAccessible(true);
      field.set(obj, map.get(field.getName()));
    }

    return obj;
  }

  public static Map<String, Object> objectToMap(Object obj) throws Exception {
    if (obj == null) {
      return null;
    }

    Map<String, Object> map = new HashMap<String, Object>();

    Field[] declaredFields = obj.getClass().getDeclaredFields();
    for (Field field : declaredFields) {
      field.setAccessible(true);
      map.put(field.getName(), field.get(obj));
    }

    return map;
  }

}
