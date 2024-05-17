<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="reBoard.*" %>

<%@ include file="/include/top.jsp" %>
<%
ExamVO m = (ExamVO)request.getAttribute("m");
List<ReExamVO> li = (List<ReExamVO>)request.getAttribute("li");
%>
<script  src="http://code.jquery.com/jquery-1.10.2.js" ></script>
<script>
  var  path='${pageContext.request.contextPath}';
  // alert("경로확인:" +path);
  jQuery.ajaxSetup({cache:false}); // 캐시 메모리 초기화
  
  $(document).ready( function(){
	  $('#saveK').click( function(){
		 		 
		  // alert("확인2:" + $('#sw').val() + $('#sno').val() + "," + $('#title').val() +"," + $('#details').val());
		  var  dataStr={
			  sw : $('#sw').val(),
				sno : $('#sno').val(),
				name : $('#name').val(),
				title : $('#title').val(),
				details : $('#details').val(),
		  };
		  
		  $.ajax({
			  type: "GET",
			  url : path + "/ReBoardController",
			  data : dataStr,
			  success: function (data){
			  	// alert("return: " + data);
			 		// alert("저장성공!!" + $('#sno').val() );
				  if(data == "T") {
					  location.replace(path+"/ReBoardController?sw=E&sno="+$('#sno').val()) ;
				  }
			  }
		  })
	  } )
  })
  
  function delK(idx, sno){
	  alert("idx: " +  idx + ", sno : " + sno);
	  location.href(path+"/ReBoardController?sw=D&sno="+sno+"&idx="+idx);
  }
</script>

<section>
	<br>
	<div align=center>
		<h2>성적 상세보기</h2>
		<form action="<%=path %>/ReBoardController">
			<input type=hidden name=sw value=U>
			<table border="1" width = 600 >
				<tr align="center" > 
				     <td class="td1">학&emsp;번</td> 
				     <td><input  type=text id=sno name=sno value="<%=m.getSno() %>" /></td>
				     <td class="td1">이&emsp;름</td> 
				     <td><input  type=text name=sname value="<%=m.getSname() %>" /></td>
				</tr>
				<tr> 
					<td class="td1">성적 </td>
			  		<td colspan=3 align="center" >
				         국어:<input  type=text  name=kor value="<%=m.getKor() %>"  size=7 />
				         영어:<input  type=text  name=eng value="<%=m.getEng() %>"  size=7 /> 
				         수학:<input  type=text  name=math value="<%=m.getMath() %>"  size=7 /> 
				         역사:<input  type=text  name=hist value="<%=m.getHist() %>"  size=7 /> 
						</td >	      
				</tr>
				<tr> 
					<td class="td1">특이사항  </td>
				  <td colspan=3><textarea  cols=50  rows=4 name=etc><%=m.getEtc() %></textarea>   </td></tr>
				<tr> 
					<td colspan=4 align="center" >
		      	<input  type=submit  value="수정하기" /> &emsp;
		      	<input  type=button  value="목록보기" /> 
		      </td>
				</tr>
			</table>
		</form>
		<form action="<%=path %>/ReBoardController">
			<input type=hidden id=sw name=sw value=Re>
			<table border="1" width = 600 >
				<tr align="center" > 
			    <td class="td1">작성자</td> 
			    <td class="td1">제목</td> 
			    <td rowspan=2 class="td1">저장</td> 
				</tr>
				<tr> 
					<td class="td1">
						<input type=text id=name name=name >
					</td>
					<td class="td1">
						<input type=text id=title name=title >
					</td>
				<tr>
				  <td colspan=2><textarea  cols=40  rows=4 id=details name=details><%=m.getEtc() %></textarea>   </td>
					<td colspan=3 align="center" >
		      	<!-- <input type=submit  value="저장하기" /> &emsp; -->
		      	<input type=button value="저장" id=saveK>
		      	
				  </td>
				</tr>
			</table>
			<table border=1 width=600>
				<tr>
					<td>idx</td>
					<td>sno</td>
					<td>name</td>
					<td>title</td>
					<td>details</td>
					<td>today</td>
					<td>삭제</td>
				</tr>
				<%
				if(li.size() == 0) {
				%>
				<tr>
					<td colspan=7>코맨트가 존재하지 않습니다.</td>
				</tr>
				<%
				} else {
					for(ReExamVO vo : li) {
				%>
				<tr>
					<td><%=vo.getIdx() %></td>
					<td><%=vo.getSno() %></td>
					<td><%=vo.getName() %></td>
					<td><%=vo.getTitle() %></td>
					<td><%=vo.getDetails() %></td>
					<td><%=vo.getToday() %></td>
					<td><input type=button onclick="delK('<%=vo.getIdx()%>', '<%=vo.getSno()%>')" value="삭제"></td>
			  </tr>
			  <%
					}
			  }
			  %>
			</table>
		</form>
	</div>
	<br>
</section>
<%@ include file="/include/bottom.jsp" %>