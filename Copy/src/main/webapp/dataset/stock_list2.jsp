<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/top.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="dataset.*" %>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.bundle.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.css">

<style>
  table{    width:800px;  }  
</style>

<%
  List<StockVO>  li =( List<StockVO> ) request.getAttribute("li"); 

   StockVO  s0 = li.get(0);
   StockVO  s1 = li.get(1);
   StockVO  s2 = li.get(2);
   StockVO  s3 = li.get(3);
   StockVO  s4 = li.get(4);
   
%> 

<section>
<br>
<div align="center"> 
<div class="chart-div" align=center >
    <canvas id="pieChartCanvas" width="500px" height="370px"></canvas>
</div>

<script>
	window.onload = function () {
		pieChartDraw();
	}
	 var data_tbl = {
		  table: 'listtable'
	   };

	let pieChartData = {
		labels: ['<%=s0.getCol1()%>', '<%=s1.getCol1()%>', '<%=s2.getCol1()%>', '<%=s3.getCol1()%>', '<%=s4.getCol1()%>'],
		datasets: [{
			data: [<%=s0.getCol2()%>,<%=s1.getCol2()%>,<%=s2.getCol2()%>,<%=s3.getCol2()%>,<%=s4.getCol2()%>],
			backgroundColor: ['rgb(255, 100, 250)', 'rgb(100, 255, 64)', 'rgb(55, 100, 64)', 'rgb(200, 255, 64)', 'rgb(100, 255, 170)']
		}] 
	};

	let pieChartDraw = function () {
		let ctx = document.getElementById('pieChartCanvas').getContext('2d');
		
		window.pieChart = new Chart(ctx, {
			type: 'pie',
			data: pieChartData,
			options: {
				responsive: false
			}
		});
	};

 </script>

     
<table  border=1 >
<tr>
 <td width=10%> 순번 </td>  <td width=55%  >종목명</td> 
 <td width=10%> 비중  </td> <td width=10%> 금액(억)  </td> 
 <td width=15%> 자산내 비중   </td>  
</tr>

<% 
if (li == null) {
	%>
	<tr><td colspan=5 class=td2> 레코드가 존재 하지 않습니다. </td> </tr>
<% 	
} else {
	
	Double sumC2 =0.0 ; int sumC3 =0 ; Double sumC4 =0.0 ;	
	for(int k1 =0 ; k1 < 10 ; k1++ ) { 
		StockVO  m = li.get(k1);
		sumC2 += Double.parseDouble(m.getCol2())  ;
		sumC3 += Integer.parseInt(m.getCol3())  ;
		sumC4 += Double.parseDouble(m.getCol4())  ;
	%>
	<tr> 
	<td> <%=m.getIdx() %> </td>  
	<td> <%=m.getCol1() %> </td> <td> <%=m.getCol2() %> </td>  
	<td> <%=m.getCol3() %> </td> <td> <%=m.getCol4() %> </td>
	</tr>
	<% } %>	
	<tr> 
	<td colspan=2> 상위 10 종목 누적합  </td>
	<td> <%=sumC2 %> </td> <td> <%=sumC3 %> </td>  
	<td> <%=sumC4 %> </td>
	</tr>
<%
}
%>

</table>

</div> 
<br> 

</section>


<%@ include file="/include/bottom.jsp" %>