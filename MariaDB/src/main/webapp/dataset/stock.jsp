<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dataset.*" %>

<%@ include file="/include/top.jsp" %>
<%
List<StockVO> li = (List<StockVO>)request.getAttribute("li");
%>
<section>
	<br>
	<div align=center>
		<h2>목록보기</h2>
		<table border=1>
			<tr>
				<td>번호</td>
				<td>평가액(억 원)</td>
				<td>자산군 내 비중(퍼센트)</td>
				<td>종목명</td>
				<td>지분율(퍼센트)</td>
			</tr>
			<%
			if(li == null) {
			%>
				<tr>
					<td colspan=5>레코드가 존재 하지 않습니다.</td>
				</tr>
				<%
				} else {
						
						for(StockVO m : li) {
				%>
					<tr>
						<td><%=m.getIdx() %></td>
						<td><%=m.getPrice() %></td>
						<td><%=m.getImportance() %></td>
						<td><%=m.getName() %></td>
						<td><%=m.getShare() %></td>
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