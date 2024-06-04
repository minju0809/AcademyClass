<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*" %>
<%@ page import="repsd.*" %>
<jsp:include page="/include/top.jsp"  />

<style>
	.img {
    display: block;
    margin: auto;
    width: 40px;
    height: 40px;
  }
</style>

<section>
<br>
  <div align="center"> 
	  1.페이지사이즈 : ${pageSize} &emsp;
	  2.페이지 list사이즈 : ${pageListSize}&emsp;
	  3.전체레코드 수 ${totalCount}&emsp;
	  4.총페이지 수 : ${totalPage}  <br>
	  5.현재레코드  : ${start+1 }&emsp;
	  6.현재페이지 : ${nowPage}&emsp;
	  7.가로하단시작 : ${listStartPage}&emsp;
	  8.가로하단끝 : ${listEndPage}
	  
		<table  border=1 >
			<tr align="center"> <td  width=30>순번 </td>  <td  width=100>이름 </td><td width=50> 사진 </td> <td> 제목  </td> <td width=30> ref  </td> 
		  <td width=30> step  </td> <td width=30> level   </td> <td width=30> cnt   </td>  </tr>
		
			<c:if test="${li == null}">
				<tr><td colspan=5 align="center"> 레코드가 존재 하지 않습니다. </td> </tr>
			</c:if>
			<c:if test="${li != null}">
				<c:forEach var="m"  items="${li}" >
					<tr> 
						<td> ${m.getIdx()} </td>
						<td><a href="${path}/RepsdController?sw=E&idx=${m.getIdx()}"> ${m.getSname()} </a> </td> 
						<td  width=50 ><img src="${path}/repsd/files/${m.getImg()}"  class=img >  </td>  	
						<td>
							<c:if test="${m.getRe_level() > 1}">	  
								<c:if test="${m.getRe_level() == 2}">
									<img src="${path}/replyBoard/img/sp.png" height=4 width=16 >
								</c:if>
								<c:if test="${m.getRe_level() != 2}">
									<img src="${path}/replyBoard/img/sp.png" height=4 width=16 >	    
								</c:if>		    
									<img src="${path}/replyBoard/img/re.png" height=4 width=16 >
							</c:if>
							${m.getTitle()} 
						</td> 
						<td> ${m.getRef()}  </td> 
						<td> ${m.getRe_step()}  </td> 
						<td> ${m.getRe_level()}  </td> 
						<td> ${m.getCnt()}  </td> 	
					</tr>
				</c:forEach>
			</c:if>
		</table>
		  <form action = RepsdController >
		   <input  type=hidden  name=sw  value="S">
		   <select name=ch1>
		    <option value="sname"> 이름 </option>
		   </select>
		   <input  type=text  name=ch2 >
		   <input  type=submit  value="검색하기" >
		  </form>
		      
		  <c:url  value="/RepsdController?sw=S&start=0&ch1=${ch1}"  var ="url">
		      <c:param name="ch2"  value="${ch2}" ></c:param>
		  </c:url>
		  <a href="${url}">처음</a> &emsp;
		
		  <c:set var="start">
		    ${(pageSize * pageListSize) * ((listStartPage - pageListSize -1) / pageListSize)} 
		  </c:set>
		  <fmt:parseNumber var="start" value="${start}" integerOnly="true" />
		  
		  <c:if test="${nowPage > pageListSize}">
		    <c:url value="/RepsdController?sw=S&start=${start}&ch1=${ch1}" var ="url">
			    <c:param name="ch2" value="${ch2}" ></c:param>
			  </c:url>
		    <a href="${url}">이전${pageListSize}페이지</a>&emsp;
		  </c:if>
		  <c:if test="${nowPage <= pageListSize}">
		  	이전${pageListSize}페이지&emsp;
		  </c:if>
		
		  <c:forEach var="i" begin="${listStartPage}"  end="${listEndPage}" >
		    <c:set var="start">
			    ${(i-1) * pageSize} 
			  </c:set>
			  <c:if  test="${ i <= totalPage}">
			    <c:url   value="/RepsdController?sw=S&start=${start}&ch1=${ch1}"  var ="url">
			      <c:param name="ch2"  value="${ch2}" ></c:param>
			    </c:url>
			      <a href="${url}"> ${i} </a> &nbsp;
			  </c:if>
		  </c:forEach>
		
		  <c:set var="start">
			  ${(pageSize * pageListSize) * (listEndPage / pageListSize)}
		  </c:set> 
		  <fmt:parseNumber var="start" value="${start}" integerOnly="true" />
		  
		  <c:if test="${listEndPage < totalPage}">
		    <c:url   value="/RepsdController?sw=S&start=${start}&ch1=${ch1}" var="url">
			  	<c:param name="ch2"  value="${ch2}" ></c:param>
			  </c:url>
		      <a href="${url}"> 다음${pageListSize}페이지</a>&nbsp;
		  </c:if>
		  <c:if test="${listEndPage >= totalPage}">
		      다음 ${pageListSize}페이지&emsp;
		  </c:if>
		  
		  <c:set var="start">
			  ${(totalPage - 1) * pageSize}
		  </c:set> 
		  <fmt:parseNumber var="start" value="${start}" integerOnly="true" />
		  
		  <c:url   value="/RepsdController?sw=S&start=${start}&ch1=${ch1}"  var ="url">
				<c:param name="ch2"  value="${ch2}" ></c:param>
		  </c:url>
		 		<a href="${url}">마지막</a>&nbsp;
  </div> 
<br> 
</section>
<%@ include file="/include/bottom.jsp" %>
