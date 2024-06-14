<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="/include/top.jsp" />
<link href="${path}/member/member.css" rel="stylesheet" type="text/css" />

<section>
	<br><br>
	<div align="center">
		<div class="form-container">
			<h2>마주스토리 회원로그인</h2>
			ABC329 / wejd <br> ABC674 / kjwdc
			<form action="${path}/MemberController" method="get">
				<input type="hidden" name="sw" value="loginOK">
				<div class="form-grid">
					<table border=1 width=450>
						<tr>
							<td width=30%><label for="mid">&emsp;아이디:</label></td>
							<td>&emsp;<input type="text" id="mid" name="mid"
								maxlength="15" required value='ZAB573'>
							</td>
						</tr>
						<tr>
							<td><label for="mpassword1">&emsp;암 호:</label></td>
							<td>&emsp;<input type="password" id="mpassword1"
								name="mpassword1" maxlength="20" value='ufle'>
							</td>
						</tr>
						<tr>
							<td colspan=2 align="center"><input type="submit"
								value="로그인" style="width: 40%; height: 30px"> &emsp; <input
								type="submit" value="회원가입" style="width: 40%; height: 30px">
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</div>
	<br>

</section>
<jsp:include page="/include/bottom.jsp" />
