<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/top.jsp" %>

<section>
<br>
  <div align="center">
   <h3> 로그인  </h3> 
    <form  name =f1 method=post  >
      <input type=hidden name=sw value="login" size=10 id="sw">       
      <table border=1 width=450>
       <tr> <td width=100 align="center"> 아이디  </td> <td> <input type=text  name=id  id="id" >   </td> </tr>
       <tr> <td align="center"> 패스워드 </td> <td> <input type=text  name=password  id="password"  size=30>   </td> </tr>
       
       <tr> <td colspan=2 align="center">
       
            <input type=submit id="ckID"  value="로그인"> 
            
       </td>  </tr>
      </table>    
    </form> 
</div> 
  
  
<script  src="http://code.jquery.com/jquery-1.10.2.js" ></script>
<script>
  var  path='${pageContext.request.contextPath}';
    
  jQuery.ajaxSetup({cache:false});
  
  $(document).ready( function(){
	  $('#ckID').click( function(){		 		 
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
					  location.href=path+"/LoginController?sw=success";					  
					}else{
					  alert("로그인 실패.");
					  f1.id.value="";
					  f1.password.value="";
					  f1.id.focus();
					}
			  }				  
		  })		  
	  } )
  } ) 


</script>
  
  
  
  
<br> 
</section>
<%@ include file="/include/bottom.jsp" %>