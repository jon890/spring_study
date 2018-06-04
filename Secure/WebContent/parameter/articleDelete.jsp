<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="edu.secure.connector.ConnectorMySQL"%>
<%@page import="java.sql.Connection"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<%
	String seqNo = request.getParameter("seqNo");

	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	String sql = "";
	
	int result = 0;
	
	try {
		conn = ConnectorMySQL.getInstance().getConnection();
		sql = "DELETE FROM BOARD_SECURE" + 
		      " WHERE SEQ_NO = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, seqNo);
		stmt.executeUpdate();
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
%>
<script>
	alert("삭제 완료\n목록으로 이동");
	location.href = "./boardList.jsp";
</script>
</body>
</html>