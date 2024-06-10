<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%@ page import="java.util.*" %>
<%@ page import="shopping.*" %>
<jsp:include page="/include/top.jsp"  />

<style>
  table{    width:900px;  }  
  .td1 {
    height: 30px; /* td의 높이를 이미지의 높이에 맞게 조정 */    
  }  
  .td2 {
    height: 30px; /* td의 높이를 이미지의 높이에 맞게 조정 */    
    text-align : center;
  }
  #img1{
   display:block; margin:auto;
   height: 50px;  width:50px;
  }
  
</style>

 

<section>
<br>
  <div align="center"> 
  <h2>  홈쇼핑 목록조회/수정 (EL / JSTL 수정)   </h2>
<table  border=1 >
<tr align="center"> <td>회원번호 </td>  <td>회원성명 </td> <td> 회원전화  </td>
 <td> 회원주소</td><td>가입일자</td> <td>고객등급</td> <td>도시코드</td>
 <td>latitude  </td><td>longitude </td>
</tr>
  <c:if test="${li == null}">
   <tr align="center">
   
    <td colspan=7 > 레코드가 존재하지 않습니다. </td>  
   
</tr>
  </c:if>


   <c:if test="${li != null}">

	<!-- 반복문 시작 -->
	<c:forEach  var="m" items="${li}">
	<tr  align="center"> 
	<td height=30> 
	 <a href="${path}/ShoppingController?sw=E&custno=${m.custno}">
	   ${m.custno}
	 </a>
	
	</td>  
	<td> ${m.custname}	    
	</td> 
	<td> ${m.phone}  </td> 
	<td align="left"> &nbsp;   ${m.address}  </td> 
	<td> ${m.joindate}  </td> 
	<td> ${m.grade}   </td> 	
	<td> ${m.city}   </td> 
	<td> ${m.lat} </td> 	
	<td> ${m.lon}  </td> 		
	</tr>
	</c:forEach>
     <!-- 반복문 끝 -->
   </c:if>
</table>

  </div> 
<br> 
</section>
<jsp:include page="/include/bottom.jsp"  />