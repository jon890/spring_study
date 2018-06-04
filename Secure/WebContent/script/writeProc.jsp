<%@page import="edu.secure.dto.BoardDto"%>
<%@page import="edu.secure.dao.BoardDaoMySQL"%>
<%@page import="edu.secure.dao.BoardDao"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<%
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
// 	title = title.replace("<", "&lt;").replace(">", "&gt;");
// 	content = content.replace("<", "&lt;").replace(">", "&gt;");

	BoardDto dto = new BoardDto();
	dto.setTitle(title);
	dto.setContent(content);

	BoardDao dao = new BoardDaoMySQL();
	int result = dao.insert(dto);
	if(result > 0) {
		out.print("글쓰기 성공");
	} else {
		out.print("글쓰기 실패");
	}
%>
</body>
</html>