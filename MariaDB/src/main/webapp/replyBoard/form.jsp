<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/top.jsp" %>
<%
int ref = (int) request.getAttribute("ref");
%>

<section>
	<br>
	<div align=center>
		<h2>답변형 게시판 새글 작성</h2>
		<form name=f1 action="<%=path %>/ReplyBoardController">
			<input type=hidden name=sw value=I>
			<table border=1>
				<tr>
					<td>이름</td>
					<td><input type=text id=sname name=sname ></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type=text id=title name=title ></td>
				</tr>
				<tr>
					<td>ref</td>
					<td><input type=text id=sname name=ref value=<%=ref %> ></td>
				</tr>
				<tr>
					<td colspan=2 align=center>
						<input type=submit value="저장">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<br>
</section>

<%@ include file="/include/bottom.jsp" %>