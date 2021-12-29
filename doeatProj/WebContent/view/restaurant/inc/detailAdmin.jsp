<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" rel="stylesheet">
<script src="../js/jquery-3.3.1.min.js"></script>
<script src="../scripts/worno.js"></script>
<c:choose>
<c:when test="${param.nowPage!=null }">
<form name="modfrm" action="ResAdmModifyReg?nowPage=${param.nowPage }" method="post">
<input type="hidden" name="rid" value="${detail.rid }"/>
<table id="info" width="100%">
	<tr>
		<td class="title">상호명</td>
		<td width="500px" class="title"><input type="text" id="rname" name="rname" value="${detail.rname }"/></td>
		<td width="300px" align="right"><a class="title" href="../mypage/resMng?page=${param.nowPage }">식당 관리 페이지로 이동</a></td>
	</tr>
	<tr>
		<td class="title">회원번호</td>
		<td colspan="2">
			${detail.memNo }
			<input type="hidden" name="memNo" value="${detail.memNo }"/>
		</td>
	</tr>
	<tr>
		<td class="title">사업자등록번호</td>
		<td colspan="2">
			<input type="text" name="rno" value="${detail.rno }" maxlength="9"/>
		</td>
	</tr>
	<tr>
		<td class="title">사업자</td>
		<td colspan="2">		
			<input type="text" id="rowner" name="rowner" value="${detail.rowner }"/>
		</td>
	</tr>
	<tr>
		<td class="title">주소</td>
		<td colspan="2">
			<input type="text" id="raddr" name="raddr" value="${detail.raddr }"/>
		</td>
	</tr>
	<tr>
		<td class="title">전화번호</td>
		<td colspan="2">
			<input type="text" id="rtel" name="rtel" value="${detail.rtel }"/>
		</td>
	</tr>
	<tr>
		<td class="title">위치</td>
		<td colspan="2">
			<select name="rloc1">
				<c:forTokens var="ee" items="서울시,경기도,경상남도,경상북도,제주시,전라남도,전라북도,광주시,강원도,대전시,세종시" delims=",">
					<option value="${ee }">${ee }</option>	
				</c:forTokens>
			</select>
			<input type="text" id="rloc2" name="rloc2" value="${detail.rloc2 }"/>
		</td>
	</tr>
	<tr>
		<td class="title">영업시간</td>
		<td colspan="2">
			<input type="text" id="rhour" name="rhour" value="${detail.rhour }"/>
		</td>
	<tr>
		<td class="title">메뉴</td>
		<td colspan="2">
			대분류:
			<select name="rmenu1">
						<option value="한식">한식</option>
						<option value="양식">양식</option>
						<option value="중식">중식</option>
						<option value="일식">일식</option>
						<option value="기타">기타</option>
			</select>
			소분류:<input type="text" id="rmenu2" name="rmenu2" value="${detail.rmenu2 }"/>
		</td>
	</tr>
	<tr>
		<td class="title">가격대</td>
		<td colspan="2">
			<input type="text" id="rprice1" name="rprice1" value="${detail.rprice1 }"/>-
			<input type="text" id="rprice2" name="rprice2" value="${detail.rprice2 }"/>
		</td>
		</tr>
		<tr>
			<td class="title">주요메뉴</td>
			<td colspan="2">
				<input type="text" id="rmain1" name="rmain1" value="${detail.rmain1 }"/>
				<input type="text" id="rmain2" name="rmain2" value="${detail.rmain2 }"/>
				<input type="text" id="rmain3" name="rmain3" value="${detail.rmain3 }"/>
			</td>
		</tr>
		<tr>
			<td></td>
			<td align="center" colspan="2">
				<input type="button" value="수정 완료" onclick="modchecked()"/>
				<input type="reset" value="기본값 다시 불러오기"/>
				<input type="button" value="식당 삭제" onclick="location.href='ResAdmDelReg?rid=${detail.rid }&memNo=${detail.memNo }&rno=${detail.rno}&nowPage=${param.nowPage }'"/>
			</td>
		</tr>	
	</table>
