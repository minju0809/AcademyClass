<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/include/top.jsp"  />
<link href="${path}/member/member.css"  rel="stylesheet" type="text/css" />

<section>
<br>
  <div> </div> 
    <div align="center"> 
	<h2> 상품 등록하기  </h2>
	<form name="f1"   action="${path}/ProductController" 
	      method="post" enctype="multipart/form-data" >
	<input  type="hidden"  name="sw"  value="I"  />
	<table border="1" width=600 >
	<tr align=> <td class="td1" width=20%  align="center"> 상품번호  </td>
	     <td>&emsp;<input  type=text  name=pid />  </td></tr>
	<tr> <td class="td1"  align="center"> 상품이름  </td>
	     <td>&emsp;<input  type=text  name=pname size=50 />  </td></tr>
	<tr> <td class="td1"  align="center"> 상품가격  </td>
	     <td>&emsp;<input  type=text  name=pprice />  </td></tr>
	<tr> <td class="td1"  align="center"> 배송비  </td>
	     <td>&emsp;<input  type=text  name=pbaesongbi />  </td></tr>     
	     
	<tr> <td class="td1"  align="center"> 상품사진  </td>
	<td> &emsp;<input  type=file  name=pimg  id="pimg"    />   </td></tr>

	<tr> <td class="td1"  align="center"> 상품상세  </td>
	     <td>&emsp;<textarea  cols=50  rows=4 name=pdesc></textarea>   </td></tr>

	<tr> <td colspan=2 align="center" >
	       <input  type=submit  value="상품등록" /> &emsp;
	       <input  type=reset  value="다시작성" /> 
	      </td>
	</tr>

</table>
</form>
</div> 
<br> 
</section>
<jsp:include page="/include/bottom.jsp"  />
