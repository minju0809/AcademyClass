<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="/include/top.jsp" />

<section>
  <br>
	  <div  align="center"> <h2> 배열로 받아 오기  </h2> </div> 
	  <div align="center"> 
	   <form name=f1 action="${path}/PaymentServlet" onsubmit="return kakaoCk()">
		   <table border=1>
			   <tr align="center">  
			     <td> 주문번호 </td>
			     <td><input type=text name=partner_order_id value=1001 ></td> <!-- 다르면 706 -->
			   </tr>
			   <tr align="center">  
			     <td> 이름 </td>
			     <td><input type=text name=partner_user_id value=user_123 ></td> <!-- 다르면 707 -->
			   </tr>
			   <tr align="center">  
			     <td> 품명 </td>
			     <td><input type=text name=item_name ></td>
			   </tr>
			   <tr align="center">  
			     <td> 수량 </td>
			     <td><input type=text name=quantity ></td>
			   </tr>
			   <tr align="center">  
			     <td> 가격 </td>
			     <td><input type=text name=amount ></td>
			   </tr>
			   <tr align="center">  
			     <td> 총가격 </td>
			     <td><input type=text name=total_amount ></td>
			   </tr>
			   <tr>  
			     <td colspan=2 align="center" >
			      <button type=submit >카카오페이로 결제하기</button> </td>   
			   </tr>
		   </table>      
	   </form>
	  </div> 
	<br> 
</section>

<script>
	function kakaoCk() {
		var quantity = f1.quantity.value;
		var amount = f1.amount.value;
		var total_amount = quantity * amount;
		f1.total_amount.value = total_amount;
		// alert("check total_amount: " + total_amount);
	}
</script>

<jsp:include page="/include/bottom.jsp" />