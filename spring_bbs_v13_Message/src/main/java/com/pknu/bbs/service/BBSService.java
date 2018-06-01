package com.pknu.bbs.service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.io.FileSystemResource;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.pknu.bbs.dto.BBSDto;
import com.pknu.bbs.dto.UpdateDto;

public interface BBSService {
	public Model list(int pageNum,Model model);
	public String login(String id,String pass, HttpSession session);
	public void insertArticle(BBSDto article);
	public void getArticle(int articleNum, int fileStaus,Model model);
	public void delete(int articleNum,int fileStatus);
	public void replyArticle(BBSDto article, MultipartFile springFname);
	public void updateGetArticle(int articleNum, int fileStatus, Model model);
	public void updateArticle(BBSDto article,UpdateDto updateDto,
						MultipartHttpServletRequest mRequest, Model model, int fileCount);	
	public FileSystemResource download(HttpServletResponse resp, String storedFname);
	public int getMessageCount(String id);

}
