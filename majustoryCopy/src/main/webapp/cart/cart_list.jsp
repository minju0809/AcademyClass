<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="/include/top.jsp"  />
<style>
	table {
	    width:60%;
	    margin: 20px auto;
	    border-collapse: collapse;
	    background-color: white;
	}
	table, th, td {
	    border: 1px solid black;
	}
	th, td {
	    padding: 10px;
	    text-align: left;
	}
	thead {
	    background-color: #FFFFFF; /* Change header background color here */
	    color: 000000;
	}
	th {
	    background-color: #CC3350;
	    color: white;
	}
	tr:nth-child(odd) {
	    background-color: #f2f2f2;
	}
	tr:nth-child(even) {
	    background-color: #ffffff;
	}
	thead th {
	    text-align: center; /* Center align text in thead */
	}
	.dtCenter{
	 text-align: center;
	}
	.dtRight{
	 text-align: right;
	}
</style>
    
<script>
function totalpay(){	
 
	var pprices = document.getElementsByName('pprice');
	var amounts = document.getElementsByName('amount');
	
	var sumpay = 0;  
	for (var i = 0 ; i < pprices.length  ; i++  ){			
		pay = pprices[i].value * amounts[i].value;		
		sumpay = sumpay + pay;			
	}		
	f1.totalPay.value = sumpay

}
 
function allDelete(){
	location.href="${path}/CartController?sw=D"	 
}
 
function kakapPay(){
	var pprices = document.getElementsByName('pprice');
	var amounts = document.getElementsByName('amount');
	
	var sumpay = 0;  
	for (var i = 0 ; i < pprices.length  ; i++  ){			
		pay = pprices[i].value * amounts[i].value;		
		sumpay = sumpay + pay;			
	}		
	f1.totalPay.value = sumpay
  location.href="${path}/PaymentServlet?&partner_user_id=${mid}&total_amount="+sumpay
}
</script>    
    
<section>
	<form  name= "f1" action="${path}/CartController">
		<input  type=hidden name=sw value="U" />
		<br>
		<div align="center">
		  <table  border=1  >
			  <thead>
				  <tr align="center">
				  <th width=10%> 장바구니PK </th><th width=20% >상품번호 </th>  <th  >상품이름</th>
				  <th> 상품가격  </th>  <th width=10%> 수량  </th> <th> 사진  </th>
				 </tr>
			  </thead>
				<c:if test="${li.size() == 0}">
					<tr><td colspan=6 align="center"> 상품이 존재 하지 않습니다. </td> </tr>
				</c:if>
				<c:if test="${li.size() != 0}">
				<c:forEach var="m"  items="${li}" >
					<c:set var="idx" value="${idx=idx+1}" />
					<tr> 
						<td> <input  type=hidden name=cart_id value="${m.cart_id}" /> ${m.cart_id}  </td> 
						<td>  ${m.pid}   	</td> 
						<td>  ${m.pname}   	</td> 
						<td> <input  type=hidden name=pprice value="${m.pprice}" /> ${m.pprice}  </td> 
						<td> <input type=number name=amount value="${m.amount}" >  </td>
						<td align="center">  <img src=${path}/product/files/${m.pimg} width=50 height=50 >  </td> 
					</tr>
				</c:forEach>
					<tr> 
						<td colspan=6  class="dtRight"> 총구매금액 : <input  type=text name=totalPay>   </td>
					</tr> 
					<tr> 
						<td colspan=6 class="dtCenter"> 
					  	<input  type=button value="구매금액 확인" onClick="totalpay()" > &emsp;&emsp;&emsp;
							<input  type=submit value="전체수정" > &emsp;&emsp;&emsp;
							<input  type=button value="전체삭제"  onClick="allDelete()"  > &emsp;&emsp;&emsp;
							<input  type=button value=주문하기 onClick="kakapPay()" >   
						</td>
					</tr> 
				</c:if>
			</table>
		</div>
		<br> 
	</form>
</section>

<jsp:include page="/include/bottom.jsp"  />