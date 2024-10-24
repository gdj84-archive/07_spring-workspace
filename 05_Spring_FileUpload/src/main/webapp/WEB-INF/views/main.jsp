<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<style>
	form>label {
		font-size: 12px;
		color: gray;
	}
</style>
</head>
<body>

	<script>
		$(function(){
			$("input[type=file]").on('change', function(evt){
				
				const files = evt.target.files; // FileList {0:File, 1:File, ..}
				console.log(files);
				
				let totalSize = 0;
				
				for(let i=0; i<files.length; i++){
					
					if(files[i].size > 10 * 1024 * 1024){ // 첨부파일 한개의 크기가 10MB 초과할 경우
						alert("첨부파일의 최대 크기는 10MB입니다.");
						evt.target.value = "";
						return;
					}
					
					totalSize += files[i].size;
					
					if(totalSize > 100 * 1024 * 1024){ 		// 전체 첨부파일 사이즈가 100MB 초과할 경우
						alert("전체 첨부파일 최대 크기는 100MB입니다.");
						evt.target.value = "";
						return;
					}
					
				}
				
			})
			
		})
	</script>

	
	<h2>1. 한 개의 첨부파일 업로드 테스트</h2>
	<form action="${ contextPath }/board/insert1.do" method="post" enctype="multipart/form-data">
		게시글 제목 : <input type="text" name="boardTitle"> <br>
		게시글 내용 : <textarea name="boardContent"></textarea> <br>
		첨부파일 : <input type="file" name="uploadFile"> <br>
		<label>첨부파일 사이즈는 10MB 이하여야 됩니다.</label> <br><br>
		
		<button type="submit">등록</button>
	</form>
	
	
	<h2>2. 다중 첨부파일 업로드 테스트</h2>
	<form action="${ contextPath }/board/insert2.do" method="post" enctype="multipart/form-data">
	
		게시글 제목 : <input type="text" name="boardTitle"> <br>
		게시글 내용 : <textarea name="boardContent"></textarea> <br>
		첨부파일 : <input type="file" name="uploadFile" multiple> <br>
		<label>각 첨부파일 사이즈는 10MB 이하, 총 100MB 이하여야 됩니다.</label> <br><br>
		
		<button type="submit">등록</button>
	</form>
	
	<h2>3. 비동기식으로 첨부파일 업로드 테스트</h2>
	
	<div id="async_test">
		게시글 제목 : <input type="text" id="title"> <br>
		게시글 내용 : <textarea id="content"></textarea> <br>
		첨부파일 : <input type="file" id="file"> <br><br>
		
		<button type="button" id="submit">등록</button>
	</div>
	
	<script>
		$(function(){
			
			$("#submit").on("click", function(){
				
				// ajax 게시글 등록 (요청시 전달값 : 제목,내용,첨부파일)
				// 첨부파일 전달해야될 경우 => FormData(가상의 form요소) 객체에 담아서 전달
				let formData = new FormData(); 
				formData.append("boardTitle", document.getElementById("title").value);
				formData.append("boardContent", document.getElementById("content").value);
				formData.append("uploadFile", document.getElementById("file").files[0]); // File객체 담기
				
				$.ajax({
					url: '${contextPath}/board/ajaxInsert.do',
					type: 'post',
					data: formData,
					processData: false, // processData : false 선언시 formData를 string으로 변환하지 않음
					contentType: false, // contentType : false 선언시 multipart/form-data로 전송되게 함 
					success: function(result){
						if(result == "SUCCESS"){
							alert("성공적으로 등록!");
							location.reload();
						}else{
							alert("등록 실패!");
						}
					},
					error: function(){
						
					}
				})
			})
		})
	</script>
	
	
	<h2>4. 첨부파일 목록 조회</h2>
	<button onclick="fn_selectAttachList();">첨부파일 조회</button>
	
	<div id="result"></div>
	
	<script>
		function fn_selectAttachList(){
			$.ajax({
				url: '${contextPath}/board/atlist.do',
				success: function(resData){
					console.log(resData); // [{}, {}]
					
					let a = '';
					for(let i=0; i<resData.length; i++){
						a += '<a>' + resData[i].originalName + '</a><br>';
					}
					
					$('#result').html(a);
					
					
				}
			})
		}
	</script>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>