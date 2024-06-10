<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/top.jsp" %>
<section>
<br>
  <div> </div> 
    <div align="center"> 
	<h2> 자료실 폼 만들기  </h2>
	<form name="f1"   action="<%=path %>/MariaDBController" 
	      method="post" enctype="multipart/form-data" >
	<input  type="hidden"  name="sw"  value="I"  />
	<table border="1" >
	<tr> <td class="td1">이&emsp;름  </td><td>&emsp;<input  type=text  name=name />  </td></tr>
	<tr> <td class="td1">나&emsp;이  </td><td>&emsp;
          <input  type=number min=11  max=130 name=age size=5 />   </td></tr>
	<tr> <td class="td1">사&emsp;진  </td><td>&emsp;
          <input  type=file  name=photo  id="photo"    />   </td></tr>

	<tr> <td class="td1">특이사항  </td>
	     <td>&emsp;<textarea  cols=40  rows=4 name=etc></textarea>   </td></tr>

	<tr> <td colspan=2 align="center" >
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