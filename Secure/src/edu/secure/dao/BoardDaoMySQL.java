package edu.secure.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.secure.connector.ConnectorMySQL;
import edu.secure.dto.BoardDto;

public class BoardDaoMySQL implements BoardDao {
	@Override
	public List<BoardDto> select(BoardDto dto) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<BoardDto> list = new ArrayList<BoardDto>();
		
		try {
			conn = ConnectorMySQL.getInstance().getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ID, TITLE, CONTENT");
			sql.append("  FROM SECURE_BOARD");
			sql.append(" WHERE ID = '" + dto.getId() + "'");

			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql.toString());
			while(rs.next()) {
				BoardDto resultDto = new BoardDto();
				resultDto.setId(rs.getString("ID"));
				resultDto.setTitle(rs.getString("TITLE"));
				resultDto.setContent(rs.getString("CONTENT"));
				list.add(resultDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	@Override
	public int insert(BoardDto dto) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		int result = 0;
		
		try {
			conn = ConnectorMySQL.getInstance().getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO SECURE_BOARD (ID, TITLE, CONTENT)");
			sql.append("       VALUES ((SELECT MAX(ID) + 1 ");
			sql.append(" FROM SECURE_BOARD S), ?, ?)");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, dto.getTitle());
			stmt.setString(2, dto.getContent());
			
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

}
