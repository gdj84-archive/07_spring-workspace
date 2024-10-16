<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${ contextPath }/resources/js/sample.js"></script>
<script src="${ contextPath }/assets/js/jquery-3.7.1.min.js"></script>
</head>
<body>
	<!-- / 또는 /main.do 라는 url mapping으로 요청시 해당 /WEB-INF/views/main.jsp 가 보여지도록 -->
	<h1>메인페이지 입니다</h1>
	
	<h3>1. 정적인 자원 확인</h3>
	<img src="${ contextPath }/resources/images/flower1.jpg" width="100" onclick="test();">
	<img src="${ contextPath }/assets/images/flower2.jpg" width="100" id="img">
	
	<script>
		$(function(){
			$("#img").on("click", () => {
				alert("어서오세요");
			})
		})
	</script>
	
	<hr>
	
	<h3>2. 응답페이지 보여지게 하는 연습 (포워딩 방식과 redirect 방식)</h3>
	
	<a href="${ contextPath }/book/list.do">도서목록페이지로 이동</a>
	<a href="${ contextPath }/book/enrollForm.do">도서등록페이지로 이동</a>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>