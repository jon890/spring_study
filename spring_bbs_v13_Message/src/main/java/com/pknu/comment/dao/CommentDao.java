package com.pknu.comment.dao;

import java.util.HashMap;
import java.util.List;

import com.pknu.comment.dto.CommentDto;

public interface CommentDao {
	public List<CommentDto> getComments(HashMap<String, Integer> commentMap);
	public void insertComment(CommentDto comment);
}
