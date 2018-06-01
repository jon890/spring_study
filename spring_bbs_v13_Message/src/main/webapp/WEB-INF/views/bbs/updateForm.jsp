<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta charset="utf-8">
<title>수정하기</title>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
$(document).ready(function() {
//		id 값으로 읽어 올려면 하나만 선택되어짐..클래스 사용
	$(".delFile").on("click", function() {
		alert($(this).attr("storedFname"));		
// 		storedFname이 원래 있던 속성이 아니므로 아래처럼 하면 못읽어옴
// 		var storedFname=$(this).prop("storedFname");			
		var storedFname=$(this).attr("storedFname");			
		$(this).parent().remove();
		let delFileNum ="<input type='hidden' name='storedFnameList' value='"+storedFname+"'>";		
		$(delFileNum).appendTo("form");				
	});

});
</script>
</head>
<body> 
<form action="/bbs/update.bbs" method="post" enctype="multipart/form-data">
	<input type="hidden" name="articleNum" value="${articleNum}">
	<input type="hidden" name="fileStatus" value="${fileStatus}">
	<input type="hidden" name="pageNum" value="${pageNum}">
	<input type="hidden" name="fileCount" value="${fileCount}">
	<table border="2" width="200">  
		<tr>
			 <td>글쓴이 :</td> <td>${id}</td> 
		</tr>
		<tr>	 
			 <td>제목 : </td>
			 <td><input type="text" name="title" value="${article.title}"></td>			 
		</tr>
		<tr>
			  <td colspan="2">
			  <textarea cols="50" rows="20" name="content">${article.content}</textarea>
			  </td>
	    </tr> 
	    <tr>
			  <td>첨부된화일 : </td>
			  <td>
			  	<c:if test="${fileList!=null}">
				 	<ul id="delGroup">		 	
						<c:forEach var="file" items="${fileList}">
		<!-- 				JQuery 함수 사용				 -->
						 <li>${file.originFname}
			 <input type="button" storedFname="${file.storedFname}" value="삭제" class="delFile">					
						 </li>				
						</c:forEach>
					</ul>
				</c:if>
			  </td>
	    </tr> 
	     <tr>
			  <td colspan="2"><input type="file" name="springFname" multiple="multiple" value="파일 첨부"></td>		  
	    </tr>
	    <tr>
	          <td><input type="submit" value="수정하기"></td>
	      	  <td><input type="reset" value="수정하기취소"></td>	      	 
	    </tr>		
	</table>	
</form>
</body>
</html>












