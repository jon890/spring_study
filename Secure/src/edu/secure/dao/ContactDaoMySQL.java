package edu.secure.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.catalina.connector.Connector;

import edu.secure.connector.ConnectorMySQL;
import edu.secure.dto.ContactDto;

public class ContactDaoMySQL implements ContactDao {
	@Override
	public int insert(ContactDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int result = 0;
		
		StringBuffer sql = null;
		
		try {
			conn = ConnectorMySQL.getInstance().getConnection();
			
			sql = new StringBuffer();
			sql.append("INSERT INTO SECURE_CONTACT");
			sql.append("       (ID, NAME, ADDRESS)");
			sql.append("    VALUES (NULL, ?, ?)");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getAddress());
			
			result = pstmt.executeUpdate();
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
			if(pstmt != null) {
				try {
					pstmt.close();
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
