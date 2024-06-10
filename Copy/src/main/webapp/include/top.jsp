<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<c:set var="path" scope="session" 
       value="${pageContext.request.contextPath}" />
 
<%
  String path = request.getContextPath();
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
  	height:90px; 		 /* 영역의 높이 */
  	line-height:90px;	 /* 라인 높이 */
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
session ID 확인:  <%=id %> 
<header> (스마트웹&콘테츠개발) JAVA기반 웹 개발자 양성과정   </header>
<nav> &emsp;&emsp;

<% 
 if ( id != null ) {
	 if (!id.equals("")){
%>
	&emsp;<a href="<%=path %>/PsdController?sw=F"> 자료실저장 </a>
	&emsp;<a href="<%=path %>/PsdController?sw=S"> 자료실목록 </a>
	
	&emsp;<a href="<%=path %>/ReBoardController?sw=F"> 성적저장 </a>
	&emsp;<a href="<%=path %>/ReBoardController?sw=S"> 성적목록 </a>
	
	&emsp;<a href="<%=path %>/ShoppingController?sw=I"> 회원등록 </a>&emsp;
	<a href="<%=path %>/ShoppingController?sw=L"> 회원목록조회/수정 </a>&emsp;
	<a href="<%=path %>/ShoppingController?sw=M"> 회원매출조회 </a>&emsp;
	
	<a href="<%=path %>/MapController?sw=map1"> 지도1 </a>&emsp;
	<a href="<%=path %>/MapController?sw=map2"> 지도2 </a>&emsp;


	&emsp;<a href="<%=path %>/ReplyBoardController?sw=F"> 답변형글쓰기 </a>
	&emsp;<a href="<%=path %>/ReplyBoardController?sw=S"> 답변형목록 </a>
	
	&emsp;<a href="<%=path %>/RepsdController?sw=F"> 답변자료실쓰기 </a>
	&emsp;<a href="<%=path %>/RepsdController?sw=S"> 답변자료실목록 </a>
	
	&emsp;<a href="<%=path %>/LoginController?sw=logout"> 로그아웃 </a>
	
	<a href="<%=path %>/DatasetXMLController?sw=S"> XML(S) </a>&emsp;
    <a href="<%=path %>/DatasetXMLController?sw=SI"> XML(SI) </a>&emsp;
    <a href="<%=path %>/DatasetXMLController?sw=I"> XML(I) </a>&emsp;

	<a href="<%=path %>/DatasetJSONController?sw=S"> JSON(S) </a>&emsp;
	
	
	<a href="<%=path %>/StockController?PAGENO=1&sw=I"> 연기금100 </a>&emsp;
	<a href="<%=path %>/StockController?&sw=S"> 연기금목록 </a>&emsp;
	
	<a href="<%=path %>/StockController?PAGENO=1&sw=JSON"> 연기금(JSON) </a>&emsp;
	<a href="<%=path %>/StockController?PAGENO=1&sw=JSON2">연기금(JSON2) </a>&emsp;
<%
   } else {
	   %>
	   &emsp;<a href="<%=path %>/LoginController?sw=F"> 로그인 </a>&emsp;
	   <%
   }
 }else{
	 %>
	 &emsp;<a href="<%=path %>/LoginController?sw=F"> 로그인 </a>&emsp;
	 <%
 }
%>

&emsp;<a href="<%=path %>/MapController?sw=login">카카오(로그인)</a> 
&emsp;<a href="<%=path %>/MapController?sw=logout">카카오(로그아웃)</a> 

&emsp;<a href="<%=path %>/SummernoteController?sw=F">Summernote(F)</a> 
&emsp;<a href="<%=path %>/SummernoteController?sw=S">Summernote(S)</a> 

&emsp;<a href="<%=path %>/CkeditorController?sw=F">ckeditor(F)</a> 
&emsp;<a href="<%=path %>/CkeditorController?sw=S">ckeditor(S)</a> 

&emsp;<a href="<%=path %>/index.jsp">홈으로</a> 
 
 </nav>