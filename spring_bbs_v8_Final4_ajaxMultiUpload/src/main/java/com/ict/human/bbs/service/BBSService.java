package com.ict.human.bbs.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.io.FileSystemResource;
import org.springframework.ui.Model;

import com.ict.human.bbs.dto.BBSDto;

public interface BBSService {

	public void write(BBSDto article);
	public void list(String pageNum, Model model);
	public String login(String id, String pass, HttpSession session);
	//public void content(int articleNum, int fileStatus, Model model);
	public void content1(String articleNum, int fileStatus, Model model);
	public void reply(BBSDto article);
	public void delete(String articleNum, int fileStatus);
	
	public void commonFileUpload(List<String> files, int articleNum);
	public FileSystemResource download(HttpServletResponse resp,
							          String storedFname);
	
	
	/* ********** 글 수정 관련 메소드 ********** */
	// 글 수정시 글 읽어오기
	public BBSDto getUpdateArticle(String articleNum);
	// 글 수정 완료 버튼을 눌렀을 때 글 업데이트
	public void update(BBSDto article, String[] deleteFileName, Model model, int fileCount);
	// 글의 파일 리스트 읽어오기
	public List<String> getFiles(String articleNum);	
}