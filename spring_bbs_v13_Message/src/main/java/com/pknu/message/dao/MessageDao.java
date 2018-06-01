package com.pknu.message.dao;

import java.util.HashMap;
import java.util.List;

import com.pknu.message.dto.MessageDto;

public interface MessageDao {
	public void messageWrite(MessageDto messageDto);
	public int getMessageCount(String id);
	public List<MessageDto> getMessages(HashMap<String, String> paramMap);
	public MessageDto messageContent(int messageNum);
	public void updateMessageStatus(int messageNum);
	public void messageDelete(String[] delMsgs);
}
