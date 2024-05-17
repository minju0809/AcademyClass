<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
String path = request.getContextPath(); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="<%=path %>/PwdController?sw=F">저장하기</a>
	<a href="<%=path %>/PwdController?sw=S">목록보기</a>
	<a href="<%=path %>/PwdController?sw=In">로그인</a>
	<a href="<%=path %>/PwdController?sw=H">홈으로</a>
	<div align=center>
		<h2>홈</h2>
	</div>
</body>
</html>