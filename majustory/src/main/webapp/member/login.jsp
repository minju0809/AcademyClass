<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/include/top.jsp" />

<script  src="http://code.jquery.com/jquery-1.10.2.js" ></script>
<script>
  var  path='${pageContext.request.contextPath}';
  
   // alert("경로확인:" +path);
  
  jQuery.ajaxSetup({cache:false});
  
  $(document).ready(function() {
	  $('#ckID').click(function() {		 		 
		  alert("ckID확인:" + $('#mid').val() );
		  var  dataStr={				
				 mid : $('#mid').val(),
				 mpassword1 : $('#mpassword1').val(),
			   sw : $('#sw').val()	
		  };
		  
		  $.ajax({
			  type: "GET",
			  url : path + "/MemberController",
			  data : dataStr,
			  success: function (data){
				  alert("Return 확인:" + data );	  
				  if (data != ""){
					  alert("로그인 성공" + data);
					  location.replace(path + "/MemberController?sw=loginOK");
				  } else{
					  alert("아이디와 비밀번호를 확인해 주세요!" + data);					
					  f1.mid.value="";
					  f1.mid.focus();
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
			<input type=hidden name=sw id=sw value=login >
			<table border=1>
				<tr>
					<td>아이디</td>
					<td><input type=text name=mid id=mid ></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type=text name=mpassword1 id=mpassword1 ></td>
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

<jsp:include page="/include/bottom.jsp" />