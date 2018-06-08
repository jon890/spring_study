package com.ict.human.bbs.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.human.bbs.dto.BBSDto;
import com.ict.human.bbs.dto.FileDto;

@Repository
public class BBSDaoImpl implements BBSDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private final String nameSpace = "com.ict.human.bbs.dao.BBSDao";

	
	@Override
	public void write(BBSDto article) {
		sqlSession.insert(nameSpace.concat(".write"), article);
	}
	
/*	@Override
	public List<BBSDto> list(HashMap<String, String> pagingMap) {
		List<BBSDto> articleList = sqlSession.selectList(nameSpace.concat(".list"), pagingMap);
		for(BBSDto article : articleList) {
			int commentCount = sqlSession.selectOne(nameSpace.concat(".getCommentCount"), article.getArticleNum());
			article.setCommentCount(commentCount);
		}
		return articleList;
	}*/
	
	@Override
	public List<BBSDto> list(HashMap<String, String> pagingMap) {
		return sqlSession.selectList(nameSpace.concat(".list"), pagingMap);
	}

	@Override
	public String login(String id) {
		return sqlSession.selectOne(nameSpace.concat(".login"), id);
	}

	@Override
	public int getTotalCount() {
		return sqlSession.selectOne(nameSpace.concat(".getTotalCount"));
	}

	/*@Override
	public BBSDto content(int articleNum, int fileStatus, Model model) {
		
		return sqlSession.selectOne(nameSpace.concat(".content"), articleNum);
	}*/
	
	@Override
	public BBSDto content1(String articleNum) {
		return sqlSession.selectOne(nameSpace.concat(".content1"), articleNum);
	}

	@Override
	public void reply(BBSDto article) {
		sqlSession.insert(nameSpace.concat(".reply"), article);
	}

	@Override
	public void delete(String articleNum) {
		sqlSession.delete(nameSpace.concat(".delete"), articleNum);
	}

	@Override
	public BBSDto getUpdateArticle(String articleNum) {
		return sqlSession.selectOne(nameSpace.concat(".getUpdateArticle"), articleNum);
	}

	@Override
	public void update(BBSDto article) {
		sqlSession.update(nameSpace.concat(".update"), article);
	}
	

	@Override
	public void dbDelFileName(ArrayList<String> delFileList) {
		sqlSession.delete(nameSpace.concat(".dbDelFileName"), delFileList);
		
	}

	@Override
	public void insertFile(FileDto fileDto) {
		sqlSession.insert(nameSpace.concat(".insertFile"), fileDto);
	}

	@Override
	public List<FileDto> getFiles(String articleNum) {
		return sqlSession.selectList(nameSpace.concat(".getFiles"), articleNum);
	}

	@Override
	public int getCommentCount(int articleNum) {
		return sqlSession.selectOne(nameSpace.concat(".getCommentCount"), articleNum);
	}

	@Override
	public int getNextArticleNum() {
		return sqlSession.selectOne(nameSpace.concat(".getNextArticleNum"));
	}

	@Override
	public String joinIdCheck(String inputId) {
		return sqlSession.selectOne(nameSpace.concat(".joinIdCheck"), inputId);
	}

	@Override
	public String getStoredFname(String fileNum) {
		return sqlSession.selectOne(nameSpace.concat(".getStoredFname"), fileNum);
	}
}