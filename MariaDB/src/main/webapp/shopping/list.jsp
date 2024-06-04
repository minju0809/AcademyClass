<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="shopping.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<c:import url="/include/top.jsp" /> 

<%
List<HashMap<String, Object>> li = (List<HashMap<String, Object>>)request.getAttribute("li");
%>
<section>
	<div align=center>
	<br>
		<h2>홈쇼핑 목록 조회/수정</h2>
		<table border=1>
			<tr>
				<td>no</td>
				<td>지도로</td>
				<td>이름</td>
				<td>번호</td>
				<td>주소</td>
				<td>날짜</td>
				<td>등급</td>
				<td>도시</td>
				<td>위도</td>
				<td>경도</td>
			</tr>
			<c:if test="${li==null}">
				<tr>
					<td colspan=5>레코드가 존재 하지 않습니다.</td>
				</tr>
			</c:if>
			<c:if test="${li!=null}">
				<c:forEach  var="m" items="${li}">
					<tr>
						<td><a href=ShoppingController?sw=E&custno=${m.custno}>${m.custno}</a></td>
						<td><a href=MapController?sw=map3&custno=${m.custno} %>>지도3</a></td>
						<td>${m.custname}</td>
						<td>${m.phone}</td> 
						<td>${m.address}</td> 
						<td>${m.joindate}</td> 
						<td>${m.grade}</td> 	
						<td>${m.city}</td>
						<td>${m.latitude}</td>
						<td>${m.longitude}</td>
					</tr>
				</c:forEach>
			</c:if>				
		</table>
	</div>
	<br>
</section>
<%@ include file="/include/bottom.jsp" %>