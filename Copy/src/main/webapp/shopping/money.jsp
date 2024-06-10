<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page import="java.util.*" %>
<%@ page import="shopping.*" %>
<jsp:include page="/include/top.jsp"  />

<style>
  table{    width:700px;  }  
  .td1 {
    height: 40px; /* td의 높이를 이미지의 높이에 맞게 조정 */    
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
  <h2>  회원매출조회 (EL/JSTL 표기)   </h2>
<table  border=1 >
<tr align="center"> 
 <td class=td1>회원번호  </td>  <td>회원이름  </td>
 <td> 회원등급  </td> <td> 매출액  </td> 
</tr>

<c:if test="${li == null}">
	<tr><td colspan=5 class=td2> 레코드가 존재 하지 않습니다. </td> </tr>
</c:if>
 	
<c:if test="${li != null}">
	<c:forEach var="m"  items="${li}"   varStatus="status">
	    <c:if test="${status.index % 2 == 0 }">
	       <c:set var="bg" value="#121212" />
	    </c:if>
	    <c:if test="${status.index % 2 == 1 }">
	       <c:set var="bg" value="#ff22aa" />
	    </c:if>
		<tr  align="center" bgcolor="${bg}"> 
		<td class=td1>${m.getCustno()}  </td>  
		<td>
		
		${fn:substring(m.custname,0,1)}
		*
		${fn:substring(m.custname,2,fn:length(m.custname))}
		
		</td> 
		<td>
		  <c:choose>
		    <c:when test = "${m.getGrade() eq 'A'}">
		        VIP
		    </c:when>
		    <c:when test = "${m.getGrade() == 'B'}">
		        일반
		    </c:when>
		    <c:otherwise>
		        직원
		    </c:otherwise>
	      </c:choose>		 
		 </td> 
		<td> 
		
		 &#8361;<fmt:formatNumber  value="${m.getMoney()}" />		     
		
		</td> 	
		</tr>
	</c:forEach>
</c:if>	

</table>

  </div> 
<br> 
</section>
<jsp:include page="/include/bottom.jsp"  />