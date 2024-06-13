<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="/include/top.jsp"  />
    <style>

        table {
            width: 80%;
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
    </style>
<section>
<br>
 <div> </div>
 <div align="center">
 <table  border=1 width=700 >
 <thead>
 <tr align="center">
 <th> 순번 </th><th >상품번호 </th>  <th  >상품이름</th>
 <th> 상품가격  </th> <th> 배송비  </th><th> 사진  </th>
 </tr>
 </thead>
<c:if test="${li.size() == 0}">
	<tr><td colspan=5 align="center"> 레코드가 존재 하지 않습니다. </td> </tr>
</c:if>
<c:if test="${li.size() != 0}">

	<c:forEach var="m"  items="${li}" >
	<c:set var="idx" value="${idx=idx+1}" />
	<tr> 
	<td>  ${idx}  </td>
	<td>  ${m.pid}  </td> 
	<td>  ${m.pname}  </td> 
	<td>  ${m.pprice}  </td> 
	<td>  ${m.pbaesongbi}  </td>
	<td align="center">  <img src=${path}/product/files/${m.pimg} width=50 height=50 >  </td> 


	</tr>
	</c:forEach>
</c:if>
</table>
</div>
<br> 
</section>

<jsp:include page="/include/bottom.jsp"  />