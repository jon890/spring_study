package com.ict.human.bbs.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.ict.human.bbs.dto.BBSDto;

@Service
@Qualifier("b")
public class BBSasdf implements BBSService {

	@Override
	public void write(BBSDto article) {
		// TODO Auto-generated method stub

	}

	@Override
	public void list(String pageNum, Model model) {
		// TODO Auto-generated method stub

	}

	@Override
	public String login(String id, String pass, HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void content1(String articleNum, int fileStatus, Model model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reply(BBSDto article) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String articleNum, int fileStatus) {
		// TODO Auto-generated method stub

	}

	@Override
	public void commonFileUpload(List<String> files, int articleNum) {
		// TODO Auto-generated method stub

	}

	@Override
	public FileSystemResource download(HttpServletResponse resp, String storedFname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BBSDto getUpdateArticle(String articleNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(BBSDto article, String[] deleteFileName, Model model, int fileCount) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> getFiles(String articleNum) {
		// TODO Auto-generated method stub
		return null;
	}

}
