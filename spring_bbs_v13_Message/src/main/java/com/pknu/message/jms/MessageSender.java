package com.pknu.message.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.pknu.message.dto.MessageDto;

@Component
public class MessageSender {
	@Autowired
	JmsTemplate jmsTemplate;
	
	public void messageSend(MessageDto messageDto){
		jmsTemplate.send(new MessageCreator() {			
			@Override
			public Message createMessage(Session sess) throws JMSException {				
				return sess.createObjectMessage(messageDto);
			}
		});
	}

}
