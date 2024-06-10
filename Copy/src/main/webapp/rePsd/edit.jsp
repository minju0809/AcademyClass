<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/top.jsp" %>
<%@ page import="repsd.*" %>    
  
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
		alert("답글작성(RE)")
		location.href="<%=path%>/RepsdController?sw=RE&idx="+idx;
	}
	
	function funcD(idx){
		alert("삭제(D):"+idx)
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
   <h3> 답변형 게시판 상세보기  </h3> 
    <form  name = f1 method=post  
           action = "<%=path %>/RepsdController"        
           enctype="multipart/form-data" >
      <input type=hidden name=sw value="U" >
      <input type=text name=idx  value='${m.get("idx")}' size=10 >
      <input type=text name=ref  value='${m.get("ref")}' size=10 >
      <input type=text name=re_step   value='${m.get("re_step")}' size=10 >
      <input type=text name=re_level  value='${m.get("re_level")}'  size=10>
      
      <table border=1>
       <tr> <td> 이름   </td> <td> <input type=text  name=sname  id="sname" value='${m.get("sname")}' >   </td>
            <td rowspan=2> <img src='<%=path%>/rePsd/files/${m.get("img")}'  class=img > </td> </tr>
       <tr> <td> 제목   </td> <td> <input type=text  name=title  id="title" value='${m.get("title")}'  >   </td> </tr>
       <tr> <td> 이미지  </td> <td colspan=2> <input type=file  name=img  id="title"   >   </td> </tr>
       <tr> <td> 내용   </td> <td  colspan=2><textarea cols=40 rows=4 name=etc>${m.get("etc")}</textarea></td> </tr>
       
       <tr> <td colspan=3 align="center">
            <input type=submit  value="수정하기"> 
            <input type=button onClick="funcD(${m.get('idx')})" value="삭제하기">
            <input type=button onClick="funcRE(${m.get('idx')})" value="답글작성">
            <input type=button onClick="funcS()" value="목록보기">
            <input type=button onClick="funcF()" value="새글쓰기"> 
       </td>  </tr>
      </table>    
    </form> 
  </div> 
  
<br> 
</section>
<%@ include file="/include/bottom.jsp" %>