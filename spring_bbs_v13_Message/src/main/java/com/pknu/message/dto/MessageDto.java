package com.pknu.message.dto;

import java.io.Serializable;

public class MessageDto implements Serializable {	
	private static final long serialVersionUID = 4264854485785501440L;
	
	private int messageNum;
	private String id;
	private String receiveId;
	private String messageContent;
	private String messageDate;
	private int messageStatus;
	public int getMessageNum() {
		return messageNum;
	}
	public void setMessageNum(int messageNum) {
		this.messageNum = messageNum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReceiveId() {
		return receiveId;
	}
	public void setReceiveId(String receiveId) {
		this.receiveId = receiveId;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public String getMessageDate() {
		return messageDate;
	}
	public void setMessageDate(String messageDate) {
		this.messageDate = messageDate;
	}
	public int getMessageStatus() {
		return messageStatus;
	}
	public void setMessageStatus(int messageStatus) {
		this.messageStatus = messageStatus;
	}
	
	@Override
	public String toString() {
		return "MessageDto [messageNum=" + messageNum + ", id=" + id + ", receiveId=" + receiveId + ", messageContent="
				+ messageContent + ", messageDate=" + messageDate + ", messageStatus=" + messageStatus + "]";
	}	
}
