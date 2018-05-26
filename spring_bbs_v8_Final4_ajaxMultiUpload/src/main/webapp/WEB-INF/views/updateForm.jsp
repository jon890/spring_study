<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		
	</head>
	
	<body>
	
		<div class="container">
			<form action="/human/update.bbs" method="post">
				<input type="hidden" name="articleNum" value="${articleNum}">
				<input type="hidden" name="pageNum" value="${pageNum}">
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
						<td><input type="submit" value="수정하기"></td>
						<td><input type="reset" value="수정하기취소"></td>
					</tr>
				</table>
				
				<div id="uploadedList">
					<h3>업로드 된 목록</h3>
					<ul>
						<c:forEach var="file" items="${files}">
								<%-- <c:if test="${file.substring(file.lastIndexOf('.') + 1) == 'png' |
											${file.substring(file.lastIndexOf('.') + 1) == 'gif' |
											${file.substring(file.lastIndexOf('.') + 1) == 'jpg'}">
									<div>
										<img src='displayFile.bbs?fileName="${file}"'/>
										<small class='human' data-src='"${file}"'>X</small>
									</div>
								</c:if>
								
								<c:if test="${file.substring(file.lastIndexOf('.') + 1) != 'png' &
											${file.substring(file.lastIndexOf('.') + 1) != 'gif' &
											${file.substring(file.lastIndexOf('.') + 1) != 'jpg'}">
									<div>
										${file}
										<small class='human' data-src='"${file}"'>X</small>
									</div>
								</c:if> --%>
								<li>${file.substring(file.indexOf('_') + 1)}
									<small class='human' data-src='"${file}"'>X</small>
								</li>
						</c:forEach>
					</ul>
				</div>
			</form>
		</div>
	</body>
</html>












