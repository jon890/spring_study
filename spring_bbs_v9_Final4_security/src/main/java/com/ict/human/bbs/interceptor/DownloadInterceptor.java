package com.ict.human.bbs.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class DownloadInterceptor extends HandlerInterceptorAdapter {

	
	// 컨트롤러 보다 먼저 수행된다.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String sessionArticleNum = (String)request.getSession().getAttribute("articleNum");
		String requestArticleNum = request.getParameter("articleNum");
		
		if( sessionArticleNum != null && sessionArticleNum.equals(requestArticleNum)) {
			return true;
		} else {
			response.sendRedirect("/human/illegalPath.bbs");
			return false;
		}
		
	}

}
