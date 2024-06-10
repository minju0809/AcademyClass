<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/top.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="replyBoard.*" %>

<style>
  table{    width:800px;  }  
  
</style>

<%
  List<ReplyBoardVo>  li =( List<ReplyBoardVo> ) request.getAttribute("li"); 
%> 

<section>
<br>
  <div> </div> 
  <div align="center"> 

<table  border=1 >
<tr> <td  width=30>순번 </td>  <td  width=100>이름  </td> <td> 제목  </td> <td width=30> ref  </td> 
     <td width=30> step   </td> <td width=30> level   </td> <td width=30> cnt   </td>  </tr>

<% 
if (li == null) {
	%>
	<tr><td colspan=5 class=td2> 레코드가 존재 하지 않습니다. </td> </tr>
<% 	
} else {
	for(ReplyBoardVo  m :li) { %>
	<tr> 
	<td> <%=m.getIdx() %> </td>  
	<td><a href="<%=path%>/ReplyBoardController?sw=E&idx=<%=m.getIdx() %>"> <%=m.getSname() %></a> </td> 
	<td>
	
	 <%
	   if (m.getRe_level() > 1) {
		  
		    if (m.getRe_level() == 2 ) {
		    %>
		     <img src="<%=path %>/replaBoard/img/space.png" height="10" width="<%=m.getRe_level()%>" >
		    <%	
		    } else {
		   	 %>
			<img src="<%=path %>/replaBoard/img/space.png" height="10" width="<%=m.getRe_level()*10%>" >	    
			<%	
		    }
		    %>
		    
		    <img src="<%=path %>/replayBoard/img/icon_reply.png" >
		   <%
	   } 
	 %>
	 <%=m.getTitle() %>  
	 
	 </td> 
	<td> <%=m.getRef() %>  </td> 
	<td> <%=m.getRe_step() %>  </td> 
	<td> <%=m.getRe_level() %>  </td> 
	<td> <%=m.getCnt() %>  </td> 	
	</tr>
	<% } 
}
%>

</table>

  </div> 
<br> 
</section>
<%@ include file="/include/bottom.jsp" %>