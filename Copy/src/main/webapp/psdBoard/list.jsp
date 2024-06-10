<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/top.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="psdBoard.*" %>

<style>
  table{    width:800px;  }  
  .td1 {
    height: 40px; /* td의 높이를 이미지의 높이에 맞게 조정 */    
  }
  
  .td2 {
    height: 40px; /* td의 높이를 이미지의 높이에 맞게 조정 */    
    text-align : center;
  }
  #img1{
   display:block; margin:auto;
   height: 50px;  width:50px;
  }
  
</style>

<%
  List<PsdVo>  li =( List<PsdVo> ) request.getAttribute("li"); 
%> 

<section>
<br>
  <div> </div> 
  <div align="center"> 

<table  border=1 >
<tr> <td>순번 </td>  <td>이름  </td> <td> 나이  </td> <td> 암호화  </td> 
     <td> 파일명  </td> <td> 이미지  </td>  </td></tr>

<% 
if (li == null) {
	%>
	<tr><td colspan=5 class=td2> 레코드가 존재 하지 않습니다. </td> </tr>
<% 	
} else {
	for(PsdVo  m :li) { %>
	<tr> 
	<td> <%=m.getIdx() %> </td>  
	<td>
	 <a href="PsdController?sw=E&idx=<%=m.getIdx() %>">
	   <%=m.getName() %>  
	 </a>  
	</td> 
	<td> <%=m.getAge() %>  </td> 
	<td>
	 <% if (m.getAge2().length() > 2) {  %>
	 <%=m.getAge2().substring(0, 15) %>
	 <% } else { %>
	 <%=m.getAge2()%>
	 <%} %>
	 </td>
	
	<td> <%=m.getPhoto() %>  </td> 
	<td  class=td1 > 
	 <a href="PsdController?sw=D&idx=<%=m.getIdx() %>">
	 <img src="<%=path %>/files/<%=m.getPhoto() %>"  width=50  height=50 >
	 </a>
	</td> 
	</tr>
	<% } 
}
%>

</table>

  </div> 
<br> 
</section>
<%@ include file="/include/bottom.jsp" %>