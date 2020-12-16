package com.lg.web.module.conf.mq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
* @ClassName: WebMQProducerConfiguration
* @Description: TODO(用户中心消息生产者配置)
* RocketMq默认消息生产者 
* Singleton模式 
* Topic:同个业务模块相同 
* Tag:同个业务模块不同类型的消息区分
* @author zlg
* @date 2019年5月30日下午4:00:57
*
 */
@Configuration
public class DefaultMQProducerConfiguration {
	
	private final static Logger logger = LoggerFactory.getLogger(DefaultMQProducerConfiguration.class);
	
	/* 所属的组名 */
	@Value("${rocketmq.producer.groupName}")
	private String producerGroup;
	
	/* MQ地址 */
	@Value("${rocketmq.producer.namesrvAddr}")
	private String producerNamesrvAddr;
	
	/* 客户端实例名称 */
	@Value("${rocketmq.producer.instanceName}")
	private String producerInstanceName;
	
	@Bean(name="defaultProducer")
	public DefaultMQProducer getDefaultProducer() {
		DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
		producer.setNamesrvAddr(producerNamesrvAddr);
		producer.setInstanceName(producerInstanceName);
		producer.setRetryTimesWhenSendAsyncFailed(0);
		producer.setVipChannelEnabled(false);
		try {
			producer.start();
		}catch(Exception e) {
			logger.error("DefaultMQProducerConfiguration defaultProducer create...",e);
			return null;
		}
		return producer;
	}
}
