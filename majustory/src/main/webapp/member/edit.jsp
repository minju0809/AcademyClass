<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<jsp:include page="/include/top.jsp" />


<section>
  <div align="center"> 
  	<br>
    <h2>상세보기/수정</h2>
    <form action=${path}/MajustoryController>
    	<input type=hidden name=sw value=U >
    	<table>
	    <tr>
	    	<td>아이디</td>
	    	<td><input type=text name="mid" value="${m.mid}" readonly></td>
	    </tr>	
	    <tr>
	    	<td>평문암호</td>
	    	<td><input type=text name="mpassword1" value="${m.mpassword1}" readonly></td>
	    </tr>	
	    <tr>
	    	<td>암호화암호</td>
	    	<td><input type=text name="mpassword2" value="${m.mpassword2}" readonly></td>
	    </tr>	
	    <tr>
	    	<td>전화번호</td>
	    	<td><input type=text name="mphone" value="${m.mphone}" ></td>
	    </tr>	
	    <tr>
	    	<td>우편번호</td>
	    	<td><input type=text name="maddr1" value="${m.maddr1}" ></td>
	    </tr>	
	    <tr>
	    	<td>주소</td>
	    	<td><input type=text name="maddr2" value="${m.maddr2}" ></td>
	    </tr>	
	    <tr>
	    	<td>상세주소</td>
	    	<td><input type=text name="maddr3" value="${m.maddr3}" ></td>
	    </tr>	
	    <tr>
	    	<td>이름</td>
	    	<td><input type=text name="mname" value="${m.mname}" readonly></td>
	    </tr>	
	    <tr>
	    	<td>나이</td>
	    	<td><input type=text name="mage" value="${m.mage}" readonly></td>
	    </tr>	
	    <tr>
	    	<td>성별</td>
	    	<td><input type=text name="mgender" value="${m.mgender}" readonly></td>
	    </tr>	
	    <tr>
	    	<td>등급</td>
	    	<td><input type=text name="mgrade" value="${m.mgrade}" ></td>
	    </tr>	
	    <tr>
	    	<td>특이사항</td>
	    	<td><input type=text name="metc" value="${m.metc}" ></td>
	    </tr>	
	    <tr>
	    	<td colspan=2 align=center><input type=submit value=수정하기 ></td>
	    </tr>
    </table>
    </form>
    <br>
  </div> 
</section>
<jsp:include page="/include/bottom.jsp" />