</form>
</c:when>
<c:otherwise>
<form name="modfrm" action="ResAdmModifyReg" method="post">
<input type="hidden" name="rid" value="${detail.rid }"/>
<table id="info" width="100%">
	<tr>
		<td class="title">상호명</td>
		<td width="500px" class="title"><input type="text" id="rname" name="rname" value="${detail.rname }"/></td>
		<td width="300px" align="right"><a class="title" href="../mypage/resMng">식당 관리 페이지로 이동</a></td>
	</tr>
	<tr>
		<td class="title">회원번호</td>
		<td colspan="2">
			${detail.memNo }
			<input type="hidden" name="memNo" value="${detail.memNo }"/>
		</td>
	</tr>
	<tr>
		<td class="title">사업자등록번호</td>
		<td colspan="2">
			<input type="text" name="rno" value="${detail.rno }"/>
		</td>
	</tr>
	<tr>
		<td class="title">사업자</td>
		<td colspan="2">		
			<input type="text" id="rowner" name="rowner" value="${detail.rowner }"/>
		</td>
	</tr>
	<tr>
		<td class="title">주소</td>
		<td colspan="2">
			<input type="text" id="raddr" name="raddr" value="${detail.raddr }"/>
		</td>
	</tr>
	<tr>
		<td class="title">전화번호</td>
		<td colspan="2">
			<input type="text" id="rtel" name="rtel" value="${detail.rtel }"/>
		</td>
	</tr>
	<tr>
		<td class="title">위치</td>
		<td colspan="2">
			<select name="rloc1">
				<c:forTokens var="ee" items="서울시,경기도,경상남도,경상북도,제주시,전라남도,전라북도,광주시,강원도,대전시,세종시" delims=",">
					<option value="${ee }">${ee }</option>	
				</c:forTokens>
			</select>
			<input type="text" id="rloc2" name="rloc2" value="${detail.rloc2 }"/>
		</td>
	</tr>
	<tr>
		<td class="title">영업시간</td>
		<td colspan="2">
			<input type="text" id="rhour" name="rhour" value="${detail.rhour }"/>
		</td>
	<tr>
		<td class="title">메뉴</td>
		<td colspan="2">
			대분류:
			<select name="rmenu1">
						<option value="한식">한식</option>
						<option value="양식">양식</option>
						<option value="중식">중식</option>
						<option value="일식">일식</option>
						<option value="기타">기타</option>
			</select>
			소분류:<input type="text" id="rmenu2" name="rmenu2" value="${detail.rmenu2 }"/>
		</td>
	</tr>
	<tr>
		<td class="title">가격대</td>
		<td colspan="2">
			<input type="text" name="rprice1" value="${detail.rprice1 }"/>-
			<input type="text" name="rprice2" value="${detail.rprice2 }"/>
		</td>
		</tr>
		<tr>
			<td class="title">주요메뉴</td>
			<td colspan="2">
				<input type="text" id="rmain1" name="rmain1" value="${detail.rmain1 }"/>
				<input type="text" id="rmain2" name="rmain2" value="${detail.rmain2 }"/>
				<input type="text" id="rmain3" name="rmain3" value="${detail.rmain3 }"/>
			</td>
		</tr>
		<tr>
			<td></td>
			<td align="center" colspan="2">
				<input type="button" value="수정 완료" onclick="modchecked()"/>
				<input type="reset" value="기본값 다시 불러오기"/>
				<input type="button" value="식당 삭제" onclick="location.href='ResAdmDelReg?rid=${detail.rid }&memNo=${detail.memNo }&rno=${detail.rno}&nowPage=1'"/>
			</td>
		</tr>	
	</table>
</form>
</c:otherwise>
</c:choose>