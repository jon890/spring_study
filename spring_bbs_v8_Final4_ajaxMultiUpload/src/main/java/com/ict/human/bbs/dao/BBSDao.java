package com.ict.human.bbs.dao;

import java.util.HashMap;
import java.util.List;

import com.ict.human.bbs.dto.BBSDto;
import com.ict.human.bbs.dto.FileDto;

public interface BBSDao {
	
	public void write(BBSDto article);
	public List<BBSDto> list(HashMap<String, String> pagingMap);
	public int getTotalCount();
	public String login(String id);
	//public BBSDto content(int articleNum, int fileStatus, Model model);
	public BBSDto content1(int articleNum);
	public void reply(BBSDto article);
	public void delete(String articleNum);
	public BBSDto updateGetArticle(String articleNum);
	public void update(BBSDto article);
	public void insertFile(FileDto fileDto);
	public List<FileDto> getFiles(int articleNum);
	public int getCommentCount(int articleNum);
	public List<String> getFileList(String articleNum);
}