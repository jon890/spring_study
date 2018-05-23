package com.ict.human.comment.service;

import java.util.List;

import com.ict.human.comment.dto.CommentDto;

public interface CommentService {
	
	public void insertComment(CommentDto commentDto);
	public List<CommentDto> getComments(int articleNum, int commentRow);

}