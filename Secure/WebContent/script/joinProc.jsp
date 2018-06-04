<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<%
	String jumin1 = request.getParameter("jumin1");
	String jumin2 = request.getParameter("jumin2");

 	String method = request.getMethod();
	
	if(!method.equalsIgnoreCase("post")) {
		out.print("잘못된 접근방법입니다");
		return;
	}
	
	String referer = request.getHeader("referer");
	out.print(referer);
	
	if(referer == null) {
		out.print("잘못된 접근방법입니다");
		return;
	}
%>
	<br>
	입력한 주민등록번호 : <%=jumin1%> - <%=jumin2%>
</body>
</html>