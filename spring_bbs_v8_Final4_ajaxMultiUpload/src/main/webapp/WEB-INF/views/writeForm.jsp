<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
	<head>
		<meta charset="UTF-8">
		<title>글쓰기</title>
		
		<style>	
			.fileDrop{
				width : 400px;
				height : 150px;
				border : 1px dotted blue;
				margin : auto 0;
			}
			
			small{
				margin-left : 3px;
				font-weight : bold;
				color : gray;
			}
		</style>
		
		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
		
		<!-- Optional JavaScript -->
    	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>

	</head>
		
	<body>

		<div class="container">
			<form action="/human/write.bbs" method="post">
			
				<table class="table">  
					<tr>
			 			 <td>글쓴이 :</td>
			 			 <!-- session에서 가져온다-->
			 			 <!-- request > session > application 순으로 찾는다 -->
			 			 <td>${id}</td>
			 		</tr>
			 		
			 		<tr>	 
					 <td>제목 : </td>
					 <td><input type="text" name="title"></td>			 
					</tr>
					
					<tr>
					  <td colspan="2">
					  	<textarea cols="100" rows="20" name="content"></textarea>
					  </td>
				    </tr>
				        
				    <tr>
				      <td><input type="submit" value="글쓰기"></td>
				      <td><input type="reset" value="글쓰기취소"></td>	      	 
				    </tr>		    		
				</table>
				
				<div class="fileDrop"></div>
				<input type="button" value="모두삭제"/>
				<div class="uploadedList"></div>
				
			</form>
		</div>
		
		<script>
		
			/* 파일 드로그 앤 드랍시 기본 동작(파일 실행)을 막기 */
			$(".fileDrop").on("dragenter dragover", function(event){
				event.preventDefault();
			});
			
			$(".fileDrop").on("drop", function(event){
				event.preventDefault();
			});
			/* 파일 드로그 앤 드랍시 기본 동작(파일 실행)을 막기 */
			
			
		</script>
			
	</body>
	
</html>