<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE>
<html>
<head>
<meta charset="utf-8">
<title>글읽기</title>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body>   
   <form action="messageReplyForm.message" method="post">      
    <input type="hidden" name="id" value="${message.id}"> 
    <input type="hidden" name="pageNum" value="${pageNum}"> 
	<table border="1" width="500" align="center">  
		<tr>
 			 <td>글쓴이 :</td> <td>${message.id}</td> 	 
 			 <td>날짜 : </td><td>${message.messageDate}</td>			 
 		</tr> 
 		<tr>		
			  <td colspan="4"><xmp>${message.messageContent}</xmp></td>
	    </tr> 	
	          
	    <tr>	      
	       	  <td colspan="4" align="right">	    	
	    	  <input type="submit" value="답장하기">
	    	  <input type="button" value="삭제하기" onclick="document.location.href='messageDelete.message?messageNum=${message.messageNum}&pageNum=${pageNum}">
	    	  <input type="button" value="목록으로" onclick="document.location.href='messageList.message?pageNum=${pageNum}'">
	    	  </td>	     	 
	     </tr>	     
		 </table>	
	</form>
	
	<form>
	<div>
		<div id="showComment" align="center">
		</div>
		<input type="hidden" id="commPageNum" value="1">
	</div>	
	</form>
</body>
</html>