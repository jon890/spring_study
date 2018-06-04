package edu.secure.dao;

import java.util.List;

import edu.secure.dto.BoardDto;

public interface BoardDao {
	List<BoardDto> select(BoardDto dto);
	int insert(BoardDto dto);
}
