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

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=108fd5969e1c36f2b7cbd5a913507317"></script>
<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
    mapOption = { 
		// 37.5665851  , 126.9782038   ( 서울시청 )
		// 33.450701, 126.570667  ( 제주도 )
        center: new kakao.maps.LatLng(35.7325671, 127.9042308), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
 
// 마커를 표시할 위치와 title 객체 배열입니다
/*
var positions = [
    {
        title: '카카오', 
        latlng: new kakao.maps.LatLng(33.450705, 126.570677)
    },
    {
        title: '생태연못', 
        latlng: new kakao.maps.LatLng(33.450936, 126.569477)
    },

];
*/

var  custno = new Array();
var  title = new Array();
var  lat = new Array();
var  lon = new Array();

<% for (MemberVO  m : li ) { %>
   custno.push('<%=m.getCustno()%>');
   title.push('<%=m.getCustname()%>');
   lat.push(<%=m.getLat()%>);
   lon.push(<%=m.getLon()%>);
<%}%>

var positions = [];

// alert("title.length : " + title.length)
for (i=0 ; i < title.length ; i++ ){
	positions[i] = {
			title :  i + '.' + title[i] ,			
			latlng : new kakao.maps.LatLng(lat[i], lon[i])
	};
}

var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
//alert("positions.length : " + positions.length)
for (i = 0; i < positions.length; i++ ) {
    var imageSize = new kakao.maps.Size(24, 35); 
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
    var marker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: positions[i].latlng, // 마커를 표시할 위치
        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
        image : markerImage, // 마커 이미지 
        clickable:true
    });
    

    var p = function(idx, custnoK) {
    	
        kakao.maps.event.addListener(marker, 'click', function() {
            location.href = "<%=path%>/ShoppingController?sw=E&custno=" + custnoK;
        });
    }
            
    p(i,custno[i]);

}

</script>

</section>
<%@ include file="/include/bottom.jsp" %>