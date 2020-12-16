/**
 * TestSDKUtil.java com.ejie.compliance.util
 * 
 * Function： TODO
 * 
 * ver date author ────────────────────────────────── ver1.0 2018年1月23日 zlg
 * 
 * Copyright (c) 2018, EJie All Rights Reserved.
 */

package com.bi.activity.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:TestSDKUtil 模拟资方SDK
 * 
 * @author zlg
 * @version Ver 1.0
 * @Date 2018年1月23日 下午2:22:49
 * @see
 */
public class TestSDKUtil {

  protected static Map<String, Object> mapAfter = new HashMap<String, Object>();
  final static String flowStatus = "1";
  final static String respMsg = "接口返回报文";
  final static Date respTime = new Date();
  final static String remark = "备注(备注为null或''则不是异常)";
  final static String respRemark = "接口处理结果描述(成功或失败或其他自定义描述)";

  public static Map<String, Object> creditOne() {
    mapAfter.put("flowStatus", flowStatus);
    mapAfter.put("respMsg", respMsg);
    mapAfter.put("respTime", respTime);
    mapAfter.put("remark", remark);
    mapAfter.put("respRemark", respRemark);
    return mapAfter;
  }

  public static Map<String, Object> creditOneQuery() {
    mapAfter.put("flowStatus", flowStatus);
    mapAfter.put("respMsg", respMsg);
    mapAfter.put("respTime", respTime);
    mapAfter.put("remark", remark);
    mapAfter.put("respRemark", respRemark);
    return mapAfter;
  }

  public static Map<String, Object> creditTwo() {
    mapAfter.put("flowStatus", flowStatus);
    mapAfter.put("respMsg", respMsg);
    mapAfter.put("respTime", respTime);
    mapAfter.put("remark", remark);
    mapAfter.put("respRemark", respRemark);
    return mapAfter;
  }

  public static Map<String, Object> creditTwoQuery() {
    mapAfter.put("flowStatus", flowStatus);
    mapAfter.put("respMsg", respMsg);
    mapAfter.put("respTime", respTime);
    mapAfter.put("remark", remark);
    mapAfter.put("respRemark", respRemark);
    return mapAfter;
  }

  public static Map<String, Object> loanOne() {
    mapAfter.put("flowStatus", flowStatus);
    mapAfter.put("respMsg", respMsg);
    mapAfter.put("respTime", respTime);
    mapAfter.put("remark", remark);
    mapAfter.put("respRemark", respRemark);
    return mapAfter;
  }

  public static Map<String, Object> loanOneQuery() {
    mapAfter.put("flowStatus", flowStatus);
    mapAfter.put("respMsg", respMsg);
    mapAfter.put("respTime", respTime);
    mapAfter.put("remark", remark);
    mapAfter.put("respRemark", respRemark);
    return mapAfter;
  }

  public static Map<String, Object> loanTwo() {
    mapAfter.put("flowStatus", flowStatus);
    mapAfter.put("respMsg", respMsg);
    mapAfter.put("respTime", respTime);
    mapAfter.put("remark", remark);
    mapAfter.put("respRemark", respRemark);
    return mapAfter;
  }

  public static Map<String, Object> loanTwoQuery() {
    mapAfter.put("flowStatus", flowStatus);
    mapAfter.put("respMsg", respMsg);
    mapAfter.put("respTime", respTime);
    mapAfter.put("remark", remark);
    mapAfter.put("respRemark", respRemark);
    return mapAfter;
  }

}
