<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="/include/top.jsp"  />

<section>
<br>
 <div> </div>
 <div align="center">
 <table  border=1 width=200 >
<tr align="center"> <td  width=50>순번 </td>  <td  width=150>과일 </td>
 <td> 수량  </td>  </tr>
	<c:forEach var="m"  items="${li}" >
	<c:set var="idx" value="${ idx = idx + 1  }" />
	<tr> 
	<td> <input type=text name=idx value="${idx}" >  </td>
	<td> <input type=text name=name value="${m.name}" >   </td> 
	<td> <input type=text name=amount value="${m.amount}" >   </td> 
	</tr>
	<c:set var="sum" value="${ sum = sum + m.amount  }" />
	</c:forEach>
	<tr> <td colspan=3 > 수량 합계 ${sum} </td> </tr>
</table>
</div>
<br> 
</section>

<jsp:include page="/include/bottom.jsp"  />