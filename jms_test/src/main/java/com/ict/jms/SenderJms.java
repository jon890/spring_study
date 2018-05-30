package com.ict.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class SenderJms {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	public void sendJms() {
		final JmsVo jv = new JmsVo();
		jv.setJmsText("jms 서비스 클라이언트");
		jmsTemplate.send(
						new MessageCreator() {
							@Override
							public Message createMessage(Session session) throws JMSException {
								System.out.println("보내는곳");
								// 메시지 생성
								return session.createObjectMessage(jv);
							}
						});
		System.out.println("메시지 전송완료");
	}
	
	
	
}