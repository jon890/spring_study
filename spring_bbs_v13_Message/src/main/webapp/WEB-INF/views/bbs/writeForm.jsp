<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<style>
.fileDrop {
	width: 400px;
	height: 150px;
	border: 1px dotted blue;
	margin: auto,0;
}

small {
	margin-left: 3px;
	font-weight: bold;
	color: gray;
}
</style>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<!-- <script src="resources/js/uploadAjax.js"></script>  -->
</head>
<body>
<div class="container">
<form action="/bbs/write.bbs" method="post">
	<table class="table" border="2" width="200">  
		<tr>
 			 <td>글쓴이 :</td>
 			 <td>${id}</td>
 		</tr>
 		<tr>	 
		 <td>제목 : </td>
		 <td><input type="text" name="title"></td>			 
		</tr>
		<tr>
		  <td colspan="2">
		  <textarea cols="50" rows="20" name="content" ></textarea>
		  </td>
	    </tr>    
	    <tr>
	      <td><input type="submit" value="글쓰기"></td>
	      <td><input type="reset" value="글쓰기취소"></td>	      	 
	    </tr>		
	</table>
	
	 <div class='fileDrop'></div>
	 <input type="button" value="모두삭제"/>
	 <div class='uploadedList'></div>	
</form>
</div>
<script>
// 글쓰기 취소시에 업로드 되어있는 파일 삭제
   $("input[type=reset]").on("click", function(){	 
	   allDeleteFiles();	
	});
	
$(".fileDrop").on("dragenter dragover", function(event){
		event.preventDefault();		
	});
	
$(".fileDrop").on("drop", function(event){
	event.preventDefault();	
	var files = event.originalEvent.dataTransfer.files;				
	var formData = new FormData();
	$.each(files,function(index,item){
		formData.append("multiFile", item);
	});		
	
	$.ajax({
		  url: '/bbs/uploadAjax.bbs',
		  data: formData,
// 			  복수개를 업로드시 
		  dataType:'json',		  
		  processData: false,
		  contentType: false,
		  type: 'POST',
		  success: function(data){
			  var str ="";				 
			  alert(data);				  
			  $.each(data,function(index, fileName){					  					 
				  if(checkImageType(fileName)){						 
					  str ="<div><img src='displayFile.bbs?fileName="+fileName+"'/>"	
						  +"<small class='human' data-src='"+fileName+"'>X</small>"
// 	 				 이미지 파일일 경우에는 이름에 s_ 가 포함되어있으므로 테이블에 바로 입력하면
// 	 				 다운로드시 썸네일 파일을 다운로드 받게됨...이름에 s_ 제거하고 테이블에 입력
						  +"<input type='hidden' name='fileNames' value='"+getImageLink(fileName)+"'></div>";
				  }else{
				 	  str ="<div>"+ getOriginFname(fileName)
						  +"<small class='human' data-src='"+fileName+"'>X</small>"
						  +"<input type='hidden' name='fileNames' value='"+fileName+"'></div>";
				  }
				  
				  $(".uploadedList").append(str);
			  });				 
		  },
		  error : function(xhr){
				alert("error html = " + xhr.statusText);
		  }			  
		});	
});

$(".uploadedList").on("click", "small", function(event){			
	var that = $(this);
   $.ajax({
	   url:"/bbs/deleteFile.bbs",
	   type:"post",
	   data: {
		   fileName:$(this).attr("data-src")
	   },
	   dataType:"text",		 
	   success:function(result){
		   if(result == 'deleted'){				   
			   that.parent("div").remove();
			   alert("삭제성공");
		   }
	   }
   });
});

$("input[type=button]").on("click", function(event){			
 	allDeleteFiles();
});

function allDeleteFiles(){
	var files=[];
	$.each($(".human"),function(index,item){
// 			files[index]=$(this).attr("data-src");
		files.push($(this).attr("data-src"));						
	});	
// 	배열을 직렬화해서 전송함 
   jQuery.ajaxSettings.traditional = true;
   $.ajax({
	   url:"/bbs/deleteAllFiles.bbs",
	   type:"post",
	   data: {files: files},
	   dataType:"text",		  
	   success:function(result){
		   if(result == 'deleted'){
			   $(".uploadedList").children().remove();
			   alert("삭제성공");
		   }
	   }
   });
}

function checkImageType(fileName){	
// 		/i는 대소문자 구분 하지 말라는 뜻임
	var pattern = /.jpg|.gif|.png/i;		
	return fileName.match(pattern);		
}

function getImageLink(fileName){
	if(!checkImageType(fileName)){
		return;
	}	
	var front = fileName.substr(0,12);
	var end = fileName.substr(14);			
	return front + end;	
}

function getOriginFname(fileName){
	if(checkImageType(fileName)){
		return;
	}
	
	var idx = fileName.indexOf("_") + 1 ;
	return fileName.substr(idx);	
}
</script>

</body>
</html>