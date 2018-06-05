package com.ict.human.bbs.filter;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XSSRequestWrapper extends HttpServletRequestWrapper {


	public XSSRequestWrapper(HttpServletRequest request) {
		super(request);
	}


//	파라미터가 1개 일 때 값을 읽어보기
//	@Override
//	public String getParameter(String name) {
//		System.out.println("테스트1 " + name);
//		String value = super.getParameter(name);
//		System.out.println("테스트2 : " + value);
//		return value;
//	}
	
	
	@Override
	public String[] getParameterValues(String name) {
//		System.out.println("테스트1 : " + name);
//		String[] values = super.getParameterValues(name);
//		System.out.println("테스트2 : " + values[0]);
		
		
		System.out.println("테스트1 : " + name);
		String[] values = super.getParameterValues(name);
		System.out.println("테스트2 : " + values[0]);
		if( name.equals("title") || name.equals("content") ){
			values[0] = values[0].replace("<", "&lt;").replace(">", "&gt;");
		}
		System.out.println("테스트3 : " + values[0]);
		System.out.println("=======================");
		return values;
	}

	
	

	
	
}