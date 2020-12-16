package com.lg.web.module.conf.mq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.lg.web.module.bean.po.Users;
import com.lg.web.module.constant.WebConstant;
import com.lg.web.module.dao.UsersMapper;

@Component
public class UserProducer {
	private final static Logger logger = LoggerFactory.getLogger(UserProducer.class);
	private RuntimeSchema<Users> schema = RuntimeSchema.createFrom(Users.class);
	
	@Autowired
	private UsersMapper usersMapper;
	
	@Autowired
	private DefaultMQProducer defaultMQProducer;
	
	public void sendMsgByMq(String id,String tags) {
		Users users = this.usersMapper.selectByPrimaryKey(Integer.parseInt(id));
		if(users != null){
			byte[] cashCoinBytes = ProtostuffIOUtil.toByteArray(users, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
			Message msg = new Message(WebConstant.ROCKETMQ_PRODUCER_TOPIC,tags,users.getId().toString(),cashCoinBytes);
			try{
				SendResult sendResult = defaultMQProducer.send(msg);
				if(sendResult != null){
					logger.info("UserProducer sendMsgByMq success , Topic is:" + msg.getTopic() 
					+ " ,messageId is:" + sendResult.getMsgId());
				}
//				this.defaultMQProducer.send(msg, new SendCallback() {
//					@Override
//					public void onSuccess(SendResult sendResult) {
//						logger.info("UserProducer sendMsgByMq onSuccess , key : "+id);
//					}
//					@Override
//					public void onException(Throwable e) {
//						logger.error("UserProducer sendMsgByMq onException , key : "+id,e);
//					}
//				});
			}catch(Exception e) {
				logger.error("UserProducer sendMsgByMq Exception , key : "+id,e);
			}
		}
		
	}
}
