<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTf-8">
<title>Insert title here</title>
</head>
<body>
    <header>
    	<div class="login">
			<c:if test="${id!=null}">
				<%@include file="../login/login_ok.jsp"%>
			</c:if>
	
			<c:if test="${id==null}">
				<%@include file="../login/login.jsp"%>
			</c:if>
	    </div>	 
	    <h1>Spring Framework</h1>
    	<h2>게시판 구현</h2>
    	   	
	</header> 	

	<nav class="navi">
      <ul>
        <li><a href="#">사이트 소개</a></li>
        <li><a href="/bbs/list.bbs?pageNum=1">게시판 글보기</a></li>
        <li><a href="#">기타 등등</a></li>
        <li><a href="/bbs/main.bbs">메인으로 가기</a></li>
      </ul>
  </nav> 
</body>
</html>