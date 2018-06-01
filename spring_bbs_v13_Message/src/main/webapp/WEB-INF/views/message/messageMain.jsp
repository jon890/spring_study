<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>쪽지보내기</title>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
$(document).ready(function(){
	$("a").on("click",function(event){
		event.preventDefault();
		let receiveId="${receiveId}";		
		window.open("messageForm.message?receiveId="+receiveId,	"messageForm","width=400, height=400, left=300, top=150");
	});
});
</script>
</head>
<body>
  <a href="#">쪽지보내기</a>
</body>
</html>