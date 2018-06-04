<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
</head>
<body>
	<form method="post" action="./writeProc.jsp">
		제목 : <input type="text" name="title">
		<br> 
		내용 : <textarea name="content"></textarea>
		<input type="submit" value="완료">
	</form>
</body>
</html>