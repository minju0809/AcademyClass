<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="shopping.*" %>

<%@ include file="/include/top.jsp" %>
<%
List<MemberVO> li = (List<MemberVO>)request.getAttribute("li");
%>

<section>
  <div id="map" style="width:100%;height:350px;"></div>
</section>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5fd42cdd845577dc157f2510c3e96a73&libraries=services"></script>
<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {             
        center: new kakao.maps.LatLng(37.4562557, 126.7052062), // 지도의 중심좌표
        level: 10 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

<% 
for (MemberVO m : li) { 
%>
    // 주소로 좌표를 검색합니다
    geocoder.addressSearch('<%=m.getAddress() %>', function(result, status) {
        	
    	// 정상적으로 검색이 완료됐으면
        if (status === kakao.maps.services.Status.OK) {
          // (2)  좌표값이 리턴되는지 확인하기  
          // alert('${m.ADDRESS}' + ":" +result[0].y +':'+ result[0].x) 
          
          var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
          
          // 결과값으로 받은 위치를 마커로 표시합니다
          var marker = new kakao.maps.Marker({
            map: map,
            position: coords,
          });

          // 인포윈도우를 생성합니다
          var infowindow = new kakao.maps.InfoWindow({
        	  content: '<div style="width:150px;text-align:center;padding:6px 0;">' +
              '<a href="<%=path %>/ShoppingController?sw=E&custno=<%=m.getCustno() %>">' +
              '<img src="<%=path %>/kakao/img/<%=m.getCustname() %>.png" style="width:100px;height:auto;" />' +
              '<br /><%=m.getCustname() %>' +
              '</a></div>',
              removable : true
          });
              
          // 마커에 클릭이벤트를 등록합니다

          kakao.maps.event.addListener(marker, 'click', function() {
                // 마커 위에 인포윈도우를 표시합니다
                infowindow.open(map, marker);  
          });
     
	    } 
	});
<%
}
%>

</script>

<%@ include file="/include/bottom.jsp" %>
