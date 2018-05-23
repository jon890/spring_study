<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC>

<html>

	<head>
		<meta charset="UTF-8">
		<title>로그인 페이지</title>
		
		<style>
			input[type=text]{
				width : 200px;
				height : 30px;
			}
			
			span{
				font-size : 16px;
			}
			
			div#btns > input{
				width : 100px;
				height : 30px;
				color : white;
				background-color : darksalmon;
				font-size : 18px;
				border : none;
			}
			
			div{
				margin : 10px;
			}
		</style>
		
		
	</head>
	
	<body>
	
		<form action="/human/login.bbs" method="post" id="loginForm">
			<div>
				<label for="id"><span>아이디 : </span><input type="text" name="id" id="id"></label><br>
				<label for="pass"><span>비밀번호 : </span><input type="text" name="pass" id="pass"></label>
			</div>
			
			<div id="btns">
				<input type="submit" value="로그인">
				<input type="reset" value="취소">
				<input type="button" value="회원가입">
			</div>
		</form>
	
	
		<script>
		$(function(){
			$("input[type=button]").on("click", function(){
				document.location.href="/human/join.bbs";
			});
		});
		</script>
		
	</body>
</html>