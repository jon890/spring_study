package edu.secure.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.secure.connector.ConnectorMySQL;
import edu.secure.dto.ArticleDto;

public class ArticleDaoMySQL implements ArticleDao {
	@Override
	public List<ArticleDto> select() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<ArticleDto> list = new ArrayList<ArticleDto>();
		
		try {
			conn = ConnectorMySQL.getInstance().getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ID, TITLE, CONTENT, M_ID");
			sql.append("  FROM SECURE_ARTICLE");

			stmt = conn.prepareStatement(sql.toString());
			
			rs = stmt.executeQuery(sql.toString());
			while(rs.next()) {
				ArticleDto resultDto = new ArticleDto();
				resultDto.setId(rs.getInt("ID"));
				resultDto.setTitle(rs.getString("TITLE"));
				resultDto.setContent(rs.getString("CONTENT"));
				resultDto.setmId(rs.getString("M_ID"));
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
				} catch (SQLException e) {}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		return list;
	}

	@Override
	public int delete(ArticleDto dto) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		int result = 0;
		
		try {
			conn = ConnectorMySQL.getInstance().getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM SECURE_ARTICLE");
			sql.append(" WHERE ID = ?");
			
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, dto.getId());
			
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		return result;
	}

}
