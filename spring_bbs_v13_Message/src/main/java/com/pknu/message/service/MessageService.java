package com.pknu.message.service;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.pknu.message.dto.MessageDto;

public interface MessageService {
	public void messageWrite(MessageDto messageDto);
	public void messageList(HttpSession session,int pageNum,Model model);
	public MessageDto messageContent(int messageNum,int messageStatus);
	public void messageDelete(String[] delMsgs);
}
