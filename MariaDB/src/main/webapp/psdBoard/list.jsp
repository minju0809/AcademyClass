<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="psdBoard.*" %>

<%@ include file="/include/top.jsp" %>
<%
List<PsdVO> li = (List<PsdVO>)request.getAttribute("li");
%>
<section>
	<br>
	<div align=center>
		<h2>목록보기</h2>
		<table border=1>
			<tr>
				<td>번호</td>
				<td>이름</td>
				<td>나이</td>
				<td>암호나이</td>
				<td>포토</td>
				<td>사진</td>
				<td>삭제</td>
			</tr>
			<%
			if(li == null) {
			%>
				<tr>
					<td colspan=5>레코드가 존재 하지 않습니다.</td>
				</tr>
				<%
				} else {
						
						for(PsdVO m : li) {
				%>
					<tr>
						<td><%=m.getIdx() %></td>
						<td><a href="<%=path %>/PsdController?sw=E&idx=<%=m.getIdx() %>"><%=m.getName() %></a></td>
						<td><%=m.getAge() %></td>
						<% 
						if(m.getAge2().length() > 1) {
						%>
						<td><%=m.getAge2().substring(0, 15) %></td>
						<%
						} else {
						%>
						<td><%=m.getAge2() %></td>
						<% 
						}
						%>
						<td><%=m.getPhoto() %></td>
						<td><img src="<%=path %>/psdBoard/files/<%=m.getPhoto() %>" width=50></td>
						<td><a href="<%=path %>/PsdController?sw=D&idx=<%=m.getIdx() %>">삭제</a></td>
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