<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>사용자 목록</title>
</head>
<body>
    <a href="/mongo/insert.mongo">쓰기</a><br/>
	<table border="1">
		<tr>
			<th>이름</th>
			<th>성별</th>
			<th>주소</th>
		</tr>
		<c:forEach var="user" items="${userList}">
			<tr>
				<td>
					<a href='detail.mongo?id=${user.id}'>${user.name}</a>
				</td>
				<td>${user.gender}</td>
				<td>${user.address.si} ${user.address.gu}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>