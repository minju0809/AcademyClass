<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="/include/top.jsp" />
<link href="${path}/member/member.css" rel="stylesheet" type="text/css" />

<section>
	<br>
	<div align="center">
		<div class="form-container">
			<h2>마주스토리 쇼핑몰 회원가입</h2>
			<form action="${path}/MemberController" method="get">
				<input type="hidden" name="sw" value="I">
				<div class="form-grid">
					<table border=1 width=550>
						<tr>
							<td width=30%><label for="mid">&emsp;아이디:</label></td>
							<td>&emsp;<input type="text" id="mid" name="mid"
								maxlength="15" required>
							</td>
						</tr>
						<tr>
							<td><label for="mpassword1">&emsp;암 호:</label></td>
							<td>&emsp;<input type="password" id="mpassword1"
								name="mpassword1" maxlength="20">
							</td>
						</tr>
						<tr>
							<td><label for="mphone">&emsp;전화번호:</label></td>
							<td>&emsp;<input type="text" id="mphone" name="mphone"
								maxlength="15">
							</td>
						</tr>
						<tr>
							<td><label for="maddr1">&emsp;우편번호:</label></td>
							<td>&emsp;<input type="text" name="maddr1"
								id="sample6_postcode" placeholder="우편번호" style="width: 50%">
								<input type="button" onclick="sample6_execDaumPostcode()"
								value="우편번호 찾기"><br> &emsp;<input type="text"
								name="maddr2" id="sample6_address" placeholder="주소" size=50><br>
								&emsp;<input type="text" name="maddr3"
								id="sample6_detailAddress" placeholder="상세주소"> <input
								type="hidden" name="sample6_extraAddress"
								id="sample6_extraAddress" placeholder="참고항목">
							</td>
						</tr>
						<tr>
							<td><label for="mname">&emsp;이름:</label></td>
							<td>&emsp;<input type="text" id="mname" name="mname"
								maxlength="20">
							</td>
						</tr>
						<tr>
							<td><label for="mage">&emsp;나이:</label></td>
							<td>&emsp;<input type="number" id="mage" name="mage" min="0"
								max="999" style="width: 50%">
							</td>
						</tr>
						<tr>
							<td><label for="mgender">&emsp;성별:</label></td>
							<td>&emsp;<input type="text" id="mgender" name="mgender"
								maxlength="1" style="width: 50%">
							</td>
						</tr>
						<tr>
							<td><label for="mgrade">&emsp;등급:</label></td>
							<td>&emsp;<select id="mgrade" name="mgrade">
									<option value="VIP">VIP</option>
									<option value="regular">Regular</option>
									<option value="guest">Guest</option>
									<option value="admin">Admin</option>
							</select>
							</td>
						</tr>
						<tr>
							<td><label for="metc">&emsp;특이사항:</label></td>
							<td>&emsp;<textarea id="metc" name="metc" maxlength="1000"></textarea>
							</td>
						</tr>
						<tr>
							<td colspan=2 align="center"><input type="submit"
								value="회원가입하기" style="width: 40%; height: 30px"> &emsp;
								<input type="reset" value="다시작성하기"
								style="width: 40%; height: 30px"></td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</div>
	<br>

	<script
		src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="kakaoaddr.js"></script>

</section>
<jsp:include page="/include/bottom.jsp" />
