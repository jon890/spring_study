package com.ict.human.bbs.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class XSSFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//request 처리
		XSSRequestWrapper RequestWrapperReq = new XSSRequestWrapper((HttpServletRequest)request);

		chain.doFilter(RequestWrapperReq, response);
		
		//response 처리

	}

}