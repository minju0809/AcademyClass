<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="shopping.*" %>

<%@ include file="/include/top.jsp" %>
<%
MemberVO m = (MemberVO)request.getAttribute("m");
%>

<section>
	<br>
	<div align=center>
		<h2>홈쇼핑 회원 정보 수정</h2>
		
		<form action="<%=path %>/ShoppingController">
			<input type=hidden name=sw value=U>
			<table border="1">
				<tr> 
			  	<td colspan=2>
						<div id="map" style="width:100%;height:350px;"></div>
					</td> 
				</tr>
				<tr> 
			  	<td>회원번호</td> 
			  	<td><input type=text id=custno name=custno value="<%=m.getCustno() %>" /></td>
				</tr>
	     	<tr>
			    <td>회원이름</td> 
			    <td><input type=text name=custname value="<%=m.getCustname() %>" /></td>
				</tr>
				<tr> 
					<td>번호</td>
		  		<td><input type=text name=phone value="<%=m.getPhone() %>"  /></td>
	  		</tr>
	  		<tr> 
	  			<td>주소</td>
	        <td>
						<input type="hidden" id="postcode" name=postcode placeholder="우편번호">
						<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
						<input type="text" size=50 id="address" name=address placeholder="주소" value="<%=m.getAddress() %>"><br>
						<input type="text" id="detailAddress" name=detailAddress placeholder="상세주소">
						<input type="hidden" id="extraAddress" name=extraAddress placeholder="참고항목">
					</td>
        </tr>
        <tr>
        	<td>가입일자</td>  
	        <td><input type=text name=joindate value="<%=m.getJoindate() %>" /></td>
        </tr>
        <tr> 
        	<td>고객등급</td>
	        <td><input type=text name=grade value="<%=m.getGrade() %>" /></td>
        </tr>
        <tr>  
        	<td>도시코드</td>
	        <td><input type=text name=city value="<%=m.getCity() %>" /></td>	      
				</tr>
				<tr> 
					<td colspan=2 align="center" >
		      	<input type=submit value="수정하기" /> &emsp;
		      </td>
				</tr>
			</table>
		</form>
	</div>
	<br>
</section>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postcode').value = data.zonecode;
                document.getElementById("address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("detailAddress").focus();
            }
        }).open();
    }
</script>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5fd42cdd845577dc157f2510c3e96a73&libraries=services"></script>
<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

// 주소로 좌표를 검색합니다
geocoder.addressSearch('<%=m.getAddress() %>', function(result, status) {

    // 정상적으로 검색이 완료됐으면 
     if (status === kakao.maps.services.Status.OK) {

        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
            map: map,
            position: coords
        });

        // 인포윈도우로 장소에 대한 설명을 표시합니다
        var infowindow = new kakao.maps.InfoWindow({
            content: '<div style="width:150px;text-align:center;padding:6px 0;">주소</div>'
        });
        infowindow.open(map, marker);

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
    } 
});    
</script>

<%@ include file="/include/bottom.jsp" %>