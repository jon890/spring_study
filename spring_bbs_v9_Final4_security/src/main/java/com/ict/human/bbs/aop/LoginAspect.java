package com.ict.human.bbs.aop;

import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Component -> SpringFrameWork
@Named
//@Named -> JavaEE
@Aspect
public class LoginAspect {
	
	// class를 실행할 때 log 찍기
	private Logger logger = LoggerFactory.getLogger(LoginAspect.class);
	
	@Pointcut("execution(* com.ict.human.bbs.controller.BBSController.writeForm(..))")
	// PointCut의 이름(=id)가 메소드의 이름과 같다
	// 되도록이면 기능과 모두 이름을 맞춘다
	public void writeForm() {
		logger.info("LoginAspect의  writeForm 포인트컷 동작");
	}
	
	@Pointcut("execution(* com.ict.human.bbs.controller.BBSController.content(..))")
	// PointCut의 이름(=id)가 메소드의 이름과 같다
	public void content() {
		logger.info("LoginAspect의  content 포인트컷 동작");
	}
	
	
	@Around("writeForm() || content()")
	public Object human(ProceedingJoinPoint pjt) throws Throwable {
		
		// 부가기능 코드들.. 보안모듈 , 로그인, 트랜잭션 ..
		// 로그인 유무 확인
		
		HttpSession session = null;
		//HttpServletResponse resp = null;
		
		for(Object obj : pjt.getArgs()) {
			if( obj instanceof HttpSession ) {
				session = (HttpSession)obj;
			}/* else if ( obj instanceof HttpServletResponse) {
				resp = (HttpServletResponse)obj;
			}*/
		}
		
		/*if( session.getAttribute("id") != null ) {
			// 로그인이 되어있다면, 연결된 메소드의 코드 실행
			Object result = pjt.proceed();
			return result;
		} else {
			return "redirect:/login.bbs";
			//return "login"; 
			//*login.jsp로..
			//resp.sendRedirect("/human/login.bbs");
		}*/
		
		if( session.getAttribute("id") == null) {
			return "redirect:/login.bbs";
		}
		
		//Controller의 메소드 실행
		Object result = pjt.proceed();
		
		// proceed가 끝난후에 동작할 코드
		
		return result;
		
	}
	
}