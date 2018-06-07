package com.ict.human.bbs.security;

import java.util.UUID;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

// 다른 클래스가 상속받을 수 없음
public final class CSRFManager {
	
	//	public static synchronized void createSession(HttpSession session, String id) {
	public static void createSession(HttpSession session, String id) {

		synchronized (session) {
			String token = (String)session.getAttribute("CSRF_TOKEN");
			if(token == null) {
				// 동기화 블럭 사용

				session.setAttribute("id", id);
				session.setAttribute("CSRF_TOKEN", UUID.randomUUID().toString());
			}
		}
	}
	
	
	public static String getSessionToken(HttpSession session) {
		return (String)session.getAttribute("CSRF_TOKEN");
	}
	
	
	public static String getRequestToken(ServletRequest request) {
		return (String)request.getParameter("CSRF_TOKEN");
	}
}