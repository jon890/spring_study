package com.ict.mongo.service;

import java.util.List;
import java.util.Map;

import com.ict.mongo.dto.UserDto;

public interface MongoService {
	public void insert(Map<String, String> map);
	public List<UserDto> getUserList();
	public UserDto getUser(UserDto userDto);
	public UserDto changeUser(Map<String, String> map);
	public void removeUser(String id);
}
