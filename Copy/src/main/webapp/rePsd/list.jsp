<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page import="java.util.*" %>
<%@ page import="repsd.*" %>
<jsp:include page="/include/top.jsp"  />


<style>
  table{    width:800px;  }  
  .img {   
          display: block;   
          margin:auto;
          width:30px;
          height:30px; 
       }
</style>

<%
/*
  int  sidx =( int ) request.getAttribute("sidx"); 
  int  nowPage =( int ) request.getAttribute("nowPage");
  int  totalPage =( int ) request.getAttribute("totalPage");
  int  pageSize =( int ) request.getAttribute("pageSize"); 
  int  totalCount =( int ) request.getAttribute("totalCount"); 
  
  int  pageListSize =( int ) request.getAttribute("pageListSize"); 
  int  listStartPage =( int ) request.getAttribute("listStartPage"); 
  int  listEndPage =( int ) request.getAttribute("listEndPage"); 
  
  List<RepsdVo>  li =( List<RepsdVo> ) request.getAttribute("li");
  
  String  ch1 =(String) request.getAttribute("ch1"); 
  String  ch2 =(String) request.getAttribute("ch2"); 
 */ 
%> 

<section>
<br>
  <div> </div> 
  <div align="center"> 
  1.페이지사이즈 : ${pageSize} &emsp;
  2.페이지 list사이즈 : ${pageListSize}&emsp;
  3.전체레코드 수 ${totalCount}&emsp;
  4.총페이지 수 : ${totalPage}  <br>
  5.현재레코드  : ${sidx+1 }&emsp;
  6.현재페이지 : ${nowPage}&emsp;
  7.가로하단시작 : ${listStartPage}&emsp;
  8.가로하단끝 :${listEndPage}
 
<table  border=1 >
<tr align="center"> <td  width=30>순번 </td>  <td  width=100>이름 </td><td width=50> 사진 </td> <td> 제목  </td> <td width=30> ref  </td> 
     <td width=30> step   </td> <td width=30> level   </td> <td width=30> cnt   </td>  </tr>

<c:if test="${li == null}">
	<tr><td colspan=5 align="center"> 레코드가 존재 하지 않습니다. </td> </tr>
</c:if>
<c:if test="${li != null}">

	<c:forEach var="m"  items="${li}" >
	<tr> 
	<td> ${m.idx}  </td>
	<td><a href='${path}/RepsdController?sw=E&idx=${m.get("idx")}'> ${m.get("sname")} </a> </td> 
	<td  width=50 ><img src='${path}/rePsd/files/${m.get("img")}'  class=img >  </td>  	
	<td>
	
	 <c:if test="${m.get('re_level') > 1}">	  
		  <c:if test="${m.get('re_level') == 2}">
		     <img src="${path}/rePsd/img/space.png" height="10" width="${m.get('re_level')}" >
		  </c:if>
		  <c:if test="${m.get('re_level') != 2}">
			<img src="${path}/rePsd/img/space.png" height="10" width="${m.get('re_level')*10}" >	    
		 </c:if>		    
		    <img src="${path}/rePsd/img/icon_reply.png" >
	 </c:if>
	 
	 ${m.get("title")} 
	 
	</td> 
	<td>  ${m.get("ref")}  </td> 
	<td>  ${m.get("re_step")}  </td> 
	<td>  ${m.get("re_level")}   </td> 
	<td>  ${m.get("cnt")}   </td> 	
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
  
  <c:url  value="/RepsdController?sw=S&sidx=0&ch1=${ch1}"  var ="url">
      <c:param name="ch2"  value="${ch2}" ></c:param>
  </c:url>
  <a href="${url}">처음으로</a> &emsp;&emsp;
       
  <c:set var="sidx">
    ${ (pageSize*pageListSize)*((listStartPage - pageListSize -1)/pageListSize)} 
  </c:set>
  <fmt:parseNumber var="sidx" value="${sidx}" integerOnly="true" />

  <c:if test="${nowPage > pageListSize}">
	  <c:url   value="/RepsdController?sw=S&sidx=${sidx}&ch1=${ch1}"  var ="url">
	      <c:param name="ch2"  value="${ch2}" ></c:param>
	  </c:url>
      <a href="${url}">이전${pageListSize}페이지</a>&emsp;&emsp;
  </c:if>
  
  <c:if test="${nowPage <= pageListSize}">
  이전${pageListSize}페이지&emsp;&emsp;
   </c:if>
   

   <c:forEach var="i" begin="${listStartPage}"  end="${listEndPage}" >
       <c:set var="sidx">
	      ${(i-1) * pageSize} 
	   </c:set>
	   <c:if  test="${ i <= totalPage}">
	   
	   	  <c:url   value="/RepsdController?sw=S&sidx=${sidx}&ch1=${ch1}"  var ="url">
	        <c:param name="ch2"  value="${ch2}" ></c:param>
	      </c:url>
          <a href="${url}"> ${i} </a>&nbsp;&nbsp;
          
       </c:if>
   </c:forEach>


   <!-- 다음페이지  -->
   <c:set var="sidx">
	   ${pageSize * pageListSize * ( listEndPage / pageListSize)}
   </c:set> 
   <fmt:parseNumber var="sidx" value="${sidx}" integerOnly="true" />
   &emsp;
  
   <c:if test="${listEndPage < totalPage}">
       <c:url   value="/RepsdController?sw=S&sidx=${sidx}&ch1=${ch1}"  var ="url">
	      <c:param name="ch2"  value="${ch2}" ></c:param>
	   </c:url>
       <a href="${url}"> 다음${pageListSize}페이지</a>&nbsp;&nbsp;
    </c:if>
    
  <c:if test="${listEndPage >= totalPage}">
      다음 ${pageListSize}페이지&emsp;&emsp;
  </c:if>

   <!-- 마지막 페이지 -->
   <c:set var="sidx">
	   ${(totalPage -1 ) * pageSize}
   </c:set> 
   <fmt:parseNumber var="sidx" value="${sidx}" integerOnly="true" />
   <c:url   value="/RepsdController?sw=S&sidx=${sidx}&ch1=${ch1}"  var ="url">
	     <c:param name="ch2"  value="${ch2}" ></c:param>
   </c:url>
   <a href="${url}">마지막</a>&nbsp;&nbsp;
  
  </div> 
<br> 
 

</section>
<%@ include file="/include/bottom.jsp" %>