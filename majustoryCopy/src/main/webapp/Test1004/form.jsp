<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/include/top.jsp"  />
    <script>
        function chK() {
            var checkboxes = document.getElementsByName('ch');
            var names = document.getElementsByName('name');
            var amounts = document.getElementsByName('amount');

            var selectedNames = [];
            var selectedAmounts = [];

            for (var i = 0; i < checkboxes.length; i++) {
                if (checkboxes[i].checked) {
                    // alert("Checkbox " + i + " is checked");
                    selectedNames.push(names[i].value);
                    selectedAmounts.push(amounts[i].value);
                }
            }
            document.getElementById('selectedNames').value = selectedNames.join(',');
            document.getElementById('selectedAmounts').value = selectedAmounts.join(',');
            return true;
        }
    </script>

<section>
<br>
  <div  align="center"> <h2> 배열로 받아 오기  </h2> </div> 
  <div align="center"> 
   
   <form name=f1 action="${path}/TestController"  onSubmit=" return  chK()">
   <input  type=hidden  name=sw  value=I >
   
   <input  type=hidden  name=selectedNames  id=selectedNames >
   <input  type=hidden  name=selectedAmounts  id=selectedAmounts >
   
   
   <table border=1>
   <tr align="center">  
     <td> <input  type=checkbox  name=ch  > </td>
     <td> <input  type=text  name=name value="사과" > </td>
     <td> <input  type=text  name=amount value="3" ></td>
   </tr>
   <tr align="center">  
      <td> <input  type=checkbox  name=ch  > </td>
     <td> <input  type=text  name=name value="수박" > </td>
     <td> <input  type=text  name=amount value="2" ></td>
   </tr>
   <tr align="center">  
     <td> <input  type=checkbox  name=ch  > </td>
     <td> <input  type=text  name=name value="딸기" > </td>
     <td> <input  type=text  name=amount value="5" ></td>
   </tr>
   <tr>  
     <td colspan=3 align="center" >
      <input  type=submit  value="저장하기"  > </td>   
   </tr>
   </table>
      
   </form>
   
 
  </div> 
<br> 
</section>
<jsp:include page="/include/bottom.jsp"  />