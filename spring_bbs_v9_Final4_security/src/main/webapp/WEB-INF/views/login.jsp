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
		
		<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
		
		
	</head>
	
	<body>
		<!-- 초안.. -->
		<!-- <form action="/human/login.bbs" method="post" id="loginForm" onsubmit="return validChk()"> -->
		<form action="/human/login.bbs" method="post" id="loginForm">
			<div>
				<label for="id"><span>아이디 : </span><input type="text" name="id" id="id"></label><br>
<!-- 			<label for="id"><span>아이디 : </span><input type="text" name="id" id="id" required="required"></label><br> -->
				<label for="pass"><span>비밀번호 : </span><input type="text" name="pass" id="pass"></label>
			</div>
			
			<div id="btns">
				<input type="submit" value="로그인">
				<input type="reset" value="취소">
				<input type="button" value="회원가입">
			</div>
		</form>
	
	
		<script>
		
		let id = $("#id");
		let pass = $("#pass");
		
		$(function(){
			
			$("input[type=button]").on("click", function(){
				document.location.href="/human/joinForm.bbs";
			});
			
			$("input[type=submit]").on("click", function(){
				
				id.val(id.val().trim());
				pass.val(pass.val().trim());
				
				//alert($("input[name=id]").val().trim());
				//alert($("input[name=pass]").val().trim());
				
				/* event.preventDefault(); */
							
				if( id.val() == "" || id.val() == null){
					alert("아이디를 입력해주세요");
					id.focus();
					return false;
				} else if (pass.val() == "" || pass.val() == null) {
					alert("비밀번호를 입력해주세요");
					pass.focus();
					return false;
				} else if( pass.val().length < 8) {
					alert("비밀번호는 8자 이상 입력해주세요");
					pass.focus();
					return false;
				}	
				
			});
			
		});

		/* function validChk(){

			//alert(id.val());
			//alert(pass.val());
			//alert(pass.val().length);
			
			if( id.val() == "" || id.val() == null){
				alert("아이디를 입력해주세요");
				id.focus();
				return false;
			} else if (pass.val() == "" || pass.val() == null) {
				alert("비밀번호를 입력해주세요");
				pass.focus();
				return false;
			} else if( pass.val().length < 8) {
				alert("비밀번호는 8자 이상 입력해주세요");
				pass.focus();
				return false;
			}
			
			return true;
		} */
		</script>
		
	</body>
</html>