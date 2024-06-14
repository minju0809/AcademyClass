<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/include/top.jsp"  />
<link href="${path}/member/member.css"  rel="stylesheet" type="text/css" />

<script>
  function orderOK(){
	alert("주문확인") ; 
	amount = f1.amount.value;	 
	location.href="${path}/CartController?sw=I&pid=${m.pid}&amount="+amount
  }
</script>

<section>
<br>
  <div> </div> 
    <div align="center"> 
	<h2> 상품 상세 보기  </h2>
	<form name="f1"   action="${path}/ProductController" 
	      method="post" enctype="multipart/form-data" >
	<input  type="hidden"  name="sw"  value="U"  />
	<table border="1" width=600 >
	<tr align=> <td class="td1" width=20%  align="center"> 상품번호  </td>
	     <td colspan=2>&emsp;<input  type=text  name=pid  value="${m.pid}" />  </td></tr>
	<tr> <td class="td1"  align="center"> 상품이름  </td>
	     <td colspan=2>&emsp;<input  type=text  name=pname size=50   value="${m.pname}"  />  </td></tr>
	<tr> <td class="td1"  align="center"> 상품가격  </td>
	     <td>&emsp;<input  type=text  name=pprice   value="${m.pprice}"  />
	     </td>
	     <td rowspan=3 >&emsp; <img src=${path}/product/files/${m.pimg} width=100 height=150 >
	     </td>
	</tr>
	<tr> <td class="td1"  align="center"> 배송비  </td>
	     <td>&emsp;<input  type=text  name=pbaesongbi   value="${m.pbaesongbi}"  />  </td></tr>     
	
	<tr> <td class="td1"  align="center"> 상품수량  </td>
	     <td>&emsp;<input  type=text  name=amount  />    </td></tr>
	
	     
	<tr> <td class="td1"  align="center"> 상품사진  </td>
	<td colspan=2> &emsp;<input  type=file  name=pimg  id="pimg"    />   </td></tr>

	<tr> <td class="td1"  align="center"> 상품상세  </td>
	     <td colspan=2>&emsp;<textarea  cols=50  rows=4 name=pdesc>${m.pdesc} </textarea>   </td></tr>

	<tr> <td colspan=3 align="center" >
	       <input  type=submit  value="상품등록" /> &emsp;
	       <input  type=button onClick="orderOK()" value="주문하기" /> 
	      </td>
	</tr>

</table>
</form>
</div> 
<br> 
</section>
<jsp:include page="/include/bottom.jsp"  />
