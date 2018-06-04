package edu.secure.dao;

import edu.secure.dto.MemberDto;

public interface MemberDao {
	int select(MemberDto dto);
}
