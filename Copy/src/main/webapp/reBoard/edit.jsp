<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/top.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="reBoard.*" %>  
<%
ExamVo  m =(ExamVo) request.getAttribute("m"); 

List<ReExamVo>  li =( List<ReExamVo> ) request.getAttribute("li"); 
%> 

<section>
<br>
  <div> </div>
    <div align="center">  
	<h2> 성적처리 상세보기  </h2>
	<form name="f1"    action="<%=path %>/ReBoardController" 
	      method="post"  >
	<input  type="hidden"  name="sw"  value="U"  />
	<table border="1" width = 700 >
	<tr align="center" > 
	     <td class="td1">학&emsp;번  </td> 
	     <td>&emsp;<input  type=text  name=sno value="<%=m.getSno() %>" />  </td>
	     
	     <td class="td1">이&emsp;름  </td> 
	     <td>&emsp;<input  type=text  name=sname value="<%=m.getSname() %>" />  </td>
	     
	</tr>
	<tr> <td class="td1">성적 </td>
	     <td colspan=3 align="center" >
	         &emsp;국어:<input  type=text  name=kor value="<%=m.getKor() %>"  size=7 /> &emsp;
	         &emsp;영어:<input  type=text  name=eng value="<%=m.getEng() %>"  size=7 /> &emsp;	 
	         &emsp;수학:<input  type=text  name=math value="<%=m.getMath() %>"  size=7 /> &emsp;
	         &emsp;역사:<input  type=text  name=hist value="<%=m.getHist() %>"  size=7 /> &emsp;&emsp;
	      </td >	      
	</tr>
	<tr> <td class="td1">특이사항  </td>
	     <td colspan=3>&emsp;<textarea  cols=70  rows=4 name=etc><%=m.getEtc() %></textarea>   </td></tr>

	<tr> <td colspan=4 align="center" >
	       <input  type=submit  value="수정하기" /> &emsp;
	       <input  type=button  value="목록보기" /> 
	      </td>
	</tr>

</table>
</form>

<form name="f2"   action="<%=path %>/ReBoardController" 
	      method="post"  >
	<input  type="hidden"  name="sw" id="sw"   value="Re"  />
	<input  type="hidden"  name="sno" id=sno   value="<%=m.getSno() %>"  />
	<table border="1" width = 700 >
	<tr align="center" >
	<td class="td1" width=150>작성자</td> <td class="td1" width=400> 제 목 </td> <td  width=150> 저장/삭제 </td> </tr>
	<tr> 
	<td>&nbsp; <input  type=text  name="name" size=10  id=name   /> </td>
	<td>&nbsp; <input  type=text  name="title" size=50 id=title /> </td>
	<td  rowspan=2 align="center">
	       <input  type=button  id="saveK"  value="저장" /> 
	</td>
	</tr>
	<tr> 
	<td colspan=2>&nbsp;<textarea cols=60 rows=2 name=details id=details></textarea> </td>
	</tr>

</table>
</form>
<table  border=1  width = 700 >
<tr align="center"> <td>순번 </td>  <td>작성자  </td> <td> 제목 </td> <td> 날짜  </td><td> 삭제  </td> </tr>

<% 
if ( li.size() ==0 ) {
	%>
	<tr><td colspan=5 class=td2 align="center"> 레코드가 존재 하지 않습니다. </td> </tr>
<% 	
} else {
	for(ReExamVo  m1 :li) { %>
	<tr> 
	<td> <%=m1.getIdx() %> </td>  
	<td> <%=m1.getName() %> </td> 
	<td> <%=m1.getTitle() %>  </td> 
	<td> <%=m1.getToday() %> </td>
	<td align="center" > 
	<input  type=button 
	        onClick="delK('<%=m1.getIdx() %>', '<%=m.getSno() %>')"  value="삭제">  </td>
	</tr>
	
	<tr> 
	<td colspan=5> <%=m1.getDetails() %> </td>  
	</tr>
	
	<% } 
}
%>

</table>
</div>
<script  src="http://code.jquery.com/jquery-1.10.2.js" ></script>
<script>
  var  path='${pageContext.request.contextPath}';
  
   // alert("경로확인:" +path);
  
  jQuery.ajaxSetup({cache:false});
  
  $(document).ready( function(){
	  $('#saveK').click( function(){
		 		 
		  alert("확인2:" + $('#sno').val() + "," + $('#sw').val()   +"," +  $('#name').val()   );
		  var  dataStr={
				 sw : $('#sw').val(),
				 sno : $('#sno').val(),
				 name : $('#name').val(),
				 title : $('#title').val(),	
				 details : $('#details').val()
		  };
		  
		  $.ajax({
			  type: "GET",
			  url : path + "/ReBoardController",
			  data : dataStr,
			  success: function (data){
				  alert("Return 확인:" + data );	  
				  if (data == "T"){
					 // 히스토리에 저장되지 않는다.  
					 location.replace(path+"/ReBoardController?sw=E&sno="+$('#sno').val()) 
				  }			
			  }				  
		  })		  
	  } )
  } ) 
  
  function  delK(idx, sno){
	  alert("번호확인:" + idx + "sno:" + sno )
	  // 히스토리에 저장된다.!!  
	  location.href=path+"/ReBoardController?sw=D&idx="+idx+"&sno="+sno;
  }
	  

</script>
​     
<br> 
</section>

<%@ include file="/include/bottom.jsp" %>