<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>쪽지 보내기</title>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function(){	
		window.opener.close();		
	});
	
</script>
</head>
<body>
<form action="/bbs/messageWrite.message" method="post">
	<input type="hidden" name="receiveId" value="${receiveId}">
	<table border="2" width="200">  
		<tr>
 			 <td>받는 사람 :</td>
 			 <td>${receiveId}</td>
 		</tr> 	
		<tr>
		  <td colspan="2">
		  	<textarea cols="50" rows="20" name="messageContent" ></textarea>
		  </td>
	    </tr>	    
	    <tr>
	      <td><input type="submit" value="쪽지보내기"></td>
	      <td><input type="reset" value="취소"></td>	      	 
	    </tr>		
	</table>	
</form>
</body>
</html>