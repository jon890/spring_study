package com.pknu.comment.service;

import java.util.List;

import com.pknu.comment.dto.CommentDto;

public interface CommentService {
	
	public List<CommentDto> getComments(int articleNum, int commentRow);
	public void insertComment(CommentDto comment);
}
