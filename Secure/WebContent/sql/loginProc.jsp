<%@page import="edu.secure.dao.MemberDao"%>
<%@page import="edu.secure.dao.MemberDaoMySQL"%>
<%@page import="edu.secure.dto.MemberDto"%>
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
	// 
	// 1' or '1' = '1
	// 1' #
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	
	MemberDao dao = new MemberDaoMySQL();
	MemberDto dto = new MemberDto();
	dto.setId(id);
	dto.setPw(pw);
	
	int result = dao.select(dto);
	
	if(result > 0) {
		out.print("로그인 성공");
	} else {
		out.print("로그인 실패");
	}
%>
</body>
</html>