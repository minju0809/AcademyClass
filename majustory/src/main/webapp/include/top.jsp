<%@ page language="java" pageEncoding="UTF-8"%>

<%@ page import="java.util.*" %>
<%-- <%@ page import="member.*" %> --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<c:set var="path" scope="session" 
       value="${pageContext.request.contextPath}" />

<%
  String  path = request.getContextPath();
	String id = (String) session.getAttribute("id");
	System.out.println("id" + id);
%>
   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="${path}/include/css/style.css" rel="stylesheet">
</head>
<body>
<header> (스마트웹&콘테츠개발) JAVA기반 웹 개발자 양성과정 </header>
<nav> &emsp;
	<%
	if(id != null) {
	%>
		<a href="<%=path %>/MemberController?sw=S">맴버목록</a>

    <a href="${path}/ProductController?sw=F">상품등록</a> 
		<a href="<%=path %>/MajustoryController?sw=pay">카카오결제</a>

		<a href="<%=path %>/MemberController?sw=logout">${id}(로그아웃)</a>
	<%
	} else {
	%> 
		<a href="<%=path %>/MemberController?sw=F">회원가입</a>
		<a href="<%=path %>/MemberController?sw=loginF">로그인</a>
	<%
	}
	%>	
  <a href="${path}/ProductController?sw=S">상품목록</a> 
  
	<a href="<%=path %>/index.jsp">홈으로</a>
 </nav>
