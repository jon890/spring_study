package com.ict.mongo.dto;

public class UserDto {
	
	private String id;
	private String name;
	private String gender;
	private AddressDto address;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public AddressDto getAddress() {
		return address;
	}
	public void setAddress(AddressDto address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "UserDto [id=" + id + ", name=" + name + ", gender=" + gender + ", address=" + address + "]";
	}
	
}