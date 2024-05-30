<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dataset.*" %>

<%@ include file="/include/top.jsp" %>
<%
List<DatasetVO> li = (List<DatasetVO>)request.getAttribute("li");
%>
<section>
	<br>
	<div align=center>
		<h2>목록보기</h2>
		<table border=1>
			<tr>
				<td>번호</td>
				<td>센터이름</td>
				<td>이름</td>
				<td>주소</td>
				<td>위도</td>
				<td>경도</td>
				<td>홈페이지</td>
			</tr>
			<%
			if(li == null) {
			%>
				<tr>
					<td colspan=5>레코드가 존재 하지 않습니다.</td>
				</tr>
				<%
				} else {
						
						for(DatasetVO m : li) {
				%>
					<tr>
						<td><%=m.getIdx() %></td>
						<td><%=m.getCnterNm() %></td>
						<td><%=m.getCnterChNm() %></td>
						<td><%=m.getRoadNmAddr() %></td>
						<td><%=m.getLat() %></td>
						<td><%=m.getLot() %></td>
						<td><%=m.getHmpgAddr() %></td>
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