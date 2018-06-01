package com.pknu.message.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pknu.message.dto.MessageDto;
import com.pknu.message.service.MessageService;

@Controller
public class MessageController {
	@Autowired
	MessageService messageService;
	
	@RequestMapping(value="/messageMain.message")
	public String messageMain(@ModelAttribute("receiveId") String receiveId){
		return "messageMain";		
	}
	
	@RequestMapping(value="/messageForm.message")
	public String messageForm(@ModelAttribute("receiveId") String receiveId){		
		return "messageForm";
	}
	
	@RequestMapping(value="/messageWrite.message")
	public String messageWrite(MessageDto messageDto, HttpSession session){
		messageDto.setId((String)session.getAttribute("id"));
		messageService.messageWrite(messageDto);		
		return "messageResult";
	}
	
	@RequestMapping("/messageList.message")
	public String messageList(HttpSession session, @ModelAttribute("pageNum") int pageNum, Model model){
		messageService.messageList(session,pageNum,model);		
		return "messageList";
	}
	
	@RequestMapping(value="/messageContent.message")
	public String messageContent(@RequestParam("messageNum") int messageNum,
								 @ModelAttribute("pageNum") int pageNum,
								 @RequestParam("messageStatus") int messageStatus,
								 Model model){
		model.addAttribute("message", messageService.messageContent(messageNum,messageStatus));
		return "messageContent";
	}
	
	
//	@RequestMapping(value="messageDelete.message", method=RequestMethod.POST)
//	public String messageDelete(String[] delMsgs, @RequestParam("pageNum") int pageNum){
//		
//		if( delMsgs != null) {
//			messageService.messageDelete(delMsgs);	
//		}
//		
//		return "redirect:/messageList.message?pageNum=" + pageNum;
//	}
	
	
	
	@RequestMapping(value="messageDelete.message", method=RequestMethod.POST)
	@ResponseBody
	public String messageDelete(String[] delMsgs, @RequestParam("pageNum") int pageNum){
		
		
		if( delMsgs != null) {
			messageService.messageDelete(delMsgs);	
		}
		
		return "deleted";
	}

}
