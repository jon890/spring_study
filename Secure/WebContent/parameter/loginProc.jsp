<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="edu.secure.connector.ConnectorMySQL"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<%
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	String sql = "";
	
	int result = 0;
	
	try {
		conn = ConnectorMySQL.getInstance().getConnection();
		stmt = conn.createStatement();
		sql = "SELECT COUNT(*) AS CNT" +
		             "  FROM MEMBER_SECURE" +
				     " WHERE ID = '" + id + "'" +
		             "   AND PW = '" + pw + "'";
		rs = stmt.executeQuery(sql);
		while(rs.next()) {
			result = rs.getInt("CNT");
			session.setAttribute("id", id);
		}
	} catch(Exception e) {
		e.printStackTrace();
	} finally {
		if(rs != null) {
			try {
				rs.close();
			} catch(SQLException e) {}
		}
		if(stmt != null) {
			try {
				stmt.close();
			} catch(SQLException e) {}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch(SQLException e) {}
		}
	}
	
	out.print(sql + "<br>");
	if(result > 0) {
		out.print("로그인 성공");
	} else {
		out.print("로그인 실패");
	}
%>
</body>
</html>