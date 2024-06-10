<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/top.jsp" %>
<%@ page import="psdBoard.*" %>    
<%
  PsdVo  m =(PsdVo) request.getAttribute("m"); 
%>     
<section>
<br>
  <div> </div>
    <div align="center">  
	<h2> 상세보기  </h2>
	<form name="f1"   action="<%=path %>/PsdController" 
	      method="post" enctype="multipart/form-data" >
	<input  type="hidden"  name="sw"  value="U"  />
	<input  type="hidden"  name="idx"  value="<%=m.getIdx() %>"  />
	<table border="1" >
	<tr> <td class="td1">이&emsp;름  </td> 
	     <td>&emsp;<input  type=text  name=name value="<%=m.getName() %>" />  </td>
	     <td rowspan=3> <img src="<%=path %>/files/<%=m.getPhoto() %>" width=50  height=70   /> </td> </tr>
	<tr> <td class="td1">나&emsp;이  </td><td>&emsp;
          <input  type=number min=11  max=130 name=age size=5  value="<%=m.getAge() %>" />   </td></tr>
	<tr> <td class="td1">사&emsp;진  </td><td>&emsp;
          <input  type=file  name=photo  id="photo"    />   </td></tr>

	<tr> <td class="td1">특이사항  </td>
	     <td colspan=2>&emsp;<textarea  cols=40  rows=4 name=etc><%=m.getEtc() %></textarea>   </td></tr>

	<tr> <td colspan=3 align="center" >
	       <input  type=submit  value="저장하기" /> &emsp;
	       <input  type=reset  value="다시작성" /> 
	      </td>
	</tr>

</table>
</form>
</div> 
<br> 
</section>
<%@ include file="/include/bottom.jsp" %>