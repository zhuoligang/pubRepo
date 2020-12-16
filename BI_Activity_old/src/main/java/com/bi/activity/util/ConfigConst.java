package com.bi.activity.util;

/**
 * 
 * ClassName:ConfigConst
 * 
 * @author torreswang
 * @version
 * @since Ver 1.1
 * @Date 2017 2017年8月10日 下午7:26:34
 * @see
 */
public class ConfigConst {

    public static String POST_URL_CheckLog;

    public static String TESTURL;

    public static void getConfigConst() throws Exception {
        POST_URL_CheckLog = ConfigManager.getInstance().getConfigItem("POST_URL_CheckLog");
        TESTURL = ConfigManager.getInstance().getConfigItem("testURL");
    }
}
