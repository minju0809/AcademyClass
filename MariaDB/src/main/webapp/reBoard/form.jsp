<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/top.jsp" %>

<script  src="http://code.jquery.com/jquery-1.10.2.js" ></script>
<script>
  var  path='${pageContext.request.contextPath}';
  // alert("경로확인:" +path);
  jQuery.ajaxSetup({cache:false}); // 캐시 메모리 초기화
  
  $(document).ready( function(){
	  $('#ckID').click( function(){
		 		 
		  // alert("확인2:" + $('#sno').val());
		  var  dataStr={
				sw : 'ckID',
				sno : $('#sno').val()
		  };
		  
		  $.ajax({
			  type: "GET",
			  url : path + "/ReBoardController",
			  data : dataStr,
			  success: function (data){
			  // alert("return: " + data);
			  // alert("저장성공!!" + $('#sno').val() );
				  if(data == "T") {
					// 중복값이 있는 경우
					  alert("사용 불가능한 ID 입니다");
					  f1.sno.value = "";
					  // $('#sno').val("");
					  // location.replace(path+"/ReBoardController?sw=E&sno="+$('#sno').val()) ;
				  } else {
					  // 중복값이 없는 경우
					  alert("사용 가능한 ID 입니다");
				  }
			  }
		  })
	  })
  })
  
  function ckF() {
	  sno = f1.sno.value.trim();
	  kor = f1.kor.value;
	  if(sno.length != 5) {
		  alert("5자리 학번을 입력해 주세요")
		  f1.sno.focus();
		  return false;
	  } else if(kor.length <= 0 || kor < 0 || kor > 100) {
		  alert("국어점수는 0~100 사이 숫자!")
		  f1.kor.focus();
		  return false;
	  }
  }
</script>

<section>
	<br>
	<div align=center>
		<h2>저장하기</h2>
		<form name=f1 onsubmit="return ckF()" action="<%=path %>/ReBoardController">
			<input type=hidden name=sw value=I>
			<table border=1>
				<tr>
					<td>학번</td>
					<td><input type=text id=sno name=sno ><input type=button id=ckID value="ID확인"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type=text id=sname name=sname ></td>
				</tr>
				<tr>
					<td>국어</td>
					<td><input type=text id=kor name=kor value=88></td>
				</tr>
				<tr>
					<td>영어</td>
					<td><input type=text id=eng name=eng value=77></td>
				</tr>
				<tr>
					<td>수학</td>
					<td><input type=text id=math name=math value=66></td>
				</tr>
				<tr>
					<td>역사</td>
					<td><input type=text id=hist name=hist value=55></td>
				</tr>
				<tr>
					<td>특이사항</td>
					<td><textarea  cols=40 rows=4 id=etc name=etc></textarea></td>
				</tr>
				<tr>
					<td colspan=2 align=center>
						<input type=submit value="저장">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<br>
</section>
<%@ include file="/include/bottom.jsp" %>