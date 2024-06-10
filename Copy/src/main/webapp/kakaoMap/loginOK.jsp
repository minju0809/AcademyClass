<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/top.jsp" %>
<section>
<br>
<div  align="center">
<h1> 카카오 로그인 결과 보기 </h1> <br><br>

닉네임 : <%=session.getAttribute("nickname") %> <br>
email : <%=session.getAttribute("email") %> <br>
profile_image : <%=session.getAttribute("profile_image") %> <br>
토큰 :   <%=session.getAttribute("access_Token") %> <br>
</div> 
<br>
</section>
<%@ include file="/include/bottom.jsp" %>