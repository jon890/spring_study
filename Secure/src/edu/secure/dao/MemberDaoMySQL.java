package edu.secure.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.secure.connector.ConnectorMySQL;
import edu.secure.dto.MemberDto;

public class MemberDaoMySQL implements MemberDao {
	@Override
	public int select(MemberDto dto) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		int result = 0;
		
		StringBuffer sql = null;
		
		try {
			conn = ConnectorMySQL.getInstance().getConnection();
			
			sql = new StringBuffer();
			sql.append("SELECT COUNT(*) AS CNT");
			sql.append("  FROM SECURE_MEMBER");
			sql.append(" WHERE ID = '" + dto.getId() + "'");
			sql.append("   AND PW = '" + dto.getPw() + "'");

			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql.toString());
			while(rs.next()) {
				result = rs.getInt("CNT");
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
		return result;
	}

}
