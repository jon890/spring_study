package edu.secure.dao;

import java.util.List;

import edu.secure.dto.ArticleDto;

public interface ArticleDao {
	List<ArticleDto> select();
	int delete(ArticleDto dto);
}
