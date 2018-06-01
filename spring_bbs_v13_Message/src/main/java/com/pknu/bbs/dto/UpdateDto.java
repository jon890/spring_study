package com.pknu.bbs.dto;

import java.util.ArrayList;

public class UpdateDto {
	private ArrayList<String> storedFnameList;

	public ArrayList<String> getStoredFnameList() {
		return storedFnameList;
	}

	public void setStoredFnameList(ArrayList<String> storedFnameList) {
		this.storedFnameList = storedFnameList;
	}

	@Override
	public String toString() {
		return "UpdateDto [storedFnameList=" + storedFnameList + "]";
	}

	
	
	
	
	

}
