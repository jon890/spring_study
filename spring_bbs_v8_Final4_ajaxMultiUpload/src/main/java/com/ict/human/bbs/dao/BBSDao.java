package com.ict.human.bbs.dao;

import java.util.ArrayList;
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
	public BBSDto content1(String articleNum);
	public void reply(BBSDto article);
	public void delete(String articleNum);
	public void insertFile(FileDto fileDto);
	public List<String> getFiles(String articleNum);
	public int getCommentCount(int articleNum);
	public int getNextArticleNum();
	
	/* ********** 글 수정 관련 메소드 ********** */
	// 글 수정시 글 읽어오기
	public BBSDto getUpdateArticle(String articleNum);
	// 글 수정 완료 버튼을 눌렀을 때 글 업데이트
	public void update(BBSDto article);
	// 글 수정시 업로드한 파일 제거시 파일 정보를 DB에서 제거 하는 메소드
	public void dbDelFileName(ArrayList<String> delFileList);
}