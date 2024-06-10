<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/top.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="dataset.*" %>

<style>
  table{    width:1500px;  }  
  
</style>

<%
  List<DataVo>  li =( List<DataVo> ) request.getAttribute("li"); 
%> 

<section>
<br>
  <div> </div> 
  <div align="center"> 

<table  border=1 >
<tr>
 <td> 순번 </td>  <td  >센터명  </td> 
 <td> 센터장  </td> <td> homepage  </td> 
 <td > lat   </td> <td> lot   </td> 
 <td > roadNmAddr    </td> 
</tr>

<% 
if (li == null) {
	%>
	<tr><td colspan=5 class=td2> 레코드가 존재 하지 않습니다. </td> </tr>
<% 	
} else {
	for(DataVo  m :li) { %>
	<tr> 
	<td> <%=m.getIdx() %> </td>  
	<td> 
	 <a href=DatasetXMLController?sw=E&idx=<%=m.getIdx() %>>
	   <%=m.getCnterNm() %>  
	 </a>
	 </td> 
	<td> <%=m.getCnterChNm() %>  </td> 
	<td> <%=m.getHmpgAddr() %>  </td> 
	<td> <%=m.getLat() %>  </td> 
	<td> <%=m.getLot() %>  </td> 
	<td> <%=m.getRoadNmAddr() %>  </td> 	
	</tr>
	<% } 
}
%>

</table>

  </div> 
<br> 
</section>
<%@ include file="/include/bottom.jsp" %>