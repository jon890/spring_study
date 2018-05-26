<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
	<head>
		<meta charset="UTF-8">
		<title>글쓰기</title>
		
		<style>	
			.fileDrop{
				width : 400px;
				height : 150px;
				border : 1px dotted blue;
				margin : auto 0;
			}
			
			small{
				margin-left : 3px;
				font-weight : bold;
				color : gray;
			}
		</style>
		
		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
		
		<!-- Optional JavaScript -->
    	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
		<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>

	</head>
		
	<body>

		<div class="container">
			<form action="/human/write.bbs" method="post">
				<input type="hidden" id="fileStatus" name="fileStatus" value="0">
				<table class="table">  
					<tr>
			 			 <td>작성자</td>
			 			 <!-- session에서 가져온다-->
			 			 <!-- request > session > application 순으로 찾는다 -->
			 			 <td>${id}</td>
			 		</tr>
			 		
			 		<tr>	 
					 <td>제목</td>
					 <td><input type="text" name="title"></td>			 
					</tr>
					
					<tr>
					  <td colspan="2">
					  	<textarea cols="100" rows="20" name="content"></textarea>
					  </td>
				    </tr>
				        
				    <tr>
				      <td><input type="submit" value="글쓰기"></td>
				      <td><input type="reset" value="글쓰기취소"></td>	      	 
				    </tr>		    		
				</table>
				
				<div class="fileDrop"></div>
				<input type="button" value="모두삭제" onclick="allDeleteFiles()"/>
				<div class="uploadedList"></div>
				
			</form>
		</div>
		
		<script>
		
			// 초기화 버튼을 누르면 업로드 된 파일 삭제
			$("input[type=reset]").on("click", function(){
				if( $("#fileStatus") == 1){
					allDeleteFiles();
				} 
			});
	
			function allDeleteFiles(){			
				let files=[];
				$.each($(".human"),function(index,item){
					//files[index]=$(this).attr("data-src");
					files.push($(this).attr("data-src"));						
				});
				
				// 배열 직렬화 전송
				$.ajaxSettings.traditional = true;
				
				$.ajax({
					url:"/human/deleteAllFiles.bbs",
					type:"post",
					data: {files: files},
					dataType:"text",		  
					success:function(result){
						if(result == 'deleted'){
							$(".uploadedList").children().remove();
							alert("업로드 된 파일을 모두 삭제 하였습니다.");
						}
					} ,
					error : function(xhr){
					alert("삭제할 파일이 없습니다!"); 
					}
				});
			}
		
			/* 파일 드로그 앤 드랍시 기본 동작(파일 실행)을 막기 */
			$(".fileDrop").on("dragenter dragover", function(event){
				event.preventDefault();
			});
			
			$(".fileDrop").on("drop", function(event){
				event.preventDefault();
			/* 파일 드로그 앤 드랍시 기본 동작(파일 실행)을 막기 */
				
			// 드로그앤 드롭한 파일을 이벤트에서 받아오기
				let files = event.originalEvent.dataTransfer.files;
				let formData = new FormData();
				$.each(files, function(index, item){
					formData.append("multiFile", item);
					//                  키           ,  밸류
					//Reqeust에서 multiFile로 읽어 들이기
				});
				
				$.ajax({
					  url: '/human/uploadAjax.bbs',
					  data: formData,
//		 			  복수개를 업로드시 
					  dataType:'json',	
					  
//		 			  processData: false는
//		 			  데이터를 일반적인 query string으로 변활할 것인지를 결정, 기본값은 true , 
//		 			  'application/x-www-form-urlencoded' 타입으로 전송, 다른 형식으로 데이터를
//		 			  보내기 위하여 자동 변환하고 싶지 않은 경우는 false로 지정
					  processData: false,
					  
//		 			  contentType: false 는
//		 			  기본값은 'application/x-www-form-urlencoded', 파일의 경우 'multipart/form-data'
//		 			  방식으로 전송하기 위해서 false
					  contentType: false,
					  type: 'POST',
					  success: function(data){
						  let str ="";				 
						  //alert(data);				  
						  $.each(data,function(index, fileName){					  					 
							  if(checkImageType(fileName)){						 
								  str ="<div><img src='displayFile.bbs?fileName="+fileName+"'/>"	
										  +"<small class='human'  data-src='"+fileName+"'>X</small></div>"
										  +"<input type='hidden' name='files' value='"+ getImageLink(fileName) +"'>";
							  }else{
								  str = "<div>" + getOriginalName(fileName)
										  +"<small class='human' data-src='"+fileName+"'>X</small></div>"
										  +"<input type='hidden' name='files' value='"+fileName+"'>";
							  }
							  
							  $(".uploadedList").append(str);
							  $("#fileStatus").val(1);
							  //alert($("#fileStatus").val());
						  });				 
					  },
					  error : function(xhr){
							alert("error html = " + xhr.statusText);
					  }			  
					});	
			});
			
			$(".uploadedList").on("click", "small", function(event){			
				let that = $(this);
			   $.ajax({
				   url:"/human/deleteFile.bbs",
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
			
			
			function checkImageType(fileName){			
//		 		/i는 대소문자 구분 하지 말라는 뜻임
				let pattern = /.jpg|.gif|.png/i;
				return fileName.match(pattern);
			}
			
			function getImageLink(fileName){
				if(!checkImageType(fileName)){
					return;
				}	
				let front = fileName.substr(0,12);
				let end = fileName.substr(14);
				//alert(front + end);		
				return front + end;	
			}
			
			function getOriginalName(fileName){
				if(checkImageType(fileName)){
					return;
				}
				
				let idx = fileName.indexOf("_") + 1 ;
				return fileName.substr(idx);
			}
			
		</script>
			
	</body>
	
</html>