package com.bi.activity.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.io.Resources;

import com.alibaba.fastjson.JSONObject;

public class StringExUtil {

    /**
     * @param request
     * @param type
     *            输入"房"或者"车"
     * @param houseArea
     *            输入类似{四川省}{成都市}{高新区}这类字符串，允许出现{xxx}{xxx}{##}这类为一般直辖市情况
     * @return 输出类似H/C+0561此类字符串，其中H为房，C为车，0561为区号
     * @throws Exception
     */
    public static String getAreaCode(String type, String houseArea, HttpServletRequest request) throws Exception {
        if ("房".equals(type)) {
            type = "H";
        } else if ("车".equals(type)) {
            type = "C";
        } else {
            return "第一参数格式错误";
        }
        if (houseArea != null) {
            houseArea = houseArea.replace(" ", "");
        }
        if ("".equals(houseArea) || houseArea == null || "undefined".equals(houseArea)
                || "{##}{##}{##}".equals(houseArea) || "null".equals(houseArea)) {
            return "第二参数格式错误";
        } else if (!(houseArea.contains("{") && houseArea.contains("}") && houseArea.contains("}{"))) {
            return "第二参数格式错误";
        } else {
            // 获取城市字符串
            String city = "";
            houseArea = houseArea.replace("}{", ",");
            houseArea = houseArea.replace("{", "");
            houseArea = houseArea.replace("}", "");
            String[] split = houseArea.split(",");
            // if ("##".equals(split[2])) {
            // city = split[0];
            // } else {
            // city = split[1];
            // }
            for (int i = 0; i < split.length; i++) {
                if (split[i].contains("市")) {
                    city = split[i];
                }
            }
            city = city.replace("市", "");

            BufferedReader br = null;
            InputStreamReader isr = null;
            try {
                isr = new InputStreamReader(Resources.getResourceAsStream("/XWSource/areaCode.txt"), "UTF-8");
                /*
                 * isr = new InputStreamReader(new
                 * FileInputStream(request.getSession().getServletContext()
                 * .getRealPath("resource\\doc\\areaCode.txt")), "UTF-8");
                 */
                br = new BufferedReader(isr);
                String jsonString = "";
                String line = null;
                while ((line = br.readLine()) != null) {
                    if (line != null) {
                        jsonString = jsonString + line;
                    }
                }
                jsonString = jsonString.replace(" ", "");
                JSONObject jsonObject = JSONObject.parseObject(jsonString);
                List list = (List) jsonObject.get("code");
                for (int i = 0; i < list.size(); i++) {
                    Map map = (Map) list.get(i);
                    if (map.get("name").equals(city)) {
                        houseArea = (String) map.get("zip");
                    }
                }
                if (houseArea.length() == 3) {
                    houseArea = "0" + houseArea;
                } else if (houseArea.length() == 2) {
                    houseArea = "00" + houseArea;
                } else if (houseArea.length() == 1) {
                    houseArea = "000" + houseArea;
                } else if (houseArea.length() == 0) {
                    throw new Exception("解析json异常");
                }
            } catch (Exception e) {
                throw new Exception("解析json异常");
            } finally {
                try {
                    br.close();
                    isr.close();
                } catch (Exception e) {
                    throw new Exception("读取流关闭异常");
                }
            }
        }
        return type + houseArea;
    }

    /**
     * 
     * @param date
     *            日期格式化字符串
     * @param oldPattern
     *            旧格式
     * @param newPattern
     *            新格式
     * @return 新格式日期格式字符串
     */
    public static String StringPattern(String date, String oldPattern, String newPattern) {
        if (date == null || oldPattern == null || newPattern == null) {
            return "传入参数错误";
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat(oldPattern); // 实例化模板对象
        SimpleDateFormat sdf2 = new SimpleDateFormat(newPattern); // 实例化模板对象
        Date d = null;
        try {
            d = sdf1.parse(date); // 将给定的字符串中的日期提取出来
        } catch (Exception e) { // 如果提供的字符串格式有错误，则进行异常处理
            e.printStackTrace(); // 打印异常信息
        }
        return sdf2.format(d);
    }

    /**
     * 
     * compare:(比较两个相同类的对象的不同属性值，返回不同属性的map)
     * 
     * @param oldObj
     *            老数据对象
     * @param newObj
     *            新数据对象
     * @return 返回不同属性的map，如{userName=黄振威:李离, score=1:2}这种格式，黄振威和1为老数据属性值
     * @throws
     */
    public static <T> Map<String, String> compare(T oldObj, T newObj) {

        Map<String, String> result = new LinkedHashMap<String, String>();

        Field[] fs = oldObj.getClass().getDeclaredFields();
        for (Field f : fs) {
            f.setAccessible(true);
            try {
                Object v1 = f.get(oldObj);
                Object v2 = f.get(newObj);
                if (!equals(v1, v2)) {
                    result.put(f.getName(), v1 + ":" + v2);
                }
            } catch (Exception e) {
                System.out.println("比较compare异常");
            }

        }
        return result;
    }

    /**
     * 
     * equals:(Describe the function of this method)
     * 
     * @param obj1
     * @param obj2
     * @return
     * @throws
     */
    public static boolean equals(Object obj1, Object obj2) {

        if (obj1 == obj2) {
            return true;
        }
        if (obj1 == null || obj2 == null) {
            return false;
        }
        return obj1.equals(obj2);
    }

}
