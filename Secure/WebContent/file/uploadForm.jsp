<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="./upload.jsp" enctype="multipart/form-data">
		제목 : <input type="text" name="title">
		<br>
		첨부파일 : <input type="file" name="file">
		<br>
		<input type="submit" value="완료">
	</form>
</body>
</html>