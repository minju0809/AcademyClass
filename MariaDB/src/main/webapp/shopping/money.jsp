<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="shopping.*" %>

<%@ include file="/include/top.jsp" %>
<%
List<MoneyVO> li = (List<MoneyVO>)request.getAttribute("li");
%>

<style>
.graph {
	display: flex;
}
</style>

<section>
	<div align=center>
	<br>
		<h2>홈쇼핑 목록 조회/수정</h2>
		<table border=1>
			<tr>
				<td>회원번호</td>
				<td>회원성명</td>
				<td>고객등급</td>
				<td>매출</td>
			</tr>
			<%
			if(li == null) {
			%>
				<tr>
					<td colspan=5>레코드가 존재 하지 않습니다.</td>
				</tr>
				<%
				} else {
						for(MoneyVO m : li) {
							String grade = m.getGrade();
							if(grade.equals("A")) {
								grade = "VIP";
							} else if(grade.equals("B")) {
								grade = "일반";
							} else if(grade.equals("C")) {
								grade = "직원";
							}
				%>
					<tr>
						<td><%=m.getCustno() %></td>
						<td><%=m.getCustname() %></td>
						<td><%=grade %></td>
						<td><%=m.getPrice() %></td>
					</tr>
				<%
				}
			}
			%>
		</table>
		<br>
		<div class=graph>
			<div id="chart_div" style="width: 50%; height: 250px;"></div>
		 	<div id="columnchart_values" style="width: 50%; height: 250px;"></div>
		</div>
	</div>
	<br>
</section>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">

  // Load the Visualization API and the corechart package.
  google.charts.load('current', {'packages':['corechart']});

  // Set a callback to run when the Google Visualization API is loaded.
  google.charts.setOnLoadCallback(drawChart);

  // Callback that creates and populates a data table,
  // instantiates the pie chart, passes in the data and
  // draws it.
  function drawChart() {

    // Create the data table.
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Topping');
    data.addColumn('number', 'Slices');
    data.addRows([
    	<%
    	 int i = 0 ;
         for(MoneyVO  m :li){       	     
           String custname = m.getCustname();    	  
     	  int price =  m.getPrice();
     	%>   
    	['<%=custname%>', <%=price%>],
    	<% } %>
    ]);

    // Set chart options
    var options = {'title':'매출 그래프',
                   'width':300,
                   'height':250};

    // Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
    chart.draw(data, options);
  }
</script>

<script type="text/javascript">
  google.charts.load("current", {packages:['corechart']});
  google.charts.setOnLoadCallback(drawChart);
  
  function drawChart() {
    color = ["silver", "gold", "#e5e4e2", "#b87333", "#fffaaa", "#333abc", "#3300bc", "#003abc", "#33BBbc", "#AA3abc" ] ;
    var data = google.visualization.arrayToDataTable([
      ["Element", "금액", { role: "style" } ],        
      <%
      int j = 0 ;
      for(MoneyVO  m :li){       	     
     	   String custname = m.getCustname();    	  
  	   int price =  m.getPrice();     

      %>
         ["<%=custname%>", <%=price%>, color[<%=j%>] ],
      <%
         j++;	
     	} 
      %>         
    ]);

    var view = new google.visualization.DataView(data);
    view.setColumns([0, 1,
                     { calc: "stringify",
                       sourceColumn: 1,
                       type: "string",
                       role: "annotation" },
                     2]);

    var options = {
      title: " ",
      width: 300,
      height: 250,
      bar: {groupWidth: "50%"},
      legend: { position: "none" },
    };
    var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values"));
    chart.draw(view, options);
}
</script>

<%@ include file="/include/bottom.jsp" %>