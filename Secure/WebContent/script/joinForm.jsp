<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script>
	function joinCheck() {
		var jumin1 = $("input[name=jumin1]").val();
		var jumin2 = $("input[name=jumin2]").val();
		
		var jumin = jumin1 + jumin2;
		
		if(jumin.length != 13) {
			alert("주민등록번호를 확인해주세요");
			return false;
		}
		
		return true;
	}
</script>
</head>
<body>
	<form method="post" action="./joinProc.jsp" onsubmit="return joinCheck()">
		주민등록번호
		<br>
		<input type="text" name="jumin1"> - 
		<input type="text" name="jumin2">

		<input type="submit" value="회원가입">
	</form>
</body>
</html>