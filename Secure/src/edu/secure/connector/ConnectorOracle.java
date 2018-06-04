package edu.secure.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class ConnectorOracle {
	private static ConnectorOracle conn = null;

	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "scott";
	private String pw = "tiger";
	
	private String driverClassName = "core.log.jdbc.driver.OracleDriver";
//	private String driverClassName = "oracle.jdbc.driver.OracleDriver";
	
	private static BasicDataSource ds = null;

	private ConnectorOracle() {}
	
	public static synchronized ConnectorOracle getInstance() {

		if(conn == null) {
			conn = new ConnectorOracle();
			ds = new BasicDataSource();
			
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
			ds.setValidationQuery("SELECT 1 FROM DUAL");
		}

		return conn;
/*
		if(conn == null) {
			conn = new Connector();
		}

		return conn;
*/
	
	}
	
	public Connection getConnection() throws SQLException {

		ds.setDriverClassName(driverClassName);
		ds.setUrl(url);
		ds.setUsername(id);
		ds.setPassword(pw);
		
		return ds.getConnection();

/*
		Connection connection = DriverManager.getConnection(url, id, pw);
		return connection;
*/
	}
}
