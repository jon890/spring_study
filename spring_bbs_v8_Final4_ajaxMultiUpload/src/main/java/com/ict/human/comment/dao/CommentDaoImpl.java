package com.ict.human.comment.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.human.comment.dto.CommentDto;

@Repository
public class CommentDaoImpl implements CommentDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private final String nameSpace = "com.ict.human.comment.dao.CommentDao";

	@Override
	public void insertComment(CommentDto commentDto) {
		sqlSession.insert(nameSpace.concat(".insertComment"), commentDto);
	}

	@Override
	public List<CommentDto> getComments(int articleNum, int commentRow) {
		HashMap<String, Integer> hs = new HashMap<>();
		hs.put("articleNum", articleNum);
		hs.put("commentRow", commentRow);
		return sqlSession.selectList(nameSpace.concat(".getComments"), hs);

	}
	
	
}