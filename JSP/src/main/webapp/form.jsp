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
		<h2>저장하기(<a href="<%=path %>/PwdController?sw=H">index</a>)</h2>
		<form action="<%=path %>/PwdController">
			<input type=hidden name=sw value=I>
			<table border=1>
				<tr>
					<td>아이디</td>
					<td><input type=text name=userid ></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type=text name=username ></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type=text name=userpassword ></td>
				</tr>
				<tr>
					<td>나이</td>
					<td><input type=text name=userage ></td>
				</tr>
				<tr>
					<td colspan=2 align=center>
						<input type=submit value="저장">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>