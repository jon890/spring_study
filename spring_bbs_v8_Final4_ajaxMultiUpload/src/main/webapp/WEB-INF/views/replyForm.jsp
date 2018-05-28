<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
	<head>
		<meta charset="UTF-8">
		<title>답글쓰기</title>
		
		<style>
			.fileDrop{
				border : 2px dotted darksalmon;
				width : 400px;
				height : 200px;
				background-color : silk;
			}
			
			.fileDrop p{
				color : gray;
				text-align : center;
				line-height : 10;
			}
			
			small{
				margin-left : 3px;
				font-weight : bold;
				color : gray;
			}
			
			input[type="button"]{
				color : white;
				background-color : darksalmon;
				border : none;
				margin : 10px 10px 10px 90px;
			}
		</style>
		
		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
		
		<!-- Optional JavaScript -->
    	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
		<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
	</head>
		
	<body>

		<div class="container">
			<form action="/human/reply.bbs" method="post">     
			<input type="hidden" id="fileStatus" name="fileStatus" value="0">  
			<input type="hidden" name="pageNum" value="${pageNum}">    
			<input type="hidden" name="depth" value="${depth}">
			<input type="hidden" name="groupId" value="${groupId}">
			
				<table class="table">  
					<tr>
			 			 <td>작성자</td>
			 			 <!-- session에서 가져온다-->
			 			 <!-- request > session > application 순으로 찾는다 -->
			 			 <td>${id}</td>
			 		</tr>
			 		
			 		<tr>	 
					 <td>제목</td>
					 <td><input type="text" name="title" id="title" value="[RE]"></td>			 
					</tr>
					
					<tr>
					  <td colspan="2">
					  	<textarea cols="100" rows="20" name="content" id="content"></textarea>
					  </td>
				    </tr>
				        
				    <tr>
				      <td><input type="submit" value="글쓰기"></td>
				      <td><input type="reset" value="글쓰기취소"></td>	      	 
				    </tr>
				    		
				</table>
				
				<div class="fileDrop">
					<p>업로드 할 파일을 여기에 올려두세요</p>
				</div>
				<input type="button" value="업로드 파일 모두 삭제">
				<div class="uploadedList"></div>
			</form>
		</div>
		
		
		<script>
			$("input[type=reset]").on("click", function(){
				if( $("#fileStatus") == 1 ){
					allDeleteFiles();
				}
			});
			
			function allDeleteFiles(){
				let files = [];
				$.each($(".human"), function(index, item){
					//files[index] = $(this).attr("data-src");
					files.push($(this).attr("data-src"));
				});
				
				$.ajaxSettings.traditional = true;
				
				$.ajax({
					url : "/human/deleteAllFiles.bbs",
					type : "POST",
					data : {files: files},
					dataType : "TEXT",
					
					success : function(result){
						if(result == 'deleted'){
							$(".uploadedList").children().remove();
							alert("업로드 된 파일을 모두 삭제 하였습니다.");
						}
					},
					
					error : function(xhr){
						alert("삭제할 파일이 없습니다!");
					}
				});
			}
			
			/* 파일 드로그 앤 드랍시 기본 동작을 막기 */
			$(".fileDrop").on("dragenter dragover", function(event){
				event.preventDefault();
			});
			
			$(".fileDrop").on("drop", function(event){
				event.preventDefault();
			/* 파일 드로그 앤 드랍시 기본 동작을 막기 */
				
				let files = event.originalEvent.dataTransfer.files;
				let formData = new FormData();
				$.each(files, function(index, item){
					formData.append("multiFile", item);
				});
				
				$.ajax({
					url : '/human/uploadAjax.bbs',
					data : formData,
					dataType : 'Json',
					processData : false,
					contentType : false,
					type : 'POST',
					
					success : function(data){
						let str = "";
						$.each(data, function(index, fileName){
							if(checkImageType(fileName)){						 
								  str ="<div><img src='displayFile.bbs?fileName="+fileName+"'/>"	
										  +"<small class='human'  data-src='"+fileName+"'>X</small>"
										  +"<input type='hidden' name='files' value='"+ getImageLink(fileName) +"'></div>";
							  }else{
								  str = "<div><p>" + getOriginalName(fileName)
										  +"</p><small class='human' data-src='"+fileName+"'>X</small>"
										  +"<input type='hidden' name='files' value='"+fileName+"'></div>";
							  }
						
							$(".uploadedList").append(str);
							$("#fileStatus").val(1);	
						});
						
					},
					
					error : function(xhr){
						alert("오류가 발생했습니다.");
					}
				});				
			});
			
			$(".uploadedList").on("click", "small", function(event){
				let that = $(this);
				$.ajax({
					url : '/human/deleteFile.bbs',
					type : 'POST',
					data : {
						fileName :$(this).attr("data-src")
					},
					dataType : 'text',
					
					success : function(result){
						if(result == 'deleted'){
							that.parent("div").remove();
						}
					}
				});
			});
			
			function checkImageType(fileName){
				let pattern = /.jpg|.gif|.png/i;
				return fileName.match(pattern);
			}
			
			function getImageLink(fileName){
				let front = fileName.substr(0, 12);
				let end = fileName.substr(14);
				return front + end;
			}
			
			function getOriginalName(fileName){
				let idx = fileName.indexOf("_") + 1;
				return fileName.substr(idx);
			}
			
		</script>
			
	</body>
	
</html>