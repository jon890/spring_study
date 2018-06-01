package com.pknu.message.jms;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.pknu.message.dao.MessageDao;
import com.pknu.message.dto.MessageDto;

public class MessageReceiver implements MessageListener {
	MessageDto messageDto;
	
	@Autowired
	MessageDao messageDao;

	@Override
	public void onMessage(Message message) {
		if(message instanceof ObjectMessage){					
			try{	
				messageDto=(MessageDto)((ObjectMessage) message).getObject();
				messageDao.messageWrite(messageDto);
			}catch(Exception e){
				e.printStackTrace();
			}
		}

	}

}
