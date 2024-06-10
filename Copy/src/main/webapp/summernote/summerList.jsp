<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

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
	<h2>  SummerList ( EL / JSTL )    </h2>
	<table  border=1 >
		<tr align="center"> 
			<td>idx</td>  
			<td>title</td> 
			<td>name</td>
			<td>etc</td>
			<td>today</td>
			<td>삭제</td>
		</tr>
		<c:if test="${li == null}">
			<tr align="center">   
		    <td colspan=3> 레코드가 존재하지 않습니다. </td>  
			</tr>
		</c:if>
		<c:if test="${li != null}">
			<!-- 반복문 시작 -->
			<c:forEach  var="m" items="${li}">
				<c:url  value="/SummernoteController?sw=E&idx=${m.idx}"  var ="editUrl">
				</c:url>
				<tr  align="center"> 
					<td><a href="${editUrl}"> ${m.idx}</a></td> 
					<td> ${m.title}  </td> 
					<td> ${m.name}  </td> 	
					<td> ${m.etc}</td> 
					<td> ${m.today}</td> 
					<c:url  value="/SummernoteController?sw=D&idx=${m.idx}"  var ="deleteUrl">
					</c:url>
					<td><a href="${deleteUrl}">삭제</a></td>
				</tr>
			</c:forEach>
		<!-- 반복문 끝 -->
		</c:if>
	</table>
</div> 
<br> 
</section>
<jsp:include page="/include/bottom.jsp"  />