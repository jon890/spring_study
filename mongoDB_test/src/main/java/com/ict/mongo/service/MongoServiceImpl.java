package com.ict.mongo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.mongo.dao.MongoDao;
import com.ict.mongo.dto.AddressDto;
import com.ict.mongo.dto.UserDto;

@Service
public class MongoServiceImpl implements MongoService {
	
	@Autowired
	private MongoDao mongoDao;
	
	
	
	@Override
	public List<UserDto> getUserList() {
		return mongoDao.getUserList();
	}
	

	@Override
	public UserDto getUser(UserDto userDto) {
		return mongoDao.getUser(userDto);
	}


	@Override
	public void insert(Map<String, String> map) {
		
		String address = map.get("address");
		String si = address.substring(0, address.indexOf(" "));
		String gu = address.substring(address.indexOf(" ") + 1);
		
		AddressDto addressDto = new AddressDto();
		addressDto.setSi(si);
		addressDto.setGu(gu);
		
		UserDto userDto = new UserDto();
		userDto.setName(map.get("name"));
		userDto.setGender(map.get("gender"));
		userDto.setAddress(addressDto);

		mongoDao.insert(userDto);
	}


	
	@Override
	public UserDto changeUser(Map<String, String> map) {
		
		AddressDto addressDto = new AddressDto();
		addressDto.setSi(map.get("address1"));
		addressDto.setGu(map.get("address2"));
		
		UserDto userDto = new UserDto();
		userDto.setAddress(addressDto);
		userDto.setGender(map.get("gender"));
		userDto.setId(map.get("id"));
		userDto.setName(map.get("name"));
		
		return mongoDao.changeUser(userDto);
	
	}


	@Override
	public void removeUser(String id) {
		mongoDao.removeUser(id);
	}
	
	
	
}