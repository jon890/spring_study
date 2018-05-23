package com.ict.human;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ict.human.bbs.dao.BBSDao;

@RunWith(SpringJUnit4ClassRunner.class)

// WebAppConfiguration WAS없이 테스트 할 때 꼭 필요한 애노테이션이다
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:springConfig/root-context.xml", "classpath:springConfig/appServlet/bbs-context.xml"})
// 스프링 4 부터는 클래스 베이스의 테스트도 가능하다
// Spring 4.0.4, context loaders may choose to support path-based and
//										class-based resources simultaneously
// 이렇게도 사용 가능하다 @Contextconfiguration(classes = {AppConfig.class})
// @ContextConfiguration(locations = {"classpath:/springConfig/*.xml})
// @ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BoardTestController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardTestController.class);
	
	@Autowired
	private BBSDao bbsDao;
	
	
	@Before
	public void beforeGetTotalCount() {
		logger.info("@Test메소드 이전에 실행됩니다");
	}
	
	@Test
	public void testGetTotalCount() {
		logger.info("총글의 갯수는 : " + bbsDao.getTotalCount());
	}

}