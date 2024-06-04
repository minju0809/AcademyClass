<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page import="java.util.*" %>
<%@ page import="shopping.*" %>
<jsp:include page="/include/top.jsp"  />
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
			<c:if test="${li == null}">
				<tr>
					<td colspan=5>레코드가 존재 하지 않습니다.</td>
				</tr>
			</c:if>
			<c:if test="${li != null}">
				<c:forEach var="m"  items="${li}"   varStatus="status">
				    <c:if test="${status.index % 2 == 0 }">
				       <c:set var="bg" value="#121212" />
				    </c:if>
				    <c:if test="${status.index % 2 == 1 }">
				       <c:set var="bg" value="#ff22aa" />
				    </c:if>
					<tr  align="center" bgcolor="${bg}"> 
					<td class=td1>${m.getCustno()}  </td>  
					<td>
					${fn:substring(m.custname,0,1)}
					*
					${fn:substring(m.custname,2,fn:length(m.custname))}
					
					</td> 
					<td>
					  <c:choose>
					    <c:when test = "${m.getGrade() eq 'A'}">
					        VIP
					    </c:when>
					    <c:when test = "${m.getGrade() == 'B'}">
					        일반
					    </c:when>
					    <c:otherwise>
					        직원
					    </c:otherwise>
				      </c:choose>		 
					 </td> 
					<td> 
						<fmt:formatNumber  value="${m.getPrice()}" />		     
					</td> 	
					</tr>
				</c:forEach>
			</c:if>	
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

<jsp:include page="/include/bottom.jsp"  />
