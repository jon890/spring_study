package com.ict.human.bbs.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.io.FileSystemResource;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.ict.human.bbs.dto.BBSDto;

public interface BBSService {

	public void write(BBSDto article);
	public void list(String pageNum, Model model);
	public String login(String id, String pass, HttpSession session);
	//public void content(int articleNum, int fileStatus, Model model);
	public void content1(int articleNum, int fileStatus, Model model);
	public void reply(BBSDto article, List<MultipartFile> mFile);
	public void delete(String articleNum, int fileStatus);
	public BBSDto updateGetArticle(String articleNum);
	public void update(BBSDto article);
	public void commonFileUpload(List<String> files, int articleNum);
	public FileSystemResource download(HttpServletResponse resp,
							          String storedFname);

}