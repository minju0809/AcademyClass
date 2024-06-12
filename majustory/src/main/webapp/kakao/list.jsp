<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="/include/top.jsp"  />

<section>
<br>
	<div align="center">
		<h2>구매 감사합니다.</h2>
		상품번호: ${partner_order_id}<br>
		사용자ID: ${partner_user_id}<br>
		결제금액: ${total_amount}<br>
		결과: ${result}
	</div>
<br> 
</section>

<jsp:include page="/include/bottom.jsp"  />
