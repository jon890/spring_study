<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
	<head>
		<meta charset="UTF-8">
		<title>글 수정</title>	
	</head>
		
	<body>

		<div class="container">
			<form action="/human/update.bbs" method="post">
				<input type="hidden" name="pageNum" value="${pageNum}">                 
				<input type="hidden" name="articleNum" value="${article.articleNum}">	    		
			
				<table class="table" border="2" width="200">  
					<tr>
			 			 <td>글쓴이 :</td>
			 			 <td>${id}</td>
			 		</tr>
			 		
			 		<tr>	 
					 <td>제목 : </td>
					 <td><input type="text" name="title" id="title" value="${article.title}"></td>			 
					</tr>
					
					<tr>
					  <td colspan="2">
					  	<textarea cols="50" rows="20" name="content" id="content" value="${article.content}"></textarea>
					  </td>
				    </tr>
				    
				    <tr>
				    	<td colspan="2"><input type="file" name="fname" value="${article.fname}"></td>
				    </tr>
				        
				    <tr>
				      <td><input type="submit" value="수정완료"></td>
				      <td><input type="reset" value="취소"></td>	      	 
				    </tr>
				   
				</table>
			</form>
		</div>
			
	</body>
	
</html>