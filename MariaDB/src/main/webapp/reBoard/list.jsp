<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="reBoard.*" %>

<%@ include file="/include/top.jsp" %>
<%
List<ExamVO> li = (List<ExamVO>)request.getAttribute("li");
%>
<section>
	<div align=center>
	<br>
		<h2>목록보기</h2>
		<table border=1>
			<tr>
				<td>번호</td>
				<td>이름</td>
				<td>국어</td>
				<td>영어</td>
				<td>수학</td>
				<td>역사</td>
			</tr>
			<%
			if(li == null) {
			%>
				<tr>
					<td colspan=5>레코드가 존재 하지 않습니다.</td>
				</tr>
				<%
				} else {
						
						for(ExamVO m : li) {
				%>
					<tr>
						<td><%=m.getSno() %></td>
						<td><a href="<%=path %>/ReBoardController?sw=E&sno=<%=m.getSno() %>"><%=m.getSname() %></a></td>
						<td><%=m.getKor() %></td>
						<td><%=m.getEng() %></td>
						<td><%=m.getMath() %></td>
						<td><%=m.getHist() %></td>
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