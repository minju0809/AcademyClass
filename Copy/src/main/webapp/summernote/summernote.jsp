<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/top.jsp" %>

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

<section>
<br>
  <div> </div> 
  <div align="center"> 

<form method="post" action="<%=path %>/SummernoteController"  >
<input  type=hidden  name="sw"   value="I" > 
<table  border=1  width=800>
<tr> <td>제목 </td><td><input  type=text  name="title"  style="width:100%;"> </td></tr>
<tr> <td>작성자 </td><td><input  type=text  name="name"  style="width:50%;"> </td></tr>
<tr> <td colspan=2 > 
     <textarea id="summernote" name="etc"></textarea>
</td>
</tr>
<tr> <td colspan=2 align="center" > 
     <input  type=submit  value="전송하기" style="height:70%;">
</td>
</tr>
</table>
  
</form>

  </div> 
<br>  
</section>
<%@ include file="/include/bottom.jsp" %>