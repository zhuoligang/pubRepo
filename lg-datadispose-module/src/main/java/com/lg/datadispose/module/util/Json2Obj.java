package com.lg.datadispose.module.util;


import com.alibaba.fastjson.JSONObject;

/**
 * 
 * ClassName:Json2Obj
 * 
 * @author zlg
 * @version 版本
 * @since Ver 1.1
 * @Date 2017年8月10日 上午10:44:12
 * @ModifiedBy 修改人
 * @Copyright 公司名称
 * 
 */
public class Json2Obj {
  /*
   * public static void main(String[] args) throws ParseException { JSONObject jsonObj = new
   * JSONObject();
   * 
   * jsonObj.put("loanLimit",6); jsonObj.put("serialNo","YJCD201705060000101");
   * jsonObj.put("loanAmount",500000.00); jsonObj.put("repayMethod","先息后本");
   * jsonObj.put("realName","张三"); jsonObj.put("loanDate","2017-05-06");
   * 
   * //
   * {"result":{"count":1,"data":[{"loanLimit":6,"serialNo":"YJCD201705060000101","loanAmount":500000.00
   * , // "repayMethod":"先息后本","realName":"张三","loanDate":"2017-5-06"}]},"code":0,"msg":"ok"}
   * 
   * JSONObject jsonObj2 = new JSONObject(); jsonObj2.put("serialNo","流水号1");
   * jsonObj2.put("city",jsonObj); jsonObj2.put("applyDate",jsonObj);
   * jsonObj2.put("garantyType",jsonObj); jsonObj2.put("applyAmount",jsonObj);
   * jsonObj2.put("data",jsonObj); jsonObj2.put("data",jsonObj); jsonObj2.put("data",jsonObj);
   * jsonObj2.put("data",jsonObj);
   * 
   * JSONObject jsonObj3 = new JSONObject(); jsonObj3.put("code",0); jsonObj3.put("msg","ok");
   * jsonObj3.put("result",jsonObj2);
   * 
   * System.out.println(jsonObj3.toString());
   * 
   * String st = "{\"msg\":\"ok\",\"result\":{\"data\":[" +
   * "{\"realName\":\"张三\",\"repayMethod\":\"先息后本\"" +
   * ",\"loanLimit\":6,\"loanDate\":\"2017-05-06\",\"loanAmount\":500000," +
   * "\"serialNo\":\"YJCD201705060000101\"}," + "{\"realName\":\"李四\",\"repayMethod\":\"先息后本\"" +
   * ",\"loanLimit\":6,\"loanDate\":\"2017-05-06\",\"loanAmount\":500000," +
   * "\"serialNo\":\"YJCD201705060000101\"}],\"count\":1},\"code\":0}";
   * 
   * System.out.println(Json2Obj.j2o(st, EjieLoanParent.class));
   * 
   * }
   */

	/**
	 * 字符串转对象
	 * @param str
	 * @param clazz
	 * @return
	 */
  public static <T> T j2o(String str, Class<T> clazz) {

    JSONObject jsonObject = JSONObject.parseObject(str);

    return (T) JSONObject.toJavaObject(jsonObject, clazz);

  }
/**
 * JSONObject转对象
 * @param str
 * @param clazz
 * @return
 */
  public static <T> T j2o(JSONObject str, Class<T> clazz) {

    return (T) JSONObject.toJavaObject(str, clazz);

  }
  /**
   * 对象转对象
   * @param clazzIn
   * @param clazzOut
   * @return
   */
  public static <T> T o2o(Object clazzIn, Class<T> clazzOut) {
	  String json = JSONObject.toJSONString(clazzIn); 
	  Json2Obj.j2o(json, clazzOut);
	    return (T) Json2Obj.j2o(json, clazzOut);
	  }
  /**
   * 对象集合转集合对象
   * @param clazzIn
   * @param clazzOut
   * @return
   */
//  public static <T> List<T> os2os(Object object,Class<T> clazzIn, Class<T> clazzOut) {
//	  object =new ArrayList<clazzIn.>();
//      List<T> list = new ArrayList<T>();
//	  for (Object o : objects) {
//		list.add(Json2Obj.o2o(o, clazzOut));
//	  }
//	    return list;
//	}
  
}
