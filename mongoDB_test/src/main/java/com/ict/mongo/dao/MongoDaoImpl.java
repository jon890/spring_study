package com.ict.mongo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.ict.mongo.dto.UserDto;

@Repository
public class MongoDaoImpl implements MongoDao {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	private final String COLLECTION_NAME = "ict";

	@Override
	public void insert(UserDto userDto) {	
		mongoTemplate.insert(userDto, COLLECTION_NAME);
	}

}