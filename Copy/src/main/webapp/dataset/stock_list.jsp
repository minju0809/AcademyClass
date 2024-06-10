<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/top.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="dataset.*" %>

<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="http://code.highcharts.com/highcharts.js"></script> 
<script src="http://code.highcharts.com/modules/data.js"></script>   

<style>
  table{    width:800px;  }  
</style>

<%
  List<StockVO>  li =( List<StockVO> ) request.getAttribute("li"); 
%> 

<section>

<div  align="center">
<div id=body1>
	 <div id = hidden style="display:none" > 
	 <!-- 표는 숨겨준다.!!  -->
		 <table border=1  width=800  id='datatable' >
		
		    <tr><td> 이름 </td>  <td> 투자금액 </td> </tr> 
		    <%
            for(int i=0 ; i < 10 ; i++) {
            	StockVO   m =li.get(i);
            %>
		    <tr>
		    <td> <%=m.getCol1() %>  </td> 
		    <td> <%=m.getCol3()%> </td>   

		    </tr>
           <% } %> 
		</table>
	</div>
</div>

<br>
<div id="container" style="width: 800px; height: 430px; margin: 0 auto;"></div>



<br>
  <div> </div> 
  <div align="center"> 

<table  border=1 >
<tr>
 <td> 순번 </td>  <td width=50%  >종목명</td> 
 <td> 비중  </td> <td> 금액(억)  </td> 
 <td > 자산내 비중   </td>  
</tr>

<% 
if (li == null) {
	%>
	<tr><td colspan=5 class=td2> 레코드가 존재 하지 않습니다. </td> </tr>
<% 	
} else {
	for(StockVO  m :li) { %>
	<tr> 
	<td> <%=m.getIdx() %> </td>  
	<td> <%=m.getCol1() %> </td>  
	<td> <%=m.getCol2() %> </td>  
	<td> <%=m.getCol3() %> </td>  
	<td> <%=m.getCol4() %> </td>
	</tr>
	<% } 
}
%>

</table>


</div> 
<br> 

<br>
<script>
$(document).ready(function() { 
   var data = {
      table: 'datatable'
   };
   var chart = {
      type: 'column'
   };
   var title = {
      text: '국민연금 주식 투자 상위 10위'   
   };      
   var yAxis = {
      allowDecimals: false,
      title: {
         text: '투자금액'
      }
   };
   var tooltip = {
      formatter: function () {
         return '<b>' + this.series.name + '</b><br/>' +
            this.point.y + ' ' + this.point.name.toLowerCase();
      }
   };
   var credits = {
      enabled: false
   };  
      
   var json = {};   
   json.chart = chart; 
   json.title = title; 
   json.data = data;
   json.yAxis = yAxis;
   json.credits = credits;  
   json.tooltip = tooltip;  
   $('#container').highcharts(json);
});
</script>

</section>


<%@ include file="/include/bottom.jsp" %>