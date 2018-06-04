<%@page import="edu.secure.dto.ArticleDto"%>
<%@page import="java.util.List"%>
<%@page import="edu.secure.dao.ArticleDao"%>
<%@page import="edu.secure.dao.ArticleDaoMySQL"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="edu.secure.connector.ConnectorMySQL"%>
<%@page import="java.sql.Connection"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성자</td>
		</tr>
<%
	ArticleDao dao = new ArticleDaoMySQL();
	List<ArticleDto> list = dao.select();
	for(int i = 0; i < list.size(); i++) {
%>
		<tr seq-no=<%=list.get(i).getId()%>>
			<td><%=list.get(i).getId()%></td>
			<td><%=list.get(i).getTitle()%></td>
			<td><%=list.get(i).getmId()%></td>
		</tr>
<%		
	}
%>
	</table>
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script>
		$("tr:not(tr:first)").click(function() {
			var seqNo = $(this).attr("seq-no");
			if(confirm("삭제하시겠습니까?")) {
				location.href = "boardDelete.jsp?seqNo=" + seqNo;
			}
		});
// 		$("tr").click(function() {
// 			var seqNo = $(this).attr("seq-no");
// 			var id = $(this).find("td:last").text();
// 			if(confirm("삭제하시겠습니까?")) {
// 				if(id == '${sessionScope.id}') {
// 					location.href = "./boardDelete.jsp?seqNo=" + seqNo;
// 				} else {
// 					alert("작성자 본인만 삭제 가능합니다");
// 				}
// 			}
// 		});
	</script>
</body>
</html>