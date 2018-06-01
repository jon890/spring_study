<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 하세요..^^</title>
<script src="//code.jquery.com/jquery-3.1.0.min.js"></script>
<script>
// event 객체 위치 확인하기
 $(function(){
// 	 폼이벤트 처리할때는 event.preventDefault();가 안먹는 이유...알아내기
	 $("#loginForm").on("submit", function(){
// 		 event.preventDefault();
		 var id=$("#id").val();
		 var pass=$("#pass").val(); 
		 
		 if(id==""){
		 	alert("아이디를 입력하세요");
		 	$("#id").focus();
		 	return false;
		 }
		 if(pass==""){
		 	alert("패스워트를 입력하세요");
		 	$("#pass").focus();
		 	return false;
		 }	
		 $("#loginForm").submit();
	 })
	 
	 $("input[type=button]").on("click",function(){
		 document.location.href='/join/joinMember.jsp';
	 })
 });
</script>
</head>
<body>
	<form action="login.bbs" method="post" id="loginForm">
<%-- 		<input type="hidden" name="loginPath" value="${writeForm}"> --%>
          <label for="id"> I  D : <input type="text" name="id" id="id"></label><br />
          <label for="pass">PASS : <input type="text" name="pass" id="pass"></label>
          <input type="submit" value="로그인">
          <input type="reset" value="취소">
          <input type="button" value="회원가입">          
   </form>
</body>
</html>