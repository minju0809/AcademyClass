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
		location.href="<%=path %>/RepsdIUController?sw=R&idx="+idx;
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
		<h3>답글 작성하기</h3>
		<form name=f1 action="<%=path %>/RepsdIUController"
		method="post" enctype="multipart/form-data">
			<input type=hidden name=sw value=RE>
			<input type=hidden name=idx value=<%=m.getIdx() %>>
			ref: <input type=text name=ref value=<%=m.getRef() %>>
			step: <input type=text name=re_step value=<%=m.getRe_step() %>>
			level: <input type=text name=re_level value=<%=m.getRe_level() %>>
			<table border=1>
				<tr>
        	<td> 이름 </td>
         	<td> <input type=text name=sname id="sname"> </td>
         	<td rowspan=2> <img src="<%=path%>/repsd/files/<%=m.getImg() %>" class=img> </td>
       	</tr>
       	<tr>
        	<td> 제목 </td>
         	<td> <input type=text name=title id="title" value=<%=m.getTitle()%> > </td>
       	</tr>
       	<tr>
         	<td> 이미지 </td>
         	<td colspan=2> <input type=file name=img > </td>
       	</tr>
       	<tr>
         	<td> 내용 </td>
         	<td colspan=2>[원본내용]<br>
           	<%=m.getEtc() %><br>
         		<textarea cols=40 rows=4 name=etc></textarea>
       		</td>
       	</tr>
       	<tr>
        	<td colspan=3 align="center">
          	<input type=submit value="답글작성">
          	<input type=button onClick="funcS()" value="목록보기">
         		<input type=button onClick="funcF()" value="새글쓰기">
       		</td>
     		</tr>
			</table>
		</form>
	</div>
	<br>
</section>
<%@ include file="/include/bottom.jsp" %>