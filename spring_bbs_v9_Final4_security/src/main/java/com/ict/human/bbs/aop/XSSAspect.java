package com.ict.human.bbs.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.ict.human.bbs.dto.BBSDto;


//@Component -> SpringFrameWork
//@Named -> JavaEE
@Component
@Aspect
public class XSSAspect {
	
	
	@Pointcut("execution(* com.ict.human.bbs.controller.BBSController.write(..))")
	// PointCut의 이름(=id)가 메소드의 이름과 같다
	// 되도록이면 기능과 모두 이름을 맞춘다
	public void write() {	
	}
	
	@Pointcut("execution(* com.ict.human.bbs.controller.BBSController.reply(..))")
	public void reply() {
	}
	
	@Pointcut("execution(* com.ict.human.bbs.controller.BBSController.update(..))")
	public void update() {
	}
	
	@Around("write() || reply() || update()")
//	ProceedingJoinPoint는 매개변수들을 사용할 수 있다
//  pjt.getArgs()로 매개변수들을 Object형 배열에 담을 수 있음
	public Object human(ProceedingJoinPoint pjt) throws Throwable {
		
		BBSDto article = null;
		

		for(Object obj : pjt.getArgs()) {
			if( obj instanceof BBSDto ) {
				article = (BBSDto)obj;
			}
		}
		
		//System.out.println((int)article.getContent().toCharArray()[0]);
		
		article.setTitle(article.getTitle().replace("<", "&lt;").replace(">", "&gt;"));
//		article.setContent(article.getContent().replace("<", "&lt;").replace(">", "&gt;")
//				.replace("시발", "*신발"));
		article.setContent(article.getContent().replace("<", "&lt;").replace(">", "&gt;")
				.replace("시발", "*신발").replaceAll("\t", "&#x09;").replaceAll("\r\n", "<br>").replaceAll("\u0020", "&nbsp;"));
		
		
		
		
		//Controller의 메소드 실행
		Object result = pjt.proceed();
		
		// proceed가 끝난후에 동작할 코드
		
		return result;
		
	}
	
}