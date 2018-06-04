<%@page import="java.nio.file.Path"%>
<%@page import="java.nio.file.Files"%>
<%@page import="java.nio.file.Paths"%>
<%@page import="javax.activation.MimetypesFileTypeMap"%>
<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<%
// 	String uploadPath = "/upload/";
// 	String uploadPath = this.getClass().getClassLoader().getResource("").getPath();
	String uploadPath = getServletContext().getRealPath("");
	
// 	InputStream is = request.getInputStream();
// // 	InputStreamReader isr = new InputStreamReader(is, "utf8");
// 	BufferedReader reader = new BufferedReader(isr);
// 	String data = "";
// 	int data = 0;
// 	while((data = is.read()) > -1) {
// 		out.print((char)data);
// 	}
// 	out.flush();

	int size = 100 * 1024 * 1024; // 업로드 용량 제한 100메가
	MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "utf-8", new DefaultFileRenamePolicy());

	String title = multi.getParameter("title");
	
	// 사용자가 업로드한 원본 파일명
	String originalFileName = multi.getOriginalFileName("file");

	File f = multi.getFile("file");
	
	// 업로드 후 변경된 파일명
	String uploadFileName = f.getName();
	long fileSize = f.length();

	
	out.print("업로드 경로 : " + uploadPath + "<br>");
	out.print("제목 : " + title + "<br>");
	out.print("업로드 원본 파일명 : " + originalFileName + "<br>");
	out.print("업로드 후 변경된 파일명 : " + uploadFileName + "<br>");
	out.print("업로드 파일 크기 : " + fileSize + "<br>");

// 	out.print(new MimetypesFileTypeMap().getContentType(f) + "<br>");
	
// 	Path path = Paths.get(uploadPath + uploadFileName);
// 	String type = Files.probeContentType(path);
// 	out.print(type + "<br>");

//	Tika tika = new Tika();
//	out.print(tika.detect(f) + "<br>");

%>
</body>
</html>