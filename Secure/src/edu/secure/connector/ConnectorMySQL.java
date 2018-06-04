package edu.secure.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class ConnectorMySQL {
	private static ConnectorMySQL conn = null;
	private static BasicDataSource ds = null;

	private static String url = "jdbc:mysql://localhost:3306/secure";
	private static String id = "root";
	private static String pw = "mysql";
	
	// 주석으로 SQL 공격시 지정
	private static String driverClassName = "com.mysql.jdbc.Driver";

	private ConnectorMySQL() {}
	
	public static synchronized ConnectorMySQL getInstance() {

		if(conn == null) {
			conn = new ConnectorMySQL();
			ds = new BasicDataSource();
			ds.setDriverClassName(driverClassName);
			ds.setUrl(url);
			ds.setUsername(id);
			ds.setPassword(pw);
			
			// 커넥션 풀에서 제공할 수 있는 최대 커넥션 수 (기본값 : 8)
			ds.setMaxActive(10);
			
			// 사용하지 않고 커넥션 풀에 저장될 수 있는 최대 커넥션 수 (기본값 : 8, -1인 경우 무제한)
			ds.setMaxIdle(10);
			
			// 사용하지 않고 커넥션 풀에 저장될 수 있는 최소 커넥션 수
			ds.setMinIdle(10);
			
			// 남아있는 커넥션이 없는 경우 대기할 수 있는 시간 (-1인 경우 무제한)
			ds.setMaxWait(-1);

			// IDLE 상태인 커넥션이 유효한지 검사
			ds.setTestWhileIdle(true);
			
			// IDLE 상태인 커넥션이 유효한지 검사하는 주기
			ds.setTimeBetweenEvictionRunsMillis(600000);
		
			// IDLE 상태인 커넥션이 유효한지 검사할때 사용할 Query
			ds.setValidationQuery("SELECT 1");
		}

		return conn;

//		if(conn == null) {
//			conn = new ConnectorMySQL();
//		}
//
//		return conn;

	
	}
	
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		return ds.getConnection();

//		Class.forName(driverClassName);
//		Connection connection = DriverManager.getConnection(url, id, pw);
//		return connection;
	}
}
