<%@page import="java.io.InputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
	String fileName = request.getParameter("fileName");
	fileName = fileName.replace("..", "").replace("/", "").replace("\\", "");

	String filePath = "/upload/";

	InputStream is = new FileInputStream(filePath + fileName);

	response.setHeader("Content-Disposition", "attachment; fileName=" + fileName + ";");
	
	/* 기존에 만들어져 있는 out 객체 초기화 */
	out.clear();
	out = pageContext.pushBody();
	
	OutputStream os = response.getOutputStream();
	byte[] buf = new byte[1024];
	int data = 0;
	while((data = is.read(buf)) > 0) {
		os.write(buf, 0, data);
	}
	os.flush();
	os.close();
	is.close();
%>