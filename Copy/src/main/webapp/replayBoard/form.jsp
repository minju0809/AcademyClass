<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/top.jsp" %>
<%
// String  ref = request.getParameter("ref");
int  ref = ( int )request.getAttribute("ref");
%>
<section>
<br>
  <div align="center">
   <h3> 답변형 게시판 새글작성  </h3> 
    <form  name = f1  action="<%=path %>/ReplyBoardController">
      <input type=text name=sw value="I" size=10>  
      <input type=text name=ref  value="<%=ref%>" size=10 >
      <table border=1>
       <tr> <td> 이름  </td> <td> <input type=text  name=sname  id="sname" >   </td> </tr>
       <tr> <td> 제목  </td> <td> <input type=text  name=title  id="title" >   </td> </tr>
       <tr> <td colspan=2 align="center">
            <input type=submit  value="저장하기"> 
            <input type=reset  value="다시작성"> 
       </td>  </tr>
      </table>    
    </form> 
  </div> 
  
<br> 
</section>
<%@ include file="/include/bottom.jsp" %>