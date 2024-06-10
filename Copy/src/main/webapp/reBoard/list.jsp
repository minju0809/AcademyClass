<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="reBoard.*" %>
<%@ include file="/include/top.jsp" %>

<style>
  table{    width:700px;  }  
  .td1 {
    height: 30px; /* td의 높이를 이미지의 높이에 맞게 조정 */    
  }  
  .td2 {
    height: 30px; /* td의 높이를 이미지의 높이에 맞게 조정 */    
    text-align : center;
  }
  #img1{
   display:block; margin:auto;
   height: 50px;  width:50px;
  }
  
</style>

<%
  List<ExamVo>  li =( List<ExamVo> ) request.getAttribute("li"); 
%> 

<section>
<br>
  <div> </div> 
  <div align="center"> 

<table  border=1 >
<tr align="center"> <td>학번 </td>  <td>이름  </td> <td> 국어  </td> <td> 영어  </td> 
     <td> 수학  </td> <td> 역사  </td>  </td></tr>

<% 
if (li == null) {
	%>
	<tr><td colspan=5 class=td2> 레코드가 존재 하지 않습니다. </td> </tr>
<% 	
} else {
	for(ExamVo  m :li) { %>
	<tr  align="center"> 
	<td> <%=m.getSno() %> </td>  
	<td> 
	    <a href=ReBoardController?sw=E&sno=<%=m.getSno() %>>
	      <%=m.getSname() %>	
	    </a>
	</td> 
	<td> <%=m.getKor() %>  </td> 
	<td> <%=m.getEng() %>  </td> 
	<td> <%=m.getMath() %>  </td> 
	<td> <%=m.getHist() %>  </td> 	
	</tr>
	<% } 
}
%>

</table>

  </div> 
<br> 
</section>
<%@ include file="/include/bottom.jsp" %>