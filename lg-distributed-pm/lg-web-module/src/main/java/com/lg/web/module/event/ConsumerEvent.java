package com.lg.web.module.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.lg.web.module.bean.po.Users;
import com.lg.web.module.conf.mq.DefaultMQCustomerEvent;

/**
 * RocketMQ消费者示例
 * @author ybc
 * @since 2018-09-27
 */
@Component
public class ConsumerEvent {
	private final static Logger logger = LoggerFactory.getLogger(ConsumerEvent.class);
	
	private RuntimeSchema<Users> schema = RuntimeSchema.createFrom(Users.class);
	
	@EventListener(condition = "#event.topic == 'lgTest'")
	public void rocketmqMsgListen(DefaultMQCustomerEvent event) {
		Users currency = schema.newMessage();
		ProtostuffIOUtil.mergeFrom(event.getMsg().getBody(), currency, schema);
		logger.info("监听到一个消息达到：" + JSON.toJSONString(currency));
		// 处理
		if ("add".equals(event.getTag())) {
			logger.info("满足处理要求，即将进行处理。。。。。。");
		}
	}
}
