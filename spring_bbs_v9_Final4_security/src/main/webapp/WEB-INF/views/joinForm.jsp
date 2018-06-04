<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
</head>

<body>
	<form action="/human/joinMember.bbs" method="post">
		<fieldset>
			<legend>회원가입</legend>
			<label for="id"> I D : <input type="text" name="id" id="id"></label><br>
			<div id="idCheckStatus"></div>
			<label for="pass">PWD : <input type="text" name="pass"id="pass"></label><br>
			<label for="email">E-Mail : <input type="email" name="email" id="email"></label><br>
			<label for="tel">Phone : <input type="tel" name="tel" id="tel"></label><br>
			<label for="postcode"> 우편번호 : <input type="text" name="postcode1"> 
								  - 	   <input type="text" name="postcode2"></label>
			<input type="button" value="주소찾기" id="searchAddress"><br>
			<label for="address">주소 : <input type="text" name="roadAddress" id="roadAddress"> 
									  <input type="text" name="address_etc" id="address_etc"></label><br> 
			<input type="submit" value="확인">
			<input type="reset" value="취소">
		</fieldset>
	</form>

	<script>
		$.ajaxSetup({
			type : "POST",
			async : true,
			dataType : "json",
			error : function(xhr){
				alert("error html = " + xhr.statusText);
			}
		});
		
		 $(function(){
			 $("#id").on("blur", function(){
				 
				 if( $("#id").val().trim().length != 0){
					 $.ajax({
						url:"/human/joinIdCheck.bbs",
						data:{
							inputId:$("#id").val()
						},
						success : function(data) {
							let html;
							//alert(data);
							if(data==1){
								html="<b>멋진 아이디입니다</b>";
								$("#idCheckStatus").html(html).css("color","red");
							}else{
								html="<b>안 좋은 아이디입니다</b>";
								$("#idCheckStatus").html(html).css("color","green");
								$("#id").focus();
								$("#id").select();
							}
						}
					});
				 } else {
					 html="<b>아이디를 입력해주세요</b>";
					 $("#idCheckStatus").html(html).css("color","orange");
					 $("#id").focus();
				 }
				 
			 });
			  
			 
			$("#searchAddress").on("click", function() {
				getDaumAddress();
			});
			
			
			$("input[type=submit]").on("click", function(){
				checkPass($("#pass").val().trim());
				checkEmail($("#email").val().trim());
				checkTel($("#tel").val().trim());
				// return true;
				// 하나라도 false가 넘어오면 submit 되지 않는다.
			});
			
			
		 });
		 
		 
		 function getDaumAddress(){
			 new daum.Postcode({
				oncomplete : function(data) {
					$('input[name=postcode1]').val(data.postcode1);
					$('input[name=postcode2]').val(data.postcode2);
					$('input[name=roadAddress]').val(data.roadAddress);
					$('input[name=address_etc]').focus();
				}
			}).open();
		 }

		 

	</script>
</body>

</html>