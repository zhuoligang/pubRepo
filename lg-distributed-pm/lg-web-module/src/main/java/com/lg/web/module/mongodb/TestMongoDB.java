package com.lg.web.module.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class TestMongoDB {

	@Autowired
	private MongoTemplate mongoTemplate; 
	
	public Object find(String collectionName){
		Query query = new Query();
		query.with(new Sort(Direction.DESC, "asd"));
//		query.limit(1);
		return this.mongoTemplate.findOne(query, Object.class, collectionName);
	}
	
	
}
