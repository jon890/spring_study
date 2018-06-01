<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset="utf-8">
<title>답글쓰기</title>
</head>
<body> 
<form action="reply.bbs" method="post" enctype="multipart/form-data">
	<input type="hidden" name="pageNum" value="${pageNum}">                 
    <input type="hidden" name="depth" value="${depth}">   
    <input type="hidden" name="groupId" value="${groupId}">
	<table border="2" width="200">  
		<tr>
 			 <td>글쓴이 :</td>
 			 <td>${id}</td>
 		</tr>
 		<tr>	 
		 <td>제목 : </td>
		 <td><input type="text" name="title" value="[Re]"></td>			 
		</tr>
		<tr>
		  <td colspan="2">
		  <textarea cols="50" rows="20" name="content" ></textarea>
		  </td>
	    </tr> 	    
	    <tr>
	      <td>첨부 : </td>
	      <td><input type="file"  name="springFname" ></td>
	    </tr>
	    <tr>
	      <td><input type="submit" value="글쓰기"></td>
	      <td><input type="reset" value="글쓰기취소"></td>	      	 
	    </tr>		
	</table>	
</form>
</body>
</html>