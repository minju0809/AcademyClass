<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="pkg.*" %>
<% 
String path = request.getContextPath(); 

List<UsersVO> li = (List<UsersVO>)request.getAttribute("li");
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
		<h2>목록보기(<a href="<%=path %>/PwdController?sw=H">index</a>)</h2>
		<table border=1>
			<tr>
				<td>아이디</td>
				<td>이름</td>
				<td>비밀번호</td>
				<td>나이</td>
			</tr>
			<%
			for(UsersVO m : li) {
			%>
				<tr>
					<td><%=m.getUserid() %></td>
					<td><%=m.getUsername() %></td>
					<td><%=m.getUserpassword() %></td>
					<td><%=m.getUserage() %></td>
				</tr>
			<%
			}
			%>
		</table>
	</div>
</body>
</html>