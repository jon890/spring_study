<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>사용자 정보</title>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script>
		$(document).ready(function() {	
//		$(function()){
			$("input[name=modifyBtn]").on("click", function() {		
				$.ajax({
					url : "modify.mongo",					
					type : "POST",
					async : true,
					dataType : "json",
					data : $("form").serialize(),
					success : function(data) {			
						$("input[name=id]").val(data.id);
						$("input[name=name]").val(data.name);
						$("input[name=gender]").val(data.gender);
						$("input[name=address1]").val(data.address.si);
						$("input[name=address2]").val(data.address.gu);				
					},
					error : function(error) {
						alert(error.statusText+"이것이 무엇인고");
					}
				});
			});
			
			$("input[name=deleteBtn]").on("click", function() {
				$.ajax({
					url : "delete.mongo",
					type : "POST",
					async : true,
					dataType : "json",
					data : {"id" : $("input[name=id]").val()},
					success : function(data) {				
						if(data.code == '1') {
							location.href="main.mongo";
						}else{
							alert("삭제에 실패");
							location.reload();
						}
					},
					error : function(error) {
						alert(error.statusText);
					}
				});
			});
		});
	</script>
</head>


<body>
	<form>
		<c:choose>
			<c:when test="${user.id != null}">
				<input type="hidden" name="id" value="${user.id}" />
				<table border="1">
					<tr>
						<td colspan="2">이름 : <input type="text" name="name" value="${user.name}"  /></td>
					</tr>
					<tr>
						<td colspan="2">성별 : <input type="text" name="gender" value="${user.gender}" /></td>
					</tr>
					<tr>
						<td>주소 : <input type="text" name="address1" 
						value="${user.address.si}" /></td>
						<td>주소 : <input type="text" name="address2" 
						value="${user.address.gu}" /></td>						
					</tr>
				</table>
				<input type="button" name="modifyBtn" value="수정" />
				<input type="button" name="deleteBtn" value="삭제" />
			</c:when>
			<c:otherwise>
				데이터를 찾을수 없음
			</c:otherwise>
		</c:choose>
	</form>
</body>

</html>