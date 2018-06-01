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
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
	$(document).ready(function(){	 
		  $(".memId").on("click",function(event){		  
			  var mleft= event.pageX;
			  var mtop= event.pageY;		  
			  var receiveId=$(this).text();		  		 
			  window.open("messageForm.message?receiveId="+receiveId,"message","width=2, height=2,resizable=no, toolbar=no, location=no, status=no,menubar=no,left="+mleft+",top="+mtop);
		  });
		  
			$("#allSelect").on("click", function(){
				$(".delMsgs").prop("checked", true);			
			});
			
/* 			$("#messageDelete").on("click", function(){
					
				$(".delMsgs:checked").each(function() { 
			       alert($(this).val());
			    });
					
				// input[type=checkbox] 를 submit 할 때 check 되지 않은 속성들은 값이 전달되지 않는다
				$("#msgForm").attr("action","messageDelete.message?pageNum=" + ${pageNum});
				$("#msgForm").submit();
			}); 
			
			*/
			
			/* 비동기로 연습 */
			$("#messageDelete").on("click", function(){
				
				let delMsgs = [];
				
				$.each($(".delMsgs:checked"), function(index, item){
					delMsgs.push($(this).val());
				});
				
				$.ajaxSettings.traditional = true;
				
			    $.ajax({
				   url:"/bbs/messageDelete.message",
				   type:"post",
				   data: {delMsgs : delMsgs},
				   dataType:"text",		 
				   success:function(result){
					   if(result == 'deleted'){
						   alert("삭제성공");
					   }
				   }
			    });
			});
			/* 비동기로 연습 */
			
	  });
</script>
</head>

<body>
  <%@ include file="../bbs/header.jsp" %>    
 
 <div id="includeHeader"></div>
  
  <%@ include file="../bbs/leftAside.jsp" %>
 
 
<section class="content">
	<form id="msgForm" method="post">
	<div>
		<b>쪽지목록(전체 쪽지:${messageCount})</b>
		<input type="button" value="모두 선택" id="allSelect">
		<input type="button" value="삭제" id="messageDelete">
	</div>
	<br/>
	
	<table border="1" cellpadding="2" cellspacing="2"> 
	    <tr height="30" > 
	     <td align="center"  width="50"  >선 택</td> 
	      <td align="center"  width="50"  >번 호</td> 
	      <td align="center"  width="320" >내 용</td> 
	      <td align="center"  width="100" >보낸사람</td>
	      <td align="center"  width="150" >작성일</td>	               
	    </tr>
	
	   <c:forEach var="message" items="${messageList}">
	      
	   <tr height="30">
	   
	  	<td align="center"  width="50" >
	   		<input type="checkbox" name="delMsgs" class="delMsgs" value="${message.messageNum}">  
		</td>
		
	    <td align="center"  width="50" >
		  <c:out value="${message.messageNum}"/>	   
		</td>
		
	    <td width="320" style="text-overflow : ellipsis; display:block; overflow:hidden;">	   	             
	      <a href="/bbs/messageContent.message?messageNum=${message.messageNum}&pageNum=${pageNum}&messageStatus=${message.messageStatus}">
	         <c:if test="${message.messageStatus==1}"><b>${message.messageContent}</b></c:if>
	         <c:if test="${message.messageStatus==0}">${message.messageContent}</c:if>
	      </a>	          
		</td>
		
	    <c:if test="${id!=null}">
	    <td align="center"  width="100"><label class="memId">${message.id}</label></td>
	    </c:if>
	    <td align="center"  width="150">${message.messageDate}</td>	    
	  </tr>
	  </c:forEach>
	  <tr>	  
	      <td colspan="5" align="center" height="40">	 
		  ${pageCode}
		  </td>
	  </tr>
	</table>
	</form>
</section>
<%@ include file="../bbs/rightAside.jsp" %>
<%@ include file="../bbs/footer.jsp" %>

</body>
</html>