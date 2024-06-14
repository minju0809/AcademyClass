<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/include/top.jsp"  />

<section>
<br>
  <div> </div> 
  <div align="center">  <br> <br>
     <h2> 구매 감사합니다.  </h2>
     상품번호 : ${partner_order_id} <br> <br>
     사용자 ID :${partner_user_id} <br> <br>
     결제금액 : ${total_amount } <br> <br>
  
  </div> 
<br> 
</section>
<jsp:include page="/include/bottom.jsp"  />