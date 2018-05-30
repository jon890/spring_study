package com.ict.jms;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class ReceiverJms implements MessageListener{

	private JmsVo jv;
	
	@Override
	public void onMessage(Message message) {
		System.out.println(message + "111");
		
		try {
			jv = (JmsVo) ( ((ObjectMessage)message).getObject() );
			System.out.println("리시버에서 받은 메시지"+jv.getJmsText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}