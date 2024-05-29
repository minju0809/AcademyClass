<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="shopping.*" %>

<%@ include file="/include/top.jsp" %>
<%
List<MemberVO> li = (List<MemberVO>)request.getAttribute("li");
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
						
						for(MemberVO m : li) {
				%>
					<tr>
						<td><a href=ShoppingController?sw=E&custno=<%=m.getCustno() %>><%=m.getCustno() %></a></td>
						<td><a href=MapController?sw=map3&custno=<%=m.getCustno() %>>지도3</a></td>
						<td><%=m.getCustname() %></td>
						<td><%=m.getPhone() %></td>
						<td><%=m.getAddress() %></td>
						<td><%=m.getJoindate() %></td>
						<td><%=m.getGrade() %></td>
						<td><%=m.getCity() %></td>
						<td><%=m.getLatitude() %></td>
						<td><%=m.getLongitude() %></td>
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