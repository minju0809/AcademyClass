<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/top.jsp" %>

<script  src="http://code.jquery.com/jquery-1.10.2.js" ></script>
<script>
  var  path='${pageContext.request.contextPath}';
  
   // alert("경로확인:" +path);
  
  jQuery.ajaxSetup({cache:false});
  
  $(document).ready(function() {
	  $('#ckID').click(function() {		 		 
		  alert("ckID확인:" + $('#id').val() );
		  var  dataStr={				
			   id : $('#id').val(),
			   password : $('#password').val(),
			   sw : $('#sw').val()	
		  };
		  
		  $.ajax({
			  type: "GET",
			  url : path + "/LoginController",
			  data : dataStr,
			  success: function (data){
				  alert("Return 확인:" + data );	  
				  if (data != ""){
					  alert("로그인 성공");
					  location.replace(path + "/LoginController?sw=success");
				  } else{
					  alert("아이디와 비밀번호를 확인해 주세요!");					
					  f1.id.value="";
					  f1.id.focus();
				  }		
			  }				  
		  })		  
	  })
  })

</script>

<section>
	<br>
	<div align=center>
		<h2>로그인</h2>
		<form name=f1 >
			<input type=hidden name=sw id=sw value="L">
			<table border=1>
				<tr>
					<td>아이디</td>
					<td><input type=text name=id id=id ></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type=text name=password id=password ></td>
				</tr>
				<tr>
					<td colspan=2 align=center>
						<input type=submit id="ckID" value="로그인">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<br>
</section>
<%@ include file="/include/bottom.jsp" %>