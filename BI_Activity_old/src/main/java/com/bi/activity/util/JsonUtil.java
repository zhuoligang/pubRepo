
package com.bi.activity.util;

import java.util.List;

import org.aspectj.weaver.BCException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
public class JsonUtil {

    /**
     * 
     * 描述：将对象格式化成json字符串
     * 
     * @since
     * @param object 对象
     * @return json字符串
     * @throws BCException
     */
    public static String toJson(Object object) throws BCException {
      try {
        return JSON.toJSONString(object, new SerializerFeature[] {
            SerializerFeature.WriteMapNullValue, SerializerFeature.DisableCircularReferenceDetect,
            SerializerFeature.WriteNonStringKeyAsString});
      } catch (Exception e) {
        throw new BCException();
      }
    }
  
    /**
     * 
     * 描述：将对象格式化成json字符串（PrettyFormat格式）
     * 
     * @author torreswang
     * @created 2017年4月1日 下午4:38:18
     * @since
     * @param object 对象
     * @return json字符串
     * @throws BCException
     */
    public static String toJsonFormat(Object object) throws BCException {
      try {
        return JSON.toJSONString(object, new SerializerFeature[] {
            SerializerFeature.WriteMapNullValue, SerializerFeature.PrettyFormat,
            SerializerFeature.DisableCircularReferenceDetect,
            SerializerFeature.WriteNonStringKeyAsString});
      } catch (Exception e) {
        throw new BCException();
      }
    }
  
    /**
     * 
     * 描述：转Map
     * 
     * @author torreswang
     * @created 2017年4月1日 下午5:00:20
     * @since
     * @param obj 对象
     * @return object
     * @throws BCException
     */
    public static Object toJsonObject(Object obj) throws BCException {
      try {
        return JSON.toJSON(obj);
      } catch (Exception e) {
        throw new BCException();
      }
    }
  
    /**
     * 
     * 描述：将json串转为对象
     * 
     * @author torreswang
     * @created 2017年4月1日 下午5:01:23
     * @since
     * @param jsonString json串
     * @param clazz 对象
     * @return
     * @throws BCException
     */
    public static <T> T fromJson(String jsonString, Class<T> clazz) throws BCException {
      try {
        if (com.alibaba.druid.util.StringUtils.isEmpty(jsonString)) {
          return null;
        }
        return (T) JSON.parseObject(jsonString, clazz);
      } catch (Exception e) {
        throw new BCException();
      }
    }
  
    /**
     * 
     * toList:将json 装换成 List<T>对象
     * 
     * @param @param jsonString
     * @param @param clazz
     * @param @return
     * @param @throws BCException 设定文件
     * @return List<T> DOM对象
     * @throws
     * @since CodingExample　Ver 1.1
     */
    public static <T> List<T> toList(String jsonString, Class<T> clazz) throws BCException {
      try {
        if (!com.alibaba.druid.util.StringUtils.isEmpty(jsonString)) {
          return JSON.parseArray(jsonString, clazz);
        }
        return null;
      } catch (Exception e) {
        throw new BCException();
      }
  
    }
  
    /**
     * 
     * 描述：暂时不开通
     * 
     * @author torreswang
     * @created 2017年4月1日 下午5:08:12
     * @since
     * @param jsonString
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unused")
    private static <T> T fromJson(String jsonString) throws Exception {
      return JSON.parseObject(jsonString, new TypeReference<T>() {}, new Feature[0]);
    }

}

