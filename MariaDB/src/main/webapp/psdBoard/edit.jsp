<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="psdBoard.*" %>

<%@ include file="/include/top.jsp" %>
<%
PsdVO m = (PsdVO)request.getAttribute("m");
%>
<section>
	<br>
	<div align=center>
		<h2>상세보기</h2>
		<form action="<%=path %>/PsdController"
		method="post" enctype="multipart/form-data">
			<input type=hidden name=sw value=U>
			<table border=1>
				<tr>
					<td align=center colspan=2><img src="<%=path %>/psdBoard/files/<%=m.getPhoto() %>" width=200></td>
				</tr>
				<tr>
					<td>번호</td>
					<td><input type=text name=idx value=<%=m.getIdx() %>></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type=text name=name value=<%=m.getName() %>></td>
				</tr>
				<tr>
					<td>나이</td>
					<td><input type=text name=age value=<%=m.getAge() %>></td>
				</tr>
				<tr>
					<td>암호화나이</td>
					<td><input type=text name=age value=<%=m.getAge2() %>></td>
				</tr>
				<tr>
					<td>포토</td>
					<td><input type=file name=photo></td>
				</tr>
				<tr>
					<td>기타</td>
					<td><input type=text name=etc value=<%=m.getEtc() %>></td>
				</tr>
				<tr>
					<td align=center colspan=2>
						<input type=submit value=수정하기>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<br>
</section>
<%@ include file="/include/bottom.jsp" %>