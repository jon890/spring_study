<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>
	${id }님...환영합니다<br />		 
		<button id="logout">로그아웃</button><br/>
		<a href="/bbs/messageList.message?pageNum=1">쪽지(${messageCount})</a> 
		
		
		<script>
			$("button").on("click", function(){
				location.href = "logout.bbs";
			})
		</script>
	
</body>
</html>