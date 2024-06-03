<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="shopping.*" %>

<%@taglib  uri="http://java.sun.com/jstl/core"  prefix="c" %>  
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
			<%
			if(li == null) {
			%>
				<tr>
					<td colspan=5>레코드가 존재 하지 않습니다.</td>
				</tr>
				<%
				} else {
						
						for(HashMap<String, Object> m : li) {
				%>
					<tr>
						<td><a href=ShoppingController?sw=E&custno=<%=m.get("custno") %>><%=m.get("custno") %></a></td>
						<td><a href=MapController?sw=map3&custno=<%=m.get("custno") %>>지도3</a></td>
						<td><%=m.get("custname") %></td>
						<td><%=m.get("phone") %></td>
						<td><%=m.get("address") %></td>
						<td><%=m.get("joindate") %></td>
						<td><%=m.get("grade") %></td>
						<td><%=m.get("city") %></td>
						<td><%=m.get("latitude") %></td>
						<td><%=m.get("longitude") %></td>
					</tr>
				<%
				}
			}
			%>
		</table>
	</div>
	<br>
</section>
<%@ include file="/include/bottom.jsp" %>