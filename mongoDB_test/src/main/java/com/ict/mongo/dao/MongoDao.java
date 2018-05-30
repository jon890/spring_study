package com.ict.mongo.dao;

import java.util.List;

import com.ict.mongo.dto.UserDto;

public interface MongoDao {
	public void insert(UserDto userDto);
	public List<UserDto> getUserList();
	public UserDto getUser(UserDto userDto);
	public UserDto changeUser(UserDto userDto);
	public void removeUser(String id);
}