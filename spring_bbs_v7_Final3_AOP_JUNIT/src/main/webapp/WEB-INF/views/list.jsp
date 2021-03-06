<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>게시판_버전6</title>
		<link href="resources/daram2.png" rel="shortcut icon">
		
		
		<style>		
			body{
				text-align : center;
			}
			
			#container{
				margin : 0 auto;
				width : 800px;
			}
				
			table{
				border : 1px solid black;
				margin : 20px;
			}
			
			table tr td{
				border : 1px solid gray;
				padding : 5px;
			}
			
			table tr td#title{
				width : 300px;
			}
			
			input#write {
				background-color : darksalmon;
				color : white;
				width : 200px;
				height : 50px;
				font-size : 1.5em;
				border : none;
			}
		</style>
		
	</head>
	
	
	
	<body>
	
		<div id="container">
		
		<c:if test="${id!=null}">
 			<%@include file="loginOk.jsp" %>
 		</c:if>
 
 		<c:if test="${id==null}">
 			<%@include file="login.jsp" %>
 		</c:if>


		<h1>글목록(전체 글:${totalCount})</h1>
		
		<table>
			<tr>
				<td align="center" width="50">번 호</td>
				<td align="center" width="250">제 목</td>
				<td align="center" width="100">작성자</td>
				<td align="center" width="150">작성일</td>
				<td align="center" width="50">조 회</td>
			</tr>

			<c:forEach var="article" items="${articleList}">
				<tr>
					<td width="50"><c:out
							value="${article.articleNum}" /></td>
					<td align="left" width="250">
						<c:if test="${article.depth > 0}">
							<img src="resources/img/blank.png" width="${10 * article.depth}"
								height="16">
							<img src="resources/img/reply.png" width="16" height="16">
						</c:if> 
						<c:if test="${article.depth == 0}">
							<img src="resources/img/blank.png" width="0" height="16">
						</c:if> 
						
						<a href="/human/content.bbs?articleNum=${article.articleNum}
						&pageNum=${pageNum}&fileStatus=${article.fileStatus}">
						${article.title}</a>
		      		    <c:if test="${article.commentCount!=0 }">
		      		    	<span style="color:red">[${article.commentCount}]</span>
		      		    </c:if>
						
						
						<c:if test="${article.fileStatus != 0}">
							<img src="resources/img/download.png" width="16" height="16">
						</c:if>
						
						<c:if test="${article.hit >= 20}">
							<img src="resources/img/blank.png" border="0" height="16">
						</c:if>
						
						
					</td>
					<td width="100">${article.id}</td>
					<td width="150">${article.writeDate}</td>
					<td width="50">${article.hit}</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="5" height="40">${pageCode}</td>
			</tr>
		</table>
	</div>	

	
		<hr>
		<!-- a 태그는 get 방식이다. -->
		<!-- <a href="/human/write.bbs">글쓰기</a> -->
		<form action="/human/write.bbs" method="get">
			<input type="submit" value="글쓰기" id="write">
		</form>
			
	</body>
	
</html>