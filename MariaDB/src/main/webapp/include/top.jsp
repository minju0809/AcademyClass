<%@ page language="java" pageEncoding="UTF-8"%>

<%@ page import="java.util.*" %>
<%
  String  path = request.getContextPath();
	String id = (String) session.getAttribute("id");
%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
  header{
  	background:#0000ff;  /* 배경색깔 */
  	/* height:90px; 		 /* 영역의 높이 */ */
  	/* line-height:90px;	 /* 라인 높이 */ */
  	color:#ffffff; 		 /* 글자색깔 (빨, 녹, 파) */
  	font-size:30px;		 /* 글자크기 */
  	text-align:center;   /* 글자 가운데 정렬 */
  }
  nav{
	background:#0088ff;  /* 배경색깔 */
  	height:40px; 		 /* 영역의 높이 */
  	line-height:40px;	 /* 라인 높이 */
  	color:#ffffff; 		 /* 글자색깔 (빨, 녹, 파) */
  	font-size:16px;		 /* 글자크기 */
  	text-align:left;   /* 글자 가운데 정렬 */
  }
	
  section{
	background:#eaeaea;  /* 배경색깔 */
  	min-height:500px; 		 /* 영역의 높이 */
  	line-height:40px;	 /* 라인 높이 */
  	font-size:14px;		 /* 글자크기 */
  }
	
  footer{
	background:#0000ff;  /* 배경색깔 */
  	height:40px; 		 /* 영역의 높이 */
  	line-height:40px;	 /* 라인 높이 */
  	color:#ffffff; 		 /* 글자색깔 (빨, 녹, 파) */
  	font-size:16px;		 /* 글자크기 */
  	text-align:center;   /* 글자 가운데 정렬 */
  }
</style>

</head>
<body>
<header> (스마트웹&콘테츠개발) JAVA기반 웹 개발자 양성과정 </header>
<nav> &emsp;
	<%
	if(id != null) {
	%>
		<a href="<%=path %>/PsdController?sw=F">자료실저장</a>
		<a href="<%=path %>/PsdController?sw=S">자료실목록</a>
		
		<a href="<%=path %>/ReBoardController?sw=F">성적등록</a>
		<a href="<%=path %>/ReBoardController?sw=S">학생목록</a>
		
		<a href="<%=path %>/ReplyBoardController?sw=F">등록</a>
		<a href="<%=path %>/ReplyBoardController?sw=S">목록</a>
	
		<%-- <a href="<%=path %>/RepsdController?sw=II">자료추가</a> --%>
		<a href="<%=path %>/RepsdController?sw=F">자료등록</a>
		<a href="<%=path %>/RepsdController?sw=S">자료목록</a>
		
		<a href="<%=path %>/ShoppingController?sw=M">매출</a>
		
		<a href="<%=path %>/MapController?sw=map1">지도1</a>
		<a href="<%=path %>/MapController?sw=map2">지도2</a>
		
		<a href="<%=path %>/DatasetXMLController?sw=S">XML(S)</a>
		<a href="<%=path %>/DatasetXMLController?sw=SI">XML(SI)</a>
		<a href="<%=path %>/DatasetXMLController?sw=I">XML(I)</a>
		<a href="<%=path %>/StockController?sw=SI">주식(SI)</a>
		<a href="<%=path %>/StockController?sw=S">주식(S)</a>
		<a href="<%=path %>/StockController?sw=I">주식(I)</a>
		<a href="<%=path %>/DatasetJSONController?sw=SI">JSON(SI)</a>
		
		<a href="<%=path %>/LoginController?sw=logout"><%=id %>(로그아웃)</a>
	<%
	} else {
	%> 
		<a href="<%=path %>/LoginController?sw=F">로그인</a>
	<%
	}
	%>
	<a href="<%=path %>/ShoppingController?sw=F">회원등록</a>
	<a href="<%=path %>/ShoppingController?sw=L">회원목록</a>
	
	<a href="<%=path %>/index.jsp">홈으로</a>
 </nav>
