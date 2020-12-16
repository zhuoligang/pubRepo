package com.lg.web.module.interceptor;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.lg.web.module.annotation.OperateNote;
import com.lg.web.module.conf.mq.UserProducer;
import com.lg.web.module.constant.NoteTypeEnum;

/**
 * 
* @ClassName: NoteTypeAspect
* @Description: TODO(拦截@OperateNote注解，处理不同业务)
* 只用于需要选择性的触发不同校验方式的业务，已经指定校验方式的不适合此切面
* @author Administrator
* @date 2019年1月14日下午5:41:38
*
 */
@Aspect
@Component
public class NoteTypeAspect {
	private final static Logger logger = LoggerFactory.getLogger(NoteTypeAspect.class);
	
	@Autowired
	private UserProducer userProducer;
	

	@Before("@annotation(operateNote)")
	public void doNote(JoinPoint joinPoint,OperateNote operateNote){
		logger.info("NoteTypeAspect will start doNote...");
		Object data = joinPoint.getArgs()[0];
//		logger.info("data is : " + data);
		String result = JSON.toJSONString(data);
		String id = JSON.parseObject(result).getString("id");
		this.userProducer.sendMsgByMq(id, "add");
		//获取本次请求需要进行的短信处理类型
		NoteTypeEnum NoteType = operateNote.noteType();
		logger.info("NoteType is :" + NoteType.getType());
		
	}

}
