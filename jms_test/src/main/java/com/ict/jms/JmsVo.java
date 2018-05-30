package com.ict.jms;

import java.io.Serializable;

public class JmsVo implements Serializable{
	
	private static final long serialVersionUID = 6894794688763580485L;
	private String jmsText;

	public String getJmsText() {
		return jmsText;
	}

	public void setJmsText(String jmsText) {
		this.jmsText = jmsText;
	}

	@Override
	public String toString() {
		return "JmsVo [jmsText=" + jmsText + "]";
	}
	
	
}