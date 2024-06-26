<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/top.jsp" %>
<section>
<br>
  <div align="center">
   <h2> 홈쇼핑 회원등록  </h2> 
    <form  name = f1  action="<%=path %>/ShoppingController">
      <input type=hidden name=sw value="OK" >  
      <table border=1>
       <tr> <td>회원번호(자동발생)</td>
            <td> <input type=text id="custno" name=custno>               
            </td> </tr>
       <tr> <td> 회원성명  </td> <td> <input type=text  name=custname  id="custname" >   </td> </tr>
       <tr> <td> 회원전화  </td> <td> <input type=text  name=phone    id="phone"  size=30 >   </td> </tr>
       
        <tr> <td> 회원주소  </td> <td width=550>
        
        <input type="hidden" id="sample4_postcode" placeholder="우편번호" name=postcode>
		<input type="text" id="sample4_roadAddress" placeholder="도로명주소" name=roadAddress  size=40 >
		<input type="hidden" id="sample4_jibunAddress" placeholder="지번주소" name=jibunAddress>
		<span id="guide" style="color:#999;display:none"></span>
		<input type="text" id="sample4_detailAddress" placeholder="상세주소" name=detailAddress>
		<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기">
		<input type="hidden" id="sample4_extraAddress" placeholder="참고항목" name=extraAddress>
        </td> </tr>
       
       
       <tr> <td> 가입일자  </td> <td> <input type=text  name=joindate   id="joindate"   >   </td> </tr>
       <tr> <td> 고객등급[A:VIP,B:일반,C:직원]</td> <td> <input type=text  name=grade   id="grade"  >   </td> </tr>
       <tr> <td> 도시코드  </td> <td> <input type=text  name=city   id="city"   > </td> </tr>
       <tr> <td colspan=2 align="center">
             <input type=submit  value="등록"> 
             <input type=reset  value="조회"> 
            </td>  </tr>
      </table>    
    </form> 
  </div> 
  
  
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample4_postcode').value = data.zonecode;
                document.getElementById("sample4_roadAddress").value = roadAddr;
                document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("sample4_extraAddress").value = '';
                }

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
</script>

  
<br> 
</section>
<%@ include file="/include/bottom.jsp" %>