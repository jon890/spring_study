package com.ict.mongo.dao;

import com.ict.mongo.dto.UserDto;

public interface MongoDao {
	public void insert(UserDto userDto);
}