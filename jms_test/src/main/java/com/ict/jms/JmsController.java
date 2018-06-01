package com.ict.jms;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class JmsController {
	
	@Resource
	private SenderJms senderJms;
	//private final static Logger logger = LoggerFactory.getLogger(JmsController.class);
	
	@RequestMapping(value = "/jmstest.ict", method = RequestMethod.GET)
	public String home() {
		senderJms.sendJms();

		return "home";
	}
	
}
