<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/top.jsp" %>
<%@ page import="repsd.*" %>    
<%
RepsdVo  m =(RepsdVo) request.getAttribute("m"); 
%>   
<style>
  table{    width:500px;  }  
  .img {   
          display: block;   
          margin:auto;
          width:60px;
          height:60px; 
       }
</style>

<script>  	
	function funcRE(idx){
		alert("수정(RE)")
		location.href="<%=path%>/RepsdController?sw=RE&idx="+idx;
	}
	
	function funcD(idx){
		alert("삭제(D)")
		location.href="<%=path%>/RepsdController?sw=D&idx="+idx;
	}
	
	function funcS(){
		alert("목록보기(S)")
		location.href="<%=path%>/RepsdController?sw=S";
	}
	
	function funcF(){
		alert("새글작성(F)")
		location.href="<%=path%>/RepsdController?sw=F";
	}

</script>

<section>
<br>
  <div align="center">
   <h3> 답글 작성하기  </h3> 
    <form  name = f1 method=post   
           action ="RepsdController"       
           enctype="multipart/form-data" >
      <input type=hidden name=sw value="Replay" >
      <input type=text name=idx  value="<%=m.getIdx()%>" size=10 >
      <input type=text name=ref  value="<%=m.getRef()%>" size=10 >
      <input type=text name=re_step   value="<%=m.getRe_step()%>" size=10 >
      <input type=text name=re_level  value="<%=m.getRe_level()%>"  size=10>
      
      <table border=1>
       <tr> <td> 이름   </td> <td> <input type=text  name=sname  id="sname" >   </td>
            <td rowspan=2> <img src="<%=path%>/rePsd/files/<%=m.getImg() %>"  class=img > </td> </tr>
       <tr> <td> 제목   </td> <td> <input type=text  name=title  id="title" value=<%=m.getTitle()%>  >   </td> </tr>
       <tr> <td> 이미지  </td> <td colspan=2> <input type=file  name=img  id="title"   >   </td> </tr>
       <tr> <td> 내용   </td> <td  colspan=2>[원본내용]<br><%=m.getEtc() %><br>
       <textarea cols=40 rows=4 name=etc></textarea></td> </tr>
       
       <tr> <td colspan=3 align="center">          
            <input type=submit  value="답글작성">
            <input type=button onClick="funcS()" value="목록보기">
            <input type=button onClick="funcF()" value="새글쓰기"> 
       </td>  </tr>
      </table>    
    </form> 
  </div> 
  
<br> 
</section>
<%@ include file="/include/bottom.jsp" %>