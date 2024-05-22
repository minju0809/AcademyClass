<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="repsd.*" %>

<%@ include file="/include/top.jsp" %>
<%
RepsdVO m = (RepsdVO)request.getAttribute("m");
%>
<style>
	.img {
    display: block;
    margin: auto;
    width: 60px;
    height: 60px;
  }
</style>

<script>
	var  path='${pageContext.request.contextPath}';
	
	function funcR(idx) {
		alert("R");
		location.href="<%=path %>/RepsdController?sw=R&idx="+idx;
	}
	
	function funcD(idx) {
		alert("D");
		location.href="<%=path %>/RepsdController?sw=D&idx="+idx;
	}
	
	function funcS() {
		alert("S");
		f1.action="<%=path %>/RepsdController?sw=S";
		f1.submit();
	}
	
	function funcF() {
		alert("F");
		f1.action="<%=path %>/RepsdController?sw=F";
		f1.submit();
	}
	
</script>

<section>
	<br>
	<div align=center>
		<h3>상세보기</h3>
		<form name=f1 action="<%=path %>/RepsdController"
		method="post" enctype="multipart/form-data">
			<input type=hidden name=sw value=U>
			<input type=hidden name=idx value=<%=m.getIdx() %>>
			ref: <input type=text name=ref value=<%=m.getRef() %>>
			step: <input type=text name=re_step value=<%=m.getRe_step() %>>
			level: <input type=text name=re_level value=<%=m.getRe_level() %>>
			<table border=1>
				<tr>
					<td>cnt</td>
					<td><input type=text name=cnt value=<%=m.getCnt() %>></td>
					<td rowspan=3><img class=img src="<%=path %>/repsd/files/<%=m.getImg() %>" width=200></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type=text name=sname value=<%=m.getSname() %>></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type=text name=title value=<%=m.getTitle() %>></td>
				</tr>
				<tr>
					<td>내용</td>
					<td colspan=2><input type=text name=etc value=<%=m.getEtc() %>></td>
				</tr>
				<tr>
					<td>포토</td>
					<td colspan=2><input type=file name=img></td>
				</tr>
				<tr>
					<td align=center colspan=3>
						<input  type=submit value="수정하기"  /> &emsp;
		      	<input  type=button value="삭제하기" onclick="funcD('<%=m.getIdx() %>')" /> &emsp;
		      	<input  type=button value="답글작성" onclick="funcR('<%=m.getIdx() %>')" /> &emsp;
		      	<input  type=button value="목록보기" onclick="funcS()" /> &emsp;
		      	<input  type=button value="새글쓰기" onclick="funcF()" /> &emsp;
		      	<input  type=reset  value="다시작성" /> &emsp;
					</td>
				</tr>
			</table>
		</form>
	</div>
	<br>
</section>
<%@ include file="/include/bottom.jsp" %>