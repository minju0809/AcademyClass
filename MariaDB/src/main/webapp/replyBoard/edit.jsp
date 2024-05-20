<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="replyBoard.*" %>

<%@ include file="/include/top.jsp" %>
<%
ReplyVO m = (ReplyVO)request.getAttribute("m");
%>

<script>
	var  path='${pageContext.request.contextPath}';
	
	function funcK(str) {
		if(str == "U") {
			alert("U");
			f1.sw.value="U";
			f1.action="<%=path %>/ReplyBoardController";
		} else if(str == "D") {
			alert("D");
			f1.sw.value="D";
			f1.action="<%=path %>/ReplyBoardController";
		} else if(str == "S") {
			alert("S");
			f1.sw.value="S";
			f1.action="<%=path %>/ReplyBoardController";
		} else if(str == "F") {
			alert("F");
			f1.sw.value="F";
			f1.action="<%=path %>/ReplyBoardController";
		} else if(str == "RE") {
			alert("RE");
			f1.sw.value="RE";
			f1.action="<%=path %>/ReplyBoardController";
		}
		
	}

</script>

<section>
	<br>
	<div align=center>
		<h2>상세보기</h2>
		<form name=f1 action="<%=path %>/ReBoardController">
			<input type=hidden name=sw>
      ref: <input type=text  name=ref value="<%=m.getRef() %>" /> 
      step: <input type=text  name=re_step value="<%=m.getRe_step() %>" /> 
      level: <input type=text  name=re_level value="<%=m.getRe_level() %>" /> 
      
			<table border="1" >
				<tr> 
			     <td>번호</td> 
			     <td><input type=text id=idx name=idx value="<%=m.getIdx() %>" /></td>
				</tr>
		    <tr>
			     <td>이름</td> 
			     <td><input type=text value="<%=m.getSname() %>" /></td>
				</tr>
				<tr> 
					<td>제목</td> 
		  		<td>
		         <input type=text value="<%=m.getTitle() %>" />
		  		</td>	
		  	</tr>
		  	<tr>
			     <td>이름</td> 
			     <td><input type=text name=sname /></td>
				</tr>
				<tr> 
					<td>제목</td> 
		  		<td>
		         <input type=text  name=title />
		  		</td>	
		  	</tr>
				<tr>
					<td colspan=4 align="center" >
		      	<input  type=submit value="수정하기" onclick="funcK('U')" /> &emsp;
		      	<input  type=submit value="삭제하기" onclick="funcK('D')" /> &emsp;
		      	<input  type=submit value="답글작성" onclick="funcK('RE')" /> &emsp;
		      	<input  type=submit value="목록보기" onclick="funcK('S')" /> &emsp;
		      	<input  type=submit value="새글쓰기" onclick="funcK('F')" /> &emsp;
		      	<input  type=reset  value="다시작성" /> &emsp;
		      </td>
				</tr>
			</table>
		</form>
	</div>
	<br>
</section>
<%@ include file="/include/bottom.jsp" %>