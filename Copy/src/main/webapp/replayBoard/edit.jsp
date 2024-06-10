<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/top.jsp" %>
<%@ page import="replyBoard.*" %>    
<%
ReplyBoardVo  m =(ReplyBoardVo) request.getAttribute("m"); 
%>   

<script>
  function funcK(str){
	alert("str확인:" + str);  
	if (str == "U") {  
	  f1.sw.value="U";	
	  f1.action="ReplyBoardController"
	  f1.submit();
	}else if(str == "D"){
	  f1.sw.value="D";	
	  f1.action="ReplyBoardController"	
	  f1.submit();
	}else if(str == "S"){
	  f1.sw.value="S";	
	  f1.action="ReplyBoardController"
	  f1.submit();
	}else if(str == "I"){
	  f1.sw.value="I";	
	  f1.action="ReplyBoardController"	
	}else if(str == "RE"){
	  f1.sw.value="RE";	
	  f1.action="ReplyBoardController"	
	  f1.submit();
	}
  }
</script>

<section>
<br>
  <div align="center">
   <h3> 답변형 게시판 상세보기  </h3> 
    <form  name = f1 >
      <input type=hidden name=sw  >
      <input type=text name=idx  value="<%=m.getIdx()%>" size=10 >
      <input type=text name=ref  value="<%=m.getRef()%>" size=10 >
      <input type=text name=re_step   value="<%=m.getRe_step()%>" size=10 >
      <input type=text name=re_level  value="<%=m.getRe_level()%>"  size=10>
      
      <table border=1>
       <tr> <td> 이름  </td> <td> <%=m.getSname()%>   </td> </tr>
       <tr> <td> 제목  </td> <td> <%=m.getTitle()%>   </td> </tr>
       
       <tr> <td> 이름(N/R)  </td> <td> <input type=text  name=sname  id="sname" >   </td> </tr>
       <tr> <td> 제목(N/R)  </td> <td> <input type=text  name=title  id="title" >   </td> </tr>
       <tr> <td colspan=2 align="center">
            <input type=button onClick="funcK('U')"  value="수정하기"> 
            <input type=button onClick="funcK('D')" value="삭제하기">
            <input type=button onClick="funcK('RE')" value="답글작성">
            <input type=button onClick="funcK('S')" value="목록보기">
            <input type=button onClick="funcK('I')" value="새글쓰기"> 
       </td>  </tr>
      </table>    
    </form> 
  </div> 
  
<br> 
</section>
<%@ include file="/include/bottom.jsp" %>