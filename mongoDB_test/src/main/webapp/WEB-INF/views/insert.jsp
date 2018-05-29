<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>사용자 추가</title>
</head>

<body>
<form action="/mongo/insert.mongo" method="post" >
	이름 : <input type="text" name="name" value="1" /><br />
	성별 : <input type="text" name="gender" value="남" /><br />
	주소 : <input type="text" name="address" /><br />
	<input type="submit" value="완료" />
</form>
</body>
</html>