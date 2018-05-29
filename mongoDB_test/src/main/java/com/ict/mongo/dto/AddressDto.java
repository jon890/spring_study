package com.ict.mongo.dto;

public class AddressDto {
	
	private String si;
	private String gu;
	
	public String getSi() {
		return si;
	}
	public void setSi(String si) {
		this.si = si;
	}
	public String getGu() {
		return gu;
	}
	public void setGu(String gu) {
		this.gu = gu;
	}
	
	@Override
	public String toString() {
		return "AddressDto [si=" + si + ", gu=" + gu + "]";
	}
	
}