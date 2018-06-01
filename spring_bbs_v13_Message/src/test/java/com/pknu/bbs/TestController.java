package com.pknu.bbs;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.pknu.bbs.dao.BBSDao;

@RunWith(SpringJUnit4ClassRunner.class)
//스프링 4 부터는 클래스 베이스의 테스트도 가능함
//Spring 4.0.4, context loaders may choose to support path-based and 
//				class-based resources simultaneously
//이렇게도 사용 가능 @ContextConfiguration(classes = {AppConfig.class})

//message 기능을 구현하면서 Page 클래스를 만들었기 때문에 bbs 패키지에 있는 Page와 충돌이 나서
//Junit에서는 구동이 안됨...그러나..실제 프로젝트 구동시에는 각각 서로 다른 서블릿이 스캔하므로
//실제 구동시에는 사용가능함...아래와 같이 사용하면
//@ContextConfiguration(locations={"classpath:/spring/*.xml"})
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring/root-context.xml","classpath:spring/servlet-context.xml"})
//@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class TestController {
	private static final Logger logger = LoggerFactory.getLogger("TestController.class");
	@Autowired
	BBSDao bbsDao;
	
	@Before
	public void human(){
		logger.info("시작전");
	}	
	
	@Test
	public void test(){
		logger.info("총글의 갯수는"+bbsDao.getArticleCount());
	}
	
	
	
	
	
	

}
