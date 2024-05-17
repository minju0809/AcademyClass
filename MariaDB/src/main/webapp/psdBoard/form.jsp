<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/top.jsp" %>

<section>
	<br>
	<div align=center>
		<h2>저장하기</h2>
		<form action="<%=path %>/PsdController"
		method="post" enctype="multipart/form-data">
			<input type=hidden name=sw value=I>
			<table border=1>
				<tr>
					<td>이름</td>
					<td><input type=text name=name ></td>
				</tr>
				<tr>
					<td>나이</td>
					<td><input type=text name=age ></td>
				</tr>
				<tr>
					<td>사진</td>
					<td><input type=file name=photo id=photo ></td>
				</tr>
				<tr>
					<td>특이사항</td>
					<td><textarea  cols=40  rows=4 name=etc></textarea></td>
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