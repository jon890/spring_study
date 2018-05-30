package com.ict.mongo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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

	@Override
	public List<UserDto> getUserList() {
		//								List<T>   , COLLECTION_NAME
		return mongoTemplate.findAll(UserDto.class, COLLECTION_NAME);
	}

	@Override
	public UserDto getUser(UserDto userDto) {
		return mongoTemplate.findById(userDto.getId(), UserDto.class, COLLECTION_NAME);
	}

	@Override
	public UserDto changeUser(UserDto userDto) {
		Query query = new Query(Criteria.where("_id").is(userDto.getId()));
//		Query query = new Query(new Criteria("_id").is(userDto.getId()));
		
		Update update = new Update();
		update.set("name", userDto.getName());
		update.set("gender", userDto.getGender());
		update.set("address", userDto.getAddress());
		
		mongoTemplate.updateFirst(query, update, COLLECTION_NAME);
		return userDto;
		
	}

	@Override
	public void removeUser(String id) {
		mongoTemplate.remove(new Query(new Criteria("_id").is(id)), COLLECTION_NAME);
//		mongoTemplate.remove(userDto, COLLECTION_NAME);
//		매개변수로 userDto를 받으면 다음과 같이 query 객체를 만들지 않더라도 사용 가능
		
	}
	

}