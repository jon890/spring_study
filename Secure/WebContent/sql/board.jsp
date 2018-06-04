<%@page import="java.util.List"%>
<%@page import="edu.secure.dto.BoardDto"%>
<%@page import="edu.secure.dao.BoardDaoMySQL"%>
<%@page import="edu.secure.dao.BoardDao"%>
<%@page language="java" 
        contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<%
    // ?id=1' UNION SELECT 1, ID, PW FROM SECURE_MEMBER WHERE '1' = '1
	String id = request.getParameter("id");
	
	BoardDao dao = new BoardDaoMySQL();
	BoardDto dto = new BoardDto();
	dto.setId(id); 
	
	List<BoardDto> list = dao.select(dto);
	
	out.print("<table border='1'>");
	out.print("<tr>");
	out.print("<td>번호</td>");
	out.print("<td>제목</td>");
	out.print("<td>내용</td>");
	out.print("</tr>");
	for(int i = 0; i < list.size(); i++) {
		out.print("<tr>");
		out.print("<td>" + list.get(i).getId() + "</td>");
		out.print("<td>" + list.get(i).getTitle() + "</td>");
		out.print("<td>" + list.get(i).getContent() + "</td>");
		out.print("</tr>");
	}
	out.print("</table>");

%>
</body>
</html>