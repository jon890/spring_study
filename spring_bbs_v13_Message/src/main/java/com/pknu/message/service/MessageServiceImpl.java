package com.pknu.message.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.pknu.message.dao.MessageDao;
import com.pknu.message.dto.MessageDto;
import com.pknu.message.jms.MessageSender;
import com.pknu.message.util.Page;

@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	MessageSender messageSender;
	
	@Autowired
	MessageDao messageDao;
	
	@Autowired
	@Qualifier("message")
	Page page;
	
	List<MessageDto> messageList;
	HashMap<String, String> paramMap;

	@Override
	public void messageWrite(MessageDto messageDto) {
		messageSender.messageSend(messageDto);		
	}

	@Override
	public void messageList(HttpSession session, int pageNum, Model model) {
		int totalCount=0;		
		int pageSize=10;//한페이지에 보여줄 글의 갯수
	    int pageBlock=10;//한 블럭당 보여줄 페이지 갯수  	
	    String id= (String)session.getAttribute("id");
	   		
		totalCount=messageDao.getMessageCount(id);
		page.paging(pageNum, totalCount, pageSize, pageBlock);
		
		paramMap= new HashMap<>();
		paramMap.put("id", id);
		paramMap.put("startRow", Integer.toString(page.getStartRow()));
		messageList=messageDao.getMessages(paramMap);		
		
		model.addAttribute("messageCount",totalCount);
		model.addAttribute("messageList",messageList);
		model.addAttribute("pageCode",page.getSb().toString());		
	}

	@Override
	public MessageDto messageContent(int messageNum, int messageStatus) {
		MessageDto message = messageDao.messageContent(messageNum); 
		
		if(messageStatus==1){
			messageDao.updateMessageStatus(messageNum);
		}
		return message;
	}

	@Override
	public void messageDelete(String[] delMsgs) {
		messageDao.messageDelete(delMsgs);
	}
	
	
	
	
	
	

}
