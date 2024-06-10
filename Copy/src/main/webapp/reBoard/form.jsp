<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/top.jsp" %>
<section>
<br>
  <div align="center">
   <h3> 성적저장하기  </h3> 
    <form  name = f1 onSubmit="return ckF()"  action="<%=path %>/ReBoardController">
      <input type=text name=sw value="I" >  
      <table border=1>
       <tr> <td> 학번  </td>
            <td> <input type=text id="sno" name=sno>  
                 <input type=button id="ckID" value="ID확인"  > 
            </td> </tr>
       <tr> <td> 이름  </td> <td> <input type=text  name=sname  id="sname" >   </td> </tr>
       <tr> <td> 국어  </td> <td> <input type=text  name=kor    id="kor" >   </td> </tr>
       <tr> <td> 영어  </td> <td> <input type=text  name=eng    id="eng"   value="88" >   </td> </tr>
       <tr> <td> 수학  </td> <td> <input type=text  name=math   id="math"  value="77" >   </td> </tr>
       <tr> <td> 역사  </td> <td> <input type=text  name=hist   id="hist"  value="88" >   </td> </tr>
       <tr> <td> 특이사항  </td> <td><textarea name=etc cols=50  rows=4></textarea>  </td> </tr>
       <tr> <td colspan=2 align="center">
             <input type=submit  value="저장하기"> 
             <input type=reset  value="다시작성"> 
            </td>  </tr>
      </table>    
    </form> 
  </div> 
  
<script  src="http://code.jquery.com/jquery-1.10.2.js" ></script>
<script>
  var  path='${pageContext.request.contextPath}';
  
   // alert("경로확인:" +path);
  
  jQuery.ajaxSetup({cache:false});
  
  $(document).ready( function(){
	  $('#ckID').click( function(){		 		 
		  alert("ckID확인:" + $('#sno').val() );
		  var  dataStr={				
			   sno : $('#sno').val(),
			   sw : 'ckID'	
		  };
		  
		  $.ajax({
			  type: "GET",
			  url : path + "/ReBoardController",
			  data : dataStr,
			  success: function (data){
				  alert("Return 확인:" + data );	  
				  if (data == "T"){
					  //  중복값이 있는 경우 
					  alert("사용 불가능한  ID 입니다.");
					  f1.sno.value="";
					  // $('#sno').val("");  	
					  // location.replace(path+"/ReBoardController?sw=E&sno="+$('#sno').val())
					  
				  }else{
					  // 중복값이 없는 경우
					  alert("사용가능한  ID 입니다.");					
				  }		
			  }				  
		  })		  
	  } )
  } ) 
   
  function ckF(){
	var snoK = f1.sno.value.trim();
	var korVal =  f1.kor.value;
	alert("snoK:" + snoK);	
	if ( snoK.length != 5 ){
		alert("ID는 5자리를 입력 해주세요");	
		f1.sno.focus();
		return  false;
	} else if (  korVal.length <= 0 ||  korVal < 0 || korVal > 100   ) {
		alert("국어점수는 0~100 사이 숫자.!! ");	
		f1.kor.focus();
		return  false;		
	}
	
  }

</script>
  
<br> 
</section>
<%@ include file="/include/bottom.jsp" %>