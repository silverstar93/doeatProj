<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="../js/jquery-3.3.1.min.js"></script>
<script src="../scripts/umod.js"></script>


<form name="modr" action="modify" method="post">
	<h3 class="temptitle">회원 정보 수정</h3>
	<table width="100%">
			<tr>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td colspan="2" bgcolor="#bbb" height="3px"></td>
		</tr>
		<tr>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td>아이디</td>
			<td>${dto.id }</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="button" name="pw" value="비밀번호 변경하기" onclick="pwch()" /></td>
		</tr>
		<tr>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td colspan="2" bgcolor="#bbb"></td>
		</tr>
		<tr>
			<td colspan="2">프로필</td>
		</tr>
		<tr>
			<td colspan="2" bgcolor="#bbb"></td>
		</tr>

		<tr>
			<td width="25%">이름</td>
			<td>${dto.name }</td>
		</tr>
		<tr>
			<td>생년월일</td>
			<td>${dto.birth }</td>
		</tr>
		<tr>
			<td>성별</td>
			<td>${dto.gen }</td>
		</tr>
		<tr>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td colspan="2" bgcolor="#bbb"></td>
		</tr>
		<tr>
			<td colspan="2">연락처 정보</td>
		</tr>
		<tr>
			<td colspan="2" bgcolor="#bbb"></td>
		</tr>
<tr>
			<td>휴대전화</td>
			<c:set value="${parr[0] }" var="ph"></c:set>
				<td><select name="tel1" id="tel1">
					<c:forEach var="i" items="${tel}">
							<c:choose>
					<c:when test="${i==ph }">
					<option selected>${i }</option>
					</c:when>
					<c:otherwise>
						<option>${i }</option>
					</c:otherwise>
					</c:choose>
					</c:forEach>
			</select> - <input name="tel2" id="tel2" type="text" maxlength="4" size="5" value="${parr[1] }"/>
				- <input name="tel3" id="tel3" type="text" maxlength="4" size="5" value="${parr[2] }"/></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td>${earr[0] } @ ${earr[1] } </td>
		</tr>
		<tr>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td colspan="2" bgcolor="#bbb"></td>
		</tr>
<tr>
			<td>비밀번호 찾기 질문</td>
						<td><select name="question">
						<c:set value="${dto.question }" var="qq"></c:set>
					<c:forEach var="i" items="${que }">
						<c:choose>
								<c:when test="${i==qq }">
					<option selected>${i }</option>
					</c:when>
					<c:otherwise>
						<option>${i }</option>
					</c:otherwise>
					</c:choose>
					</c:forEach>
			</select></td>
		</tr>
		<tr>
		<td>비밀번호 찾기 답변</td>
		<td><input type="text" id="answer" name="answer" value="${dto.answer }"/></td>
	</tr>
		<tr>
			<td colspan="2" align="right"><input type="button" onclick="mod()" value="수정" />
				<a href="uModify"><input type="button" value="초기화" /></a> <a href="index">돌아가기</a>
			</td>
		</tr>
	</table>
</form>