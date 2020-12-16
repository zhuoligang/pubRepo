package com.lg.web.module.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WebConstant {
	// 通讯成功
	public static final int CODE_200 = 200;
	// 非法，ip非法等
	public static final int CODE_300 = 300;
	// 失败
	public static final int CODE_500 = 500;
	// 需登录
	public static final int CODE_600 = 600;
	
	public static final String SUCCESS = "success";
	
	public static final String FAILURE = "failure";
	
	public static final String KEYWORDS_SUCCESS = "成功";
	
	public static final String KEYWORDS_FAILURE = "失败";
	
	public static final String FILL_IN_REQUIRED ="请规范填写必填内容";
	
	public static String ROCKETMQ_PRODUCER_TOPIC;
	@Value("${rocketmq.producer.topic}")
	public void setROCKETMQ_PRODUCER_TOPIC(String rOCKETMQ_PRODUCER_TOPIC) {
		ROCKETMQ_PRODUCER_TOPIC = rOCKETMQ_PRODUCER_TOPIC;
	}
	
	//解密参数出现错误
	public static final String DECRYPTP_PARAMETER_ERROR = "网络繁忙，请稍后再试";
	
	public static final String TIME_INTERVAL_IS_TOO_LONG = "时间间隔太长";
}
