<%@ page language="java" pageEncoding="UTF-8"%>
<%
  String  path = request.getContextPath();
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
<header> (스마트웹&콘테츠개발) JAVA기반 웹 개발자 양성과정   </header>
<nav> &emsp;
	<a href="<%=path %>/PsdController?sw=F">저장하기</a>
	<a href="<%=path %>/PsdController?sw=S">목록보기</a>
	
	<a href="<%=path %>/ReBoardController?sw=S">자료실목록</a>
	<a href="<%=path %>/index.jsp">홈으로</a>
 </nav>
