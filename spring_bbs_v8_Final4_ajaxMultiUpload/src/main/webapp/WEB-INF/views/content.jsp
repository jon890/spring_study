<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE>
<html>
	<head>
		<meta charset="utf-8">
		<title>글읽기</title>
		<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
			<style type="text/css">
			
				body{
					text-align : center;
				}
				
				#container{
					margin: 0 auto;
					width: 900px;
			    }
				
				table{
					width: 900px;
					border-top : 3px solid black;
					border-bottom : 3px solid lightgray;
				}	
				
				table tr td.desc{
					background-color : Gainsboro;
				}
				
				textarea{
					resize : none;
				}
				
				table tr td{
					height : 60px;
					padding-left : 15px;
				}
				
				table tr td#content{
					height : 400px;
					border : 1px solid Gainsboro;
				}
				
				input{
					width : 150px;
					height : 50px;
					border : none;
					background-color : SkyBlue;
					border-radius : 10px;
					font-size : 16px;
				}
				
				a{
					text-decoration : none;
					color : blue;
				}
				
			</style>
			<script type="text/javascript">
				
				// ajaxSetup (공통 속성 추가)
				$.ajaxSetup({
					type : "POST",
					async : true,
					dataType : "json",
					error : function(xhr){
						alert("에러가 발생했습니다! = " + xhr.statusText);
					}	
				});
				
			
				$(document).ready(function(){
					$("#commentWrite").on("click",function(){
						$.ajax({		
							url:"/human/commentWrite.comment",
		//		 			data{}에서는 EL을 ""로 감싸야함..그외에는 그냥 사용
							data:{				
								commentContent:$("#commentContent").val(),
								articleNum:"${article.articleNum}"
							},
		//		 			beforeSend : function(){
		//		 				alert("시작전");
		//		 			},
		//		 			complete: function(){
		//		 				alert("완료후");
		//		 			},
							success:function(data){
								if(data.result==1){
									alert("comment가 정상적으로 입력되었습니다");
									$("#commentContent").val("");
									showHtml(data.commentList,1);
									//getComment(1, event);
								}
							}
						}); 
					});
					
				});
				
				function getComment(commPageNum, event){
					//event.preventDefault();
					$.ajax({
						url : "/human/commentRead.comment",
						data : {
							articleNum : "${article.articleNum}",
							//숫자와 문자연산에서 + 를 제외하고는 숫자우선
							//문자 10 * 숫자 5 = 50 등..
							commentRow : commPageNum * 10
						},
						success : function(data){
							showHtml(data, commPageNum);
						}
					});
				}
				
				
				function showHtml(data, commPageNum){
					let html = "<table width='500' align='center'>";
					
						html += "<tr>";
						html += "<td class='desc'>답글번호</td>";
						html += "<td class='desc'>댓글 작성자</td>";
						html += "<td class='desc'>댓글 내용</td>";
						html += "<td class='desc'>댓글 작성시간</td>";
						html += "<td class='desc'>원본 글 번호</td>";
						html += "</tr>";
					
					$.each(data, function(index, item){
						html += "<tr>";
						html += "<td >" + (index+1) + "</td>";
						html += "<td>" + item.id + "</td>";
						html += "<td>" + item.commentContent + "</td>";
						html += "<td>" + item.commentDate + "</td>";
						html += "<td>" + item.articleNum + "</td>";
						html += "</tr>";
					});
					
					html += "</table>";
					commPageNum = parseInt(commPageNum);
					
					if("${article.commentCount}" > commPageNum * 10){
						nextPageNum = commPageNum + 1;
						html += "<br> <input type='button' onclick='getComment(nextPageNum, event)' value='다음 댓글 보기'>"
					}
					
					$("#showComment").html(html);
					$("#commentContent").val("");
				}
		
			</script>
	</head>
	<body>   
	
		<form action="/human/replyForm.bbs" method="post">      
			<input type="hidden" name="pageNum" value="${pageNum}" id="human">                 
			<input type="hidden" name="depth" value="${article.depth}">    
			<input type="hidden" name="groupId" value="${article.articleNum}">
		   
			<div id="container">
				<h1> 게시글 상세</h1>
				<p>게시물 번호 - ${article.articleNum} </p>
				<table>  
					<tr>
						<td class="desc">글쓴이</td> <td>${article.id}</td> 			 
						<td class="desc">조회수</td> <td>${article.hit}</td>
					</tr>
					
					<tr>	 				
						<td class="desc">날짜</td><td colspan="3">${article.writeDate}</td>
					</tr>
					
					<tr>	 
						<td class="desc" >제목</td><td colspan="3">${article.title} [${article.commentCount}]</td>
					</tr>
									 
					<tr>		 
						<td colspan="4" id="content">${article.content}</td>
					</tr>
						
					<tr>
						<td colspan="2" class="desc">다운로드 </td>
						<td colspan="2">
							<c:if test="${article.fileStatus == 0}">
								<div>업로드된 파일이 없습니다!</div>
							</c:if>
						
							<c:if test="${article.fileStatus !=0}">
								<c:if test="${files!=null}">
									<ul>
										<c:forEach var="storedFname" items="${files}">
											<li><a href="download.bbs?storedFname=${storedFname}">
											${storedFname.substring(storedFname.indexOf("_") + 1)}</a></li>
											<!-- 
												JSTL 이용
												${fn:substringAfter(storedFname,"_")}
											 -->
										</c:forEach>
									</ul>
								</c:if>
							</c:if>
						</td>
					</tr>		     
					
					<tr>
						<c:if test="${id !=null}">
							<td colspan="4">	    	
								<input type="submit" value="답글달기">
								<c:if test="${id ==article.id}">
									<input type="button" value="수정하기" onclick="document.location.href='/human/update.bbs?articleNum=${article.articleNum}&pageNum=${pageNum}&fileStatus=${article.fileStatus}'">
									<input type="button" value="삭제하기" onclick="document.location.href='/human/delete.bbs?articleNum=${article.articleNum}&pageNum=${pageNum}&fileStatus=${article.fileStatus}'">
								</c:if>
								
							<c:if test="${id !=article.id}">
								<input type="button" value="수정하기" disabled="disabled">
								<input type="button" value="삭제하기" disabled="disabled">
							</c:if>
							<input type="button" value="목록으로" onclick="document.location.href='/human/list.bbs?pageNum=${pageNum}'">
							</td>
						</c:if>
		
						<c:if test="${id ==null}">
							<td colspan="4">
								<input type="submit" value="답글달기" disabled="disabled">
								<input type="button" value="수정하기" disabled="disabled">
								<input type="button" value="삭제하기" disabled="disabled">
								<input type="button" value="목록으로" onclick="document.location.href='/human/list.bbs?pageNum=${pageNum}'">
							</td>   
						</c:if>      	 	      	 
					</tr>	  
		
					<tr>
						<td colspan="4">
							<textarea rows="5" cols="70" id="commentContent"></textarea><br><br>
							<c:if test="${id ==null}">
								<input type="button" value="comment 쓰기" disabled="disabled">    	  
							</c:if> 
							
							<c:if test="${id !=null}">
								<input type="button" value="comment 쓰기" id="commentWrite">
							</c:if>	     	  
							<input type="button" value="comment 읽기(${article.commentCount})" onclick="getComment(1,event)" id="commentRead">	     	       
						</td> 
					</tr> 		   
				</table>
			</div>	
		</form>	
		
		<div>
			<div id="showComment" align="center">
			</div>
			<input type="hidden" id="commPageNum" value="1">
		</div>	
		
	</body>
</html>