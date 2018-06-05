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
			<label for="email">E-Mail : <input type="text" name="email" id="email"></label><br>
			<label for="tel">Phone : <input type="tel" name="tel"
											pattern = "[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}" id="tel"></label><br>
			<h5>전화번호 형식은 하이픈(-)을 넣은 형식으로 입력해 주세요</h5>
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
								$("#idCheckStatus").html(html).css("color", "green");
							}else{
								html="<b>안 좋은 아이디입니다</b>";
								$("#idCheckStatus").html(html).css("color", "red");
								$("#id").focus();
								$("#id").select();
							}
						}
					});
				 } else {
					 html="<b>아이디를 입력해주세요</b>";
					 $("#idCheckStatus").html(html).css("color", "orange");
					 $("#id").focus();
				 }
				 
			 });
			  
			 
			$("#searchAddress").on("click", function() {
				getDaumAddress();
			});
			
			
			$("input[type=submit]").on("click", function(){
				//checkPass($("#pass").val().trim());
				if( checkEmail($("#email").val().trim()) == false ){
					return false;
				};
				//checkTel($("#tel").val().trim());
				// return true;
				// 하나라도 false가 넘어오면 submit 되지 않는다.
			});
			
			
		 });
		 
		 
		 function checkEmail(email){
		
//		 	/^[a-zA-Z0-9._-]+
//		 	이메일 주소 중 아이디가 반드시 알파벳 혹은 숫자로 시작해야함을 말한다. (
//		 	알파벳은 대소문자를 모두 허용한다. ) 추가로 마침표( . ), 밑줄( _ ), 하이픈( – )을 사용할 수 있다.
//  		\.
//  		회사 도메인명 다음에는 반드시 마침표( . )를 찍는다. 
//  		회사 도메인 다음에 오는 com, co 같은 상위 그룹 도메인을 구별해주어야 한다.
//		 	[a-zA-Z]{2,4}$/
//		 	 마지막으로 com, co와 같은 상위 도메인 규칙을 정의한다. 
//		 	 대소문자를 모두 허용하는 2~4글자의 알파벳을 허용한다. {2,4}는 각각 최소, 최대 글자 수를 지칭한다.
//			asdf@gwangju.museum
//			2~6자리의 도메인이 사용가능하다.
			let reg =/^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
//			RegExp 객체를 이용해도 가능하다
//		 	let reg = new RegExp('/^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/');
			let result = reg.test(email);
			 
			if(!result){
				alert("이메일 형식에 오류가 있습니다");
				$("#email").focus();
				return false;
			}
			
			/* if($("#email").match(reg) != null){
				alert("ok");
			} else {
				alert("fail");
			} */
			
			// match 함수나 test 둘 중에 하나로 검사 할 수 있다
			// 서로 형식이 반대이므로 체크하기
		 }
			
			
			
		// 자바스크립트용 좌우 공백제거 함수
		// jquery에서는 trim()함수를 사용하면 된다.
		function strTrim(str) {
			return str.replace(/(^\s*)|(\s*$)/g, "");
		}
		 // /:문자열 시작
		 // ^ : 문자열의 처음(문자열의 시작 문자가) 
		 // \s* : 임의의 개수의 공백 문자, \s 가 공백, *가 임의의 개수
		 // | : OR 기호
		 // $ : 문자열의 끝(문자열의 끝 문자가)
		 // g : 문자열의 모든 부분에 걸쳐 글로벌하게 치환
		 // '' : 치환할 빈 문자열임. 작은따옴표2개임. 
		 // 빈 문자열로 치환(replace)하면 문자열 삭제가 됨
		 
		 
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