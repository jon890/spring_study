package com.ict.human.comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.human.comment.dao.CommentDao;
import com.ict.human.comment.dto.CommentDto;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentDao commentDao;

	@Override
	public void insertComment(CommentDto commentDto) {
		commentDao.insertComment(commentDto);
	}

	@Override
	public List<CommentDto> getComments(int articleNum, int commentRow) {
		return commentDao.getComments(articleNum, commentRow);
	}
	
	
}