package com.ict.mongo.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators.Add;
import org.springframework.stereotype.Service;

import com.ict.mongo.dao.MongoDao;
import com.ict.mongo.dto.AddressDto;
import com.ict.mongo.dto.UserDto;

@Service
public class MongoServiceImpl implements MongoService {
	
	@Autowired
	private MongoDao mongoDao;
	
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
	
}