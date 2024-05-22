<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/top.jsp" %>

<section>
	<br>
	<div align=center>
		<h2>파일 추가 답변형 게시판 새글 작성</h2>
		<form action="<%=path %>/RepsdController"
		method="post" enctype="multipart/form-data">
			<input type=hidden name=sw value="I">
			<table border=1>
				<tr>
					<td>이름</td>
					<td><input type=text name=sname ></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type=text name=title ></td>
				</tr>
				<tr>
					<td>사진</td>
					<td><input type=file name=img id=img ></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea  cols=40  rows=4 name=etc ></textarea></td>
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