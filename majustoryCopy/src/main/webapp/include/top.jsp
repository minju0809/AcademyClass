<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<c:set var="path" scope="session" 
       value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마주스토리</title>
<link  href="${path}/include/css/top.css"  rel="stylesheet" type="text/css" />
<style type="text/css">  </style>

</head>
<body>
<header> 마주스토리 쇼핑몰 관리자 공간  ( login : ${login} ) </header>
<nav class = noto-sans-kr-uniquifier> &emsp;&emsp;
      &emsp;<a href="${path}/MemberController?sw=F">회원가입</a> 
      &emsp;<a href="${path}/MemberController?sw=S">회원목록</a> 
      
      &emsp;<a href="${path}/ProductController?sw=F">상품등록</a> 
                                      
      <c:if test="${not empty login  }"> 
        <c:if test="${ login != 'F' }"> 
          &emsp;<a href="${path}/ProductController?sw=S">상품목록</a> 
          &emsp;<a href="${path}/CartController?sw=S">장바구니</a> 
        </c:if>
      </c:if>

      <c:if test="${not empty login  }"> 
        <c:if test="${ login != 'F' }"> 
           &emsp;<a href="${path}/MemberController?sw=logout">로그아웃</a>   
         </c:if>
      </c:if>
      
      <c:if test="${ empty login ||  login == 'F' }"> 
         &emsp;<a href="${path}/MemberController?sw=login">회원로그인</a> 
      </c:if>
       
			&emsp;&emsp;<a href="${path}/index.jsp">홈으로</a> 

      &emsp;<a href="${path}/TestController?sw=F">배열Test</a>       
      &emsp;<a href="${path}/TestController?sw=kakao">카카오결제</a> 
 </nav>