<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="repsd.*" %>

<%@ include file="/include/top.jsp" %>
<%
List<RepsdVO> li = (List<RepsdVO>)request.getAttribute("li");
int tc = (int)request.getAttribute("tc");
int start = (int)request.getAttribute("start");
int pageSize = (int)request.getAttribute("pageSize");

String ch1 = (String)request.getAttribute("ch1");
String ch2 = (String)request.getAttribute("ch2");
System.out.println("ch1: " + ch1 + "ch2: " + ch2);
%>
<style>
	.img {
    display: block;
    margin: auto;
    width: 40px;
    height: 40px;
  }
</style>
<section>
	<br>
	<div align=center>
		<h2>목록보기</h2>
		tc: <%=tc %>
		<table border=1>
			<tr>
				<td>번호</td>
				<td>이미지</td>
				<td>이름</td>
				<td>제목</td>
				<td>이미지이름</td>
				<!-- <td>기타</td> -->
				<td>ref</td>
				<td>step</td>
				<td>level</td>
				<td>cnt</td>
				<td>삭제</td>
			</tr>
			<%
			if(li == null) {
			%>
				<tr>
					<td colspan=5>레코드가 존재 하지 않습니다.</td>
				</tr>
				<%
				} else {
						for(RepsdVO m : li) {
				%>
					<tr>
						<td><%=m.getIdx() %></td>
						<td><img class=img src="<%=path %>/repsd/files/<%=m.getImg() %>" width=50></td>
						<td><a href="<%=path %>/RepsdController?sw=E&idx=<%=m.getIdx() %>"><%=m.getSname() %></a></td>
						<td width=100>
							<%
							if(m.getRe_level() == 2) {
							%>
							<img src="<%=path %>/replyBoard/img/re.png" width=16 style="margin-top: 8px;">						
							<%
							} else if(m.getRe_level() == 3) {
							%>
							<img src="<%=path %>/replyBoard/img/sp.png" height=4 width=12>
							<img src="<%=path %>/replyBoard/img/re.png" width=16 style="margin-top: 8px;">
							<%
							}
							else if(m.getRe_level() > 3) {
							%>
							<img src="<%=path %>/replyBoard/img/sp.png" height=4 width=16>
							<img src="<%=path %>/replyBoard/img/sp.png" height=4 width=16>
							<img src="<%=path %>/replyBoard/img/re.png" width=16 style="margin-top: 8px;">
							<%
							}
							%>
						<%=m.getTitle() %>
					</td>
						<td><%=m.getImg() %></td>
						<%-- <td><%=m.getEtc() %></td> --%>
						<td><%=m.getRef() %></td>
						<td><%=m.getRe_step() %></td>
						<td><%=m.getRe_level() %></td>
						<td><%=m.getCnt() %></td>
						<td><a href="<%=path %>/RepsdController?sw=D&idx=<%=m.getIdx() %>">삭제</a></td>
					</tr>
				<%
				}
			}
			%>
		</table>
		<form action=RepsdController>
			<input type=hidden name=sw value="S">
			<select name=ch1>
				<option value="sname">이름</option>
			</select>
			<input type=text name=ch2 >
			<input type=submit value=검색 >
		</form>
		<a href="<%=path %>/RepsdController?sw=S&start=0&ch1=<%=ch1 %>&ch2=<%=ch2 %>">처음으로</a>
		<%
		if(start <= 0) { 
		%>
			<a>이전</a>		
		<%
		} else {
		%>
			<a href="<%=path %>/RepsdController?sw=S&start=<%=start - pageSize%>&ch1=<%=ch1 %>&ch2=<%=ch2 %>">이전</a>
		<%
		}
		%>
			<a href="<%=path %>/RepsdController?sw=S&start=<%=start + pageSize%>&ch1=<%=ch1 %>&ch2=<%=ch2 %>">다음</a>
	</div>
	<br>
</section>
<%@ include file="/include/bottom.jsp" %>