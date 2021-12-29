<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<script src="../js/jquery-3.3.1.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="../scripts/addrchk.js"></script>
<script src="../scripts/rno.js"></script>

<form name="frm" action="resRegister" method="post" enctype="multipart/form-data">
	<table width="85%">
		<tr>
			<td colspan="4" align="center" class="review"><h2>식당 등록 메인</h2></td>
		</tr>
		<tr>
			<td colspan="4" align="center" class="review">식당 기본 정보</td>
		</tr>
		<tr>
			<td class="review">사업자번호<br><font size="2">(9자리 숫자)</font></td>
			<td>
				<input type="text" name="rno" maxlength="9" id="rno" />
				<input type="button" id="dck" value="중복확인" onclick="doubleChk()"/>
			</td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td class="review">상호명</td>
			<td><input type="text" id="rname" name="rname"/></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td class="review">사업자</td>
			<td><input type="text" id="rowner" name="rowner"/></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td class="review">주소</td>
			<td colspan="3">
				<input type="text" name="raddr" id="sample4_postcode" placeholder="우편번호">
				<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
				<input type="text" name="raddr" id="sample4_roadAddress" placeholder="도로명주소">
				<span id="guide" style="color:#999;display:none"></span>
				<input type="text" name="raddr" id="sample4_detailAddress" placeholder="상세주소">
			</td>
		</tr>
		<tr>
			<td class="review">전화번호</td>
			<td colspan="3">
				<input type="text" id="rtel1" name="rtel" size="8" maxlength="3"/>-
				<input type="text" id="rtel2" name="rtel" size="12" maxlength="4"/>-
				<input type="text" id="rtel3" name="rtel" size="12" maxlength="4"/>
			</td>
		</tr>
		<tr><td colspan="4" class="cover"></td></tr>
		<tr>
			<td colspan="4" align="center" class="review">식당 페이지 노출 정보</td>
		</tr>
		<tr>
			<td class="review">위치(추천 조건)</td>
			<td>
				<select name="rloc1">
				<c:forTokens var="ee" items="서울,경기,경남,경북,제주,전남,전북,광주,강원,대전,세종,충북,충남,부산,대구" delims=",">
					<option value="${ee }">${ee }</option>	
				</c:forTokens>
				</select>
				<input type="text" id="rloc2" name="rloc2"/>
			</td>			
		</tr>
		<tr>
			<td class="review">영업시간</td>
			<td colspan="3">
				<select name="rhour1">
				<c:forEach var="i" begin="0" end="24" step="1">
					<option value="${i }:00">${i }:00</option>
				</c:forEach>
				</select>
					-
				<select name="rhour2">
				<c:forEach var="i" begin="0" end="24" step="1">
					<option value="${i }:00">${i }:00</option>
				</c:forEach>
				</select>
			</td>
		<tr>
			<td class="review">메뉴</td>
			<td colspan="3">
				대분류:
				<select name="rmenu1">
						<option value="한식">한식</option>
						<option value="양식">양식</option>
						<option value="중식">중식</option>
						<option value="일식">일식</option>
						<option value="기타">기타</option>
				</select>
				소분류:
				<input type="text" id="rmenu2" name="rmenu2"/>
			</td>
		</tr>
		<tr>
			<td class="review">가격대</td>
			<td colspan="3">
			<input type="text" id="rprice1" name="rprice1" maxlength="6"/> - 
			<input type="text" id="rprice2" name="rprice2" maxlength="6"/>
			</td>
		</tr>
		<tr>
			<td class="review">주요메뉴</td>
			<td colspan="3"><input type="text" id="rmain1" name="rmain1"/>&nbsp;&nbsp;
			<input type="text" id="rmain2" name="rmain2"/>&nbsp;&nbsp;
			<input type="text" id="rmain3" name="rmain3"/></td>
		</tr>
		<tr>
			<td class="review">사진</td>
			<td><input type="file" name="pic"></td>
		</tr>
		<tr><td colspan="4" class="cover"></td></tr>
		<tr>
			<td colspan="3" align="right"><input type="button" value="식당등록" onclick="regchecked()"/></td>
			<td align="right"><a class="review" href="res">식당 관리 페이지</a></td>	
		</tr>
	</table>
</form>