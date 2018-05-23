<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
	<head>
		<meta charset="UTF-8">
		<title>답글쓰기</title>	
		<style>
			table, table tr td{
				border : 1px solid black;
			}
			
			table{
				width : 200px;
			}
		</style>
	</head>
		
	<body>

		<div class="container">
			<form action="/human/reply.bbs" method="post" enctype="multipart/form-data">       
			<input type="hidden" name="pageNum" value="${pageNum}">    
			<input type="hidden" name="depth" value="${depth}">
			<input type="hidden" name="groupId" value="${groupId}">
			
				<table class="table">  
					<tr>
			 			 <td>답글쓴이 :</td>
			 			 <!-- session에서 가져온다-->
			 			 <!-- request > session > application 순으로 찾는다 -->
			 			 <td>${id}</td>
			 		</tr>
			 		
			 		<tr>	 
					 <td>제목 : </td>
					 <td><input type="text" name="title" id="title"></td>			 
					</tr>
					
					<tr>
					  <td colspan="2">
					  	<textarea cols="50" rows="20" name="content" id="content"></textarea>
					  </td>
				    </tr>
				    
				    <tr>
				    	<td colspan="2"><input type="file" name="fname" multiple="multiple"></td>
				    </tr>
				        
				    <tr>
				      <td><input type="submit" value="글쓰기"></td>
				      <td><input type="reset" value="글쓰기취소"></td>	      	 
				    </tr>
				    		
				</table>
			</form>
		</div>
			
	</body>
	
</html>