package com.ict.human.bbs.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ict.human.bbs.security.CSRFManager;

public class CSRFInterceptor extends HandlerInterceptorAdapter {

	
	// 컨트롤러 보다 먼저 수행된다.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
//		HttpSession session = request.getSession();
//		String CSRF_TOKEN = (String)session.getAttribute("CSRF_TOKEN");
		//System.out.println(CSRF_TOKEN);
		
		// 인터셉터를 get요청 들어올 때 처리하게끔 만들어 본것이다
		// 하지만 BBSController에 write.bbs를 get방식으로 처리하는 request mapping이 없으므로
		// was가 알아서 오류를 띄워준다. (405에러, 잘못된 접근)
//		if( !request.getMethod().equalsIgnoreCase("POST") ) {
//			//return true;
//			// 이 인터셉터가 끝나고 다른 인터셉터가 동작하게 넘어간다
//			response.sendRedirect("/human/illegalPath.bbs");
//			return false;	
//		}
 
		
//		if( CSRF_TOKEN.equals(request.getParameter("CSRF_TOKEN")) ) {
//			System.out.println("CSRF_TOKEN 확인 : " + CSRF_TOKEN);
//			return true;
//		} else {
//			System.out.println("비 정상 접근 감지");
//			response.sendRedirect("/human/list.bbs?pageNum=1");
//			return false;
//		}
		
		String sessionToken = CSRFManager.getSessionToken(request.getSession());
		String requestToken = CSRFManager.getRequestToken(request);
		
		System.out.println("session 토큰 = " + sessionToken);
		System.out.println("request hidden 토큰 = " + requestToken);
		
		if( sessionToken != null && sessionToken.equals(requestToken)) {
			System.out.println("CSRF_TOKEN 확인 : " + sessionToken);
			return true;
		} else {
			response.sendRedirect("/human/illegalPath.bbs");
			return false;
		}
		
	}

}
