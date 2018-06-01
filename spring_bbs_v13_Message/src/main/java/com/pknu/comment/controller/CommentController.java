package com.pknu.comment.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pknu.comment.dto.CommentDto;
import com.pknu.comment.service.CommentService;

@Controller
public class CommentController {
	@Autowired
	CommentService commentService;
	List<CommentDto> commentList=null;

	@RequestMapping(value="/commentRead.comment")
	@ResponseBody
	public List<CommentDto> commentRead(@RequestParam int articleNum, 
										@RequestParam int commentRow){							
		return commentService.getComments(articleNum,commentRow);	
		
	}
	
	@RequestMapping(value="/commentWrite.comment")
	public @ResponseBody HashMap<String, Object> commentWrite(CommentDto comment, 
															   HttpSession session){
		comment.setId((String)session.getAttribute("id"));
		commentService.insertComment(comment);
		commentList=commentService.getComments(comment.getArticleNum(),10);
		HashMap<String, Object> hm = new HashMap<>();
		hm.put("result", 1);
		hm.put("commentList", commentList);
		return hm;		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
