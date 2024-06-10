<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/top.jsp" %>

<section>
<br>
  <div align="center">
   <h3> 답변형 게시판 새글작성  </h3> 
    <form  name = f1 method=post 
           action="<%=path %>/RepsdController"
           enctype="multipart/form-data">
      <input type=hidden name=sw value="I" size=10>       
      <table border=1 width=450>
       <tr> <td width=100 align="center"> 이 름  </td> <td> <input type=text  name=sname  id="sname" >   </td> </tr>
       <tr> <td align="center"> 제 목  </td> <td> <input type=text  name=title  id="title"  size=50>   </td> </tr>
       <tr> <td align="center"> 파 일  </td> <td> <input type=file  name=img  id="img" >   </td> </tr>
       <tr> <td align="center"> 내 용  </td> <td> <textarea cols=40 rows=4 name=etc></textarea>   </td> </tr>
       
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