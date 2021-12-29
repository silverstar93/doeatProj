<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="../js/jquery-3.3.1.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="../scripts/addrchk.js"></script>
<script src="../scripts/join.js"></script>


<div class="temptitle"><h1>회원가입</h1></div>
<form name="jj" action="joinReg" method="post"
	enctype="multipart/form-data">
	<input type="hidden" value="${param.cate }" name="cate" />
	<table cellspacing="5">
		<tr>
			<td>아이디</td>
			<td><input type="text" id="id" name="id" /> <input
				type="button" id="idChk" name="idChk" onclick="idCheck()"
				value="중복확인" /></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="pw" id="pw" /><font size="2" color="#999">비밀번호는 6자리 이상,
				12자 이하로 설정해 주시기 바랍니다. (허용특수문자: ~!#$%^*)</font></td>
		</tr>
		
		<tr>
			<td>비밀번호 확인</td>
			<td><input type="password" name="pwChk" id="pwChk" /></td>
		</tr>
				<tr>
			<td colspan="2">　</td>
		</tr>
			<tr>
			<td colspan="2" bgcolor="#bbb"></td>
		</tr>
				<tr>
			<td colspan="2">　</td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" id="name" name="name" /></td>
		</tr>

		<tr>
			<td>생년월일</td>
			<td><input type="text" id="yy" name="byear" maxlength="4"
				size="5" />년 <select id="month" name="bmonth">
					<c:forEach begin="1" end="12" var="i">
						<option>${i }</option>
					</c:forEach>
			</select>월 <input type="text" id="dd" name="bdate" maxlength="2" size="3" />일</td>
		</tr>
		<tr>
			<td>성별</td>
			<td><input type="radio" name="gen" id="male" value="남자" />남자 <input
				type="radio" name="gen" id="female" value="여자" />여자</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" id="mail1" name="mail1" /> @ <input
				type="text" name="mail2" id="mail2" />  
				<input type="button" id="mailChk" name="mailChk" onclick="mailCheck()"
				value="중복확인" /></td>
		</tr>
		<tr>
			<td>휴대전화</td>
			<td><select name="tel1" id="tel1">
					<c:forEach var="i" items="${tel}">
						<option>${i }</option>
					</c:forEach>
			</select> - <input name="tel2" id="tel2" type="text" maxlength="4" size="5" />
				- <input name="tel3" id="tel3" type="text" maxlength="4" size="5" /></td>
		</tr>
			<tr>
			<td colspan="2">　</td>
		</tr>
			<tr>
			<td colspan="2" bgcolor="#bbb"></td>
		</tr>
			<tr>
			<td colspan="2">　</td>
		</tr>

		<c:set value="${param.cate }" var="cc"></c:set>
		<c:choose>
			<c:when test="${cc==1 }">
				<tr>
					<td>좋아하는 음식</td>
					<td><select name="favMenu">
							<c:forEach var="i" items="${menu}">
								<option>${i }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>자주가는 지역</td>
					<td><select name="myLocal">
							<c:forEach var="i" items="${location }">
								<option>${i }</option>
							</c:forEach>
					</select></td>
				</tr>
			</c:when>
			<c:when test="${cc==2 }">

				<tr>
					<td colspan="2"><font size="5" color="#FF4D00">식당 정보</font></td>
				</tr>
							<tr>
			<td colspan="2">　</td>
		</tr>
					<tr>
			<td colspan="2" bgcolor="#bbb"></td>
		</tr>
			<tr>
			<td colspan="2">　</td>
		</tr>
				<tr>
					<td>사업자번호</td>
					<td><input type="text" id="rno" name="rno" maxlength="9"/> <font color="#999"
						size="2">주소 및 전화번호는 사업자등록증과 동일해야합니다 </font></td>
				</tr>

				<tr>
					<td>상호명</td>
					<td><input type="text" name="rname" id="rname" /></td>
				</tr>

				<tr>
					<td>대표자</td>
					<td><input type="text" name="rowner" id="rowner"/></td>
				</tr>

				<tr>
					<td class="review">주소</td>
					<td>
				<input type="text" name="raddr" id="sample4_postcode" placeholder="우편번호">
				<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
				<input type="text" name="raddr1" id="sample4_roadAddress" placeholder="도로명주소">
				<span id="guide" style="color:#999;display:none"></span>
				<input type="text" name="raddr2" id="sample4_detailAddress" placeholder="상세주소">
			</td>
				</tr>

				<tr>
					<td>전화번호</td>
					<td><select name="rtel1" id="rtel1">
					<c:forEach var="i" items="${rtel }">
						<option>${i }</option>
					</c:forEach>
			</select> - <input name="rtel2" id="rtel2" type="text" maxlength="4" size="5" />
				- <input name="rtel3" id="rtel3" type="text" maxlength="4" size="5" /></td>
				</tr>

				<tr>
					<td>영업시간</td>
					<td><select name="rhour1">
							<c:forEach var="i" begin="0" end="24" step="1">
								<option value="${i }:00">${i }:00</option>
							</c:forEach>
					</select> - <select name="rhour2">
							<c:forEach var="i" begin="0" end="24" step="1">
								<option value="${i }:00">${i }:00</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>메뉴</td>
					<td>대분류: <select name="rmenu1">
								<c:forEach var="i" items="${menu}">
								<option>${i }</option>
							</c:forEach>
					</select> 소분류: <input type="text" id="rmenu2" name="rmenu2" />
					</td>

				</tr>

				<tr>
					<td>가격</td>
					<td><input type="text" name="rprice1" id="rprice1" maxlength="6"/> - <input type="text"
						name="rprice2" id="rprice2" maxlength="6"/></td>
				</tr>
				<tr>
					<td>주요메뉴</td>
					<td><input type="text" name="rmain1" id="rmain1"/><br> <input
						type="text" name="rmain2" id="rmain2"/><br> <input type="text"
						name="rmain3" id="rmain3"/></td>
				</tr>
				<tr>
					<td>사진</td>
					<td><input type="file" name="img" /></td>
				</tr>
				<tr>
					<td>사업자 등록증(스캔본)</td>
					<td><input type="file" name="offpic" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"></td>
				</tr>
			</c:when>
		</c:choose>
			<tr>
			<td colspan="2">　</td>
		</tr>
	<tr>
			<td colspan="2" bgcolor="#bbb"></td>
		</tr>
			<tr>
			<td colspan="2">　</td>
		</tr>
		<tr>
			<td align="center">비밀번호 찾기 질문</td>
			<td><select name="question">
					<c:forEach var="i" items="${que }">
						<option>${i }</option>
					</c:forEach>
			</select></td>
		</tr>
		<tr>

			<td align="center">비밀번호 찾기 답변</td>
			<td><input type="text" id="answer" name="answer" /></td>
		</tr>
					<tr>
			<td colspan="2">　</td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="button"
				onclick="join(${param.cate})" value="가입하기" /></td>
		</tr>
	</table>
</form>