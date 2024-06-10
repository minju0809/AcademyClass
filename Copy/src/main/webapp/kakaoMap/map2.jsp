<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/top.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="shopping.*" %>
<%
  List<MemberVO>  li =( List<MemberVO> ) request.getAttribute("li"); 
%> 

<section>
<br>
  <div> </div> 
  <div align="center"> 
   
   <div id="map" style="width:70%;height:450px;"></div>
 
  </div> 
<br> 

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=108fd5969e1c36f2b7cbd5a913507317&libraries=services"></script>
<script>

alert("확인:" + '<%=path%>' )

var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
    mapOption = { 
		// 37.5665851  , 126.9782038   ( 서울시청 )
		// 33.450701, 126.570667  ( 제주도 )
		// 35.6770928  127.911657    
        center: new kakao.maps.LatLng(35.6770928, 127.911657), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

//주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

<%
  int i = 1;
  for (MemberVO m:li) {
	  i ++;
%>

    // alert("확인"+"${m.ADDRESS}")
    // 주소로 좌표를 검색합니다
    geocoder.addressSearch('<%=m.getAddress()%>', function(result, status) {
        	
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
              content: '<div style="width:150px;text-align:center;padding:6px 0;">'
                       + '<a href=<%=path%>/ShoppingController?sw=E&custno='+<%=m.getCustno()%>+'>'
                       + '<%=m.getCustname()%>' 
                       + '<img src=<%=path%>/kakaoMap/img/<%=m.getCustname()%>.PNG width=100  height=50 /></a></div>',
              removable : true
          });
              
          // 마커에 클릭이벤트를 등록합니다

          kakao.maps.event.addListener(marker, 'click', function() {
                
                infowindow.open(map, marker);  
          });
     
	    } 
	});
 <%
  }
 %>

</script>

</section>
<%@ include file="/include/bottom.jsp" %>