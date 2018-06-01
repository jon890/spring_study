<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>  
<head>
<meta charset="UTf-8">
<title>게시판</title> 
<link href="resources/style.css" rel="stylesheet" type="text/css">
<link href="resources/gun.bmp" rel="shortcut icon">
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
$(document).ready(function(){	 
	  $(".messageId").on("click",function(event){		  
		  let mleft= event.pageX;
		  let mtop= event.pageY;		  
		  let receiveId=$(this).text();		  		 
		  window.open("messageMain.message?receiveId="+receiveId, "message","width=2, height=2,resizable=no, toolbar=no, location=no, status=no,menubar=no,left="+mleft+",top="+mtop);
	  });	  
  }); 

</script>
</head>

<body>
  <%@ include file="header.jsp" %>    
 
 <div id="includeHeader"></div>
  
  <%@ include file="leftAside.jsp" %> 
 
<section class="content">	
	<div>
		<b>글목록(전체 글:${totalCount})</b>
		<a href="/bbs/writeForm.bbs">글쓰기</a>		
	</div>
	<br/>
	
	<table border="1" cellpadding="2" cellspacing="2"> 
	    <tr height="30" > 
	      <td align="center"  width="50"  >번 호</td> 
	      <td align="center"  width="270" >제   목</td> 
	      <td align="center"  width="100" >작성자</td>
	      <td align="center"  width="150" >작성일</td> 
	      <td align="center"  width="50" >조 회</td>          
	    </tr>
	
	   <c:forEach var="article" items="${articleList}">
	   <tr height="30">
	    <td align="center"  width="50" >
		  <c:out value="${article.articleNum}"/>	   
		</td>
	    <td  width="270" >  
	      <c:if test="${article.depth > 0}">
		  	<img src="images/image3.png" width="${10 * article.depth}"  height="16">
		    <img src="images/cut.gif">
		  </c:if>
		  <c:if test="${article.depth == 0}">
		    <img src="images/image3.png" width="0"  height="16">
		  </c:if>         
	      <a href="/bbs/content.bbs?articleNum=${article.articleNum}&pageNum=${pageNum}&fileStatus=${article.fileStatus}">
	          ${article.title}</a> 
	          <c:if test="${article.hit >= 20}">
	            <img src="images/image3.png" border="0" height="16">
			  </c:if>
		</td>
		<c:if test="${id==null}">
	    	<td align="center"  width="100">${article.id}</td>
	    </c:if>
	    <c:if test="${id!=null}">
	    	<td align="center"  width="100"><label class="messageId">${article.id}</label></td>
	    </c:if>
	    <td align="center"  width="150">${article.writeDate}</td>
	    <td align="center"  width="50">${article.hit}</td>
	  </tr>
	  </c:forEach>
	  <tr>	  
	      <td colspan="5" align="center" height="40">	 
		  ${pageCode}
		  </td>
	  </tr>
	</table>
</section>
<%@ include file="rightAside.jsp" %>
<%@ include file="footer.jsp" %>

</body>
</html>