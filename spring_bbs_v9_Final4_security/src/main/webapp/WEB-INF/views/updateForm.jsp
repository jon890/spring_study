<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
	<head>
	
		<meta charset="utf-8">
		<title>글 수정</title>
		
		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
		
		<!-- Optional JavaScript -->
    	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
		<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
		
		<style>
			#fileDrop{
				border : 2px dotted darksalmon;
				width : 400px;
				height : 200px;
				background-color : yellow;
				margin-bottom : 30px;
			}
			
			#fileDrop p{
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
			}
			
			div#uploadedList{
				border : 2px solid gray;
				margin-top : 30px;
				margin-bottom : 30px;
			}
	
		</style>
		
	</head>
	
	<body>
	
		<div class="container">
			<form:form action="/human/update.bbs" method="post">
				<input type="hidden" name="articleNum" value="${articleNum}">
				<input type="hidden" name="pageNum" value="${pageNum}">
				<input type="hidden" id="fileStatus" name="fileStatus" value="${fileStatus}">
				<input type="hidden" name="fileCount" value="${fileCount}">
				<table class="table">
					<tr>
						<td>작성자</td>
						<td>${id}</td>
					</tr>
					<tr>
						<td>제목</td>
						<td><input type="text" name="title" value="${article.title}"></td>
					</tr>
					<tr>
						<td colspan="2"><textarea cols="100" rows="20" name="content">${article.content}</textarea>
						</td>
					</tr>
					<tr>
						<td>이전에 첨부된 파일</td>
						<td>
							<c:if test="${files==null}">
								<div>업로드된 파일이 없습니다!</div>
							</c:if>
							<c:if test="${files!=null}">
								<ul id="delGroup">
									<c:forEach var="storedFname" items="${files}">
										<li>${storedFname.substring(storedFname.indexOf("_")+1)}
											<input type="button" deleteFileName="${storedFname}" value="삭제" class="delFile">
										</li>
									</c:forEach>
								</ul>
							</c:if>
						</td>
					</tr>
				<tr>
						<td><input type="submit" value="수정하기"></td>
						<td><input type="reset" value="모두 초기화하기"></td>
					</tr>
				</table>
				
				<div id="fileDrop">
					<p>업로드 할 파일을 여기에 올려두세요</p>
				</div>
							
				<div id="uploadedList">
					<h3>수정시 업로드한 목록</h3>
						<%-- <c:if test="${files != null}">
							<c:forEach var="file" items="${files}">
								<div>
									<c:if
										test="${common.getFormatName(file) == 'png' ||
												common.getFormatName(file) == 'jpg' ||
												common.getFormatName(file) == 'gif'}">
										<img src='displayFile.bbs?fileName="${file}"' />  
										<small class='human' data-src='"${file}"'>X</small><
										<input type='hidden' name='files' value="getImageLink(file)">
									</c:if> 
									<c:if
										test="${common.getFormatName(file) != 'png' &&
												common.getFormatName(file) != 'jpg' &&
												common.getFormatName(file) != 'gif'}">
										${file.substring(file.indexOf('_') + 1)}
										<small class='human' data-src='"${file}"'>X</small>
										<input type='hidden' name='files' value='"${file}"'>
									</c:if>
								</div>
							</c:forEach>
						</c:if> --%>
				</div>
				<input type="button" id="allDelete" value="업로드된 파일 모두 삭제" onclick="allDeleteFiles()">
			</form:form>
		</div>

		<script>
			$(document).ready(function(){
				// 이미 업로드된 파일 삭제 버튼을 눌렀을 때
				// 버튼이 몇 개 생길지 모르므로 class 속성으로 선택 (id는 한 개 밖에 못 받아온다)
				$(".delFile").on("click", function(){
					alert($(this).attr("deleteFileName"));
					// deleteFileName이 원래 있던 속성이 아니므로 아래처럼 하면 못읽어 온다
					// let storedFname=$(this).prop("deleteFileName");
					let storedFname=$(this).attr("deleteFileName");
					$(this).parent().remove();
					let deleteFileName = "<input type='hidden' name='deleteFileName' value='"+storedFname+"'>";
					alert(deleteFileName);
					$(deleteFileName).appendTo("form");
				});
			});
		
		
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
							$("#uploadedList").children().remove();
							alert("업로드 된 파일을 모두 삭제 하였습니다.");
							$("#fileStatus").val(0);
							
						}
					},
					
					error : function(xhr){
						alert("삭제할 파일이 없습니다!");
					}
				});
			}
			
			/* 파일 드로그 앤 드랍시 기본 동작을 막기 */
			$("#fileDrop").on("dragenter dragover", function(event){
				event.preventDefault();
			});
			
			$("#fileDrop").on("drop", function(event){
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
								  str = "<div>" + getOriginalName(fileName)
										  +"<small class='human' data-src='"+fileName+"'>X</small>"
										  +"<input type='hidden' name='files' value='"+fileName+"'></div>";
							  }
						
							$("#uploadedList").append(str);
							$("#fileStatus").val(1);
						});
						
					},
					
					error : function(xhr){
						alert("오류가 발생했습니다.");
					}
				});				
			});
			
			$("#uploadedList").on("click", "small", function(event){
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
							//alert("업로드 한 파일 " + that.siblings("p").val() + " 이 삭제 되었습니다");
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












