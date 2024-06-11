<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<jsp:include page="/include/top.jsp" />


<section>
  <div align="center"> 
  	<br>
    <h2>맴버 목록</h2>
    <table>
    	<tr>
    		<td>아이디</td>
    		<td>평문암호</td>
    		<td>암호화암호</td>
    		<td>전화번호</td>
    		<td>우편번호</td>
    		<td>주소</td>
    		<td>상세주소</td>
    		<td>이름</td>
    		<td>나이</td>
    		<td>성별</td>
    		<td>등급</td>
    	</tr>	
    	<c:if test="${li==null}">
				<tr>
					<td colspan=5>레코드가 존재 하지 않습니다.</td>
				</tr>
			</c:if>
			<c:if test="${li!=null}">
				<c:forEach  var="m" items="${li}">
					<tr>
						<td><a href="${path}/MajustoryController?sw=E&mid=${m.mid}">${m.mid}</a></td>
						<td>${m.mpassword1}</td>
						<td>${m.mpassword2}</td>
						<td>${m.mphone}</td> 
						<td>${m.maddr1}</td> 
						<td>${m.maddr2}</td> 
						<td>${m.maddr3}</td> 	
						<td>${m.mname}</td>
						<td>${m.mage}</td>
						<td>${m.mgender}</td>
						<td>${m.mgrade}</td>
					</tr>
				</c:forEach>
			</c:if>		
    </table>
    <br>
  </div> 
</section>
<jsp:include page="/include/bottom.jsp" />