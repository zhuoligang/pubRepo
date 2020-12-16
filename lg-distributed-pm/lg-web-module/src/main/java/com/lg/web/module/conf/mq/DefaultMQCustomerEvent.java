package com.lg.web.module.conf.mq;

import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.context.ApplicationEvent;


/**
 * 
* @ClassName: WebMQCustomerEvent
* @Description: TODO(用户中心RocketMQ消费者监听消息事件)
* @author Administrator
* @date 2018年12月7日上午11:55:42
*
 */
public class DefaultMQCustomerEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private String topic;
	
	private String tag;

	private MessageExt msg;

	public DefaultMQCustomerEvent(Object source, String topic, String tag, MessageExt msg) {
		super(source);
		this.topic = topic;
		this.tag = tag;
		this.msg = msg;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public MessageExt getMsg() {
		return msg;
	}

	public void setMsg(MessageExt msg) {
		this.msg = msg;
	}

}
