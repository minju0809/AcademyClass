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

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5fd42cdd845577dc157f2510c3e96a73"></script>
<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
    mapOption = { 
        center: new kakao.maps.LatLng(37.5919112, 127.061803), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

var custno = new Array();
var title = new Array();
var latitude = new Array();
var longitude = new Array();

<% 
for(MemberVO m : li) { 
%>
	custno.push(<%=m.getCustno() %>);
	title.push('<%=m.getCustname() %>');
	latitude.push(<%=m.getLatitude() %>);
	longitude.push(<%=m.getLongitude() %>);
<%
} 
%>

var positions = [];

for (i = 0; i <= title.length; i++) {
	positions[i] = {
			title : i + '.' + title[i],
			latlng : new kakao.maps.LatLng(latitude[i], longitude[i])
	};
}

// 마커 이미지의 이미지 주소입니다
var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
    
for (var i = 0; i < positions.length; i ++) {
    
    // 마커 이미지의 이미지 크기 입니다
    var imageSize = new kakao.maps.Size(24, 35); 
    
    // 마커 이미지를 생성합니다    
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
    
    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: positions[i].latlng, // 마커를 표시할 위치
        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
        image : markerImage // 마커 이미지 
    });
    
    (function(marker, position) {
        var iwContent = '<div style="padding:5px;"><a href="<%=path %>/ShoppingController?sw=E&custno=' + custno[i] + '">' + position.title + '</a></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
            iwRemoveable = true; // removeable 속성을 true로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

        // 인포윈도우를 생성합니다
        var infowindow = new kakao.maps.InfoWindow({
            content: iwContent,
            removable: iwRemoveable
        });

        // 마커에 클릭이벤트를 등록합니다
        kakao.maps.event.addListener(marker, 'click', function() {
            // 마커 위에 인포윈도우를 표시합니다
            infowindow.open(map, marker);  
        });
    })(marker, positions[i]);
}
</script>

<%@ include file="/include/bottom.jsp" %>
