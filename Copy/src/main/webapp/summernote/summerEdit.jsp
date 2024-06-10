<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%-- <%@ include file="/include/top.jsp" %> --%>
<jsp:include page="/include/top.jsp"  />

<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<script>
  $(document).ready(function() {
      $('#summernote').summernote({
      	placeholder:'내용을 작성해 주세요!!',
          tabsize:2,
      	height:300        	
      });
  });
</script>

<style>
  table {width:900px;}  
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
  <h2>  SummerEdit ( EL / JSTL )    </h2>
	<form method="post" action="${path}/SummernoteController">
		<input type=hidden name=sw value=U>
		<table  border=1 >
			<tr align="center"> 
				<td>idx</td>  
				<td>title</td> 
				<td>name</td>
				<td>today</td> 
			</tr>
			<tr  align="center"> 
				<td><input type=text name="idx" value="${m.idx}" readonly></td> 
				<td><input type=text name="title" value="${m.title}"></td> 
				<td><input type=text name="name" value="${m.name}"></td> 	
				<td><input type=text name="today" value="${m.today}" readonly></td> 
			</tr>
			<tr align="center"> 
				<td colspan=4>
					<textarea id="summernote" name="etc">${m.etc}</textarea>
				</td> 
			</tr>
			<tr>
				<td colspan=4 align="center" > 
					<input  type=button onClick="newK()" value="새글작성" style="height:70%;"> &emsp;
			    <input  type=submit value="수정하기" style="height:70%;"> &emsp;
			    <input  type=button onClick="listK()" value="목록보기" style="height:70%;"> &emsp;
			    <input  type=button onClick="delK(${m.idx})" value="삭제하기" style="height:70%;">   
				</td>
			</tr>
		</table>
	</form>

</div> 
<br> 
</section>

<script>
function  delK(idx){
	alert("삭제:" + idx)
	location.href="${path}/SummernoteController?sw=D&idx="+idx
}

function  listK(){
	location.href="${path}/SummernoteController?sw=S"
}

function  newK(){
	location.href="${path}/SummernoteController?sw=F"
}
</script>

<jsp:include page="/include/bottom.jsp"  />