<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
	
		<meta charset="utf-8">
		<title>수정하기</title>
		
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
		<form action="/human/update.bbs" method="post">
			<input type="hidden" name="articleNum" value="${articleNum}">
			<input type="hidden" name="pageNum" value="${pageNum}">
			<table>
				<tr>
					<td>글쓴이 :</td>
					<td>${id}</td>
				</tr>
				<tr>
					<td>제목 :</td>
					<td><input type="text" name="title" value="${article.title}"></td>
				</tr>
				<tr>
					<td colspan="2"><textarea cols="50" rows="20" name="content">${article.content}</textarea>
					</td>
				</tr>
				<tr>
					<td><input type="submit" value="수정하기"></td>
					<td><input type="reset" value="수정하기취소"></td>
				</tr>
			</table>
		</form>
	</body>
</html>












