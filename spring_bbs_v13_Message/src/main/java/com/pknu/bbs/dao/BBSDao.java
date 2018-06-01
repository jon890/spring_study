package com.pknu.bbs.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.pknu.bbs.dto.BBSDto;
import com.pknu.bbs.dto.FileDto;

public interface BBSDao {
	public int getArticleCount();
//	public List<BBSDto> getArticles(int startRow, int endRow);
	public List<BBSDto> getArticles(HashMap<String, Integer> paramMap);
	public int getMessageCount(String id);
	public String login(String id);
	public void insertArticle(BBSDto article);
	public BBSDto getArticle(int articleNum);
	public void delete(int articleNum);
	public void replyArticle(BBSDto article);	
	public BBSDto updateGetArticle(int articleNum);
	public void updateArticle(BBSDto article);	
	public int getNextArticleNum();
	public void insertFile(FileDto fileDto);
	public List<String> getFiles(int articleNum);
	public void someDelFile(ArrayList<String> storedFnameList);
	public List<String> getStoredFnames(int articleNum);
}
