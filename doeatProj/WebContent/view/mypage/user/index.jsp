<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:useBean id="mm" class="db_p.UserDTO" scope="session" />
<jsp:setProperty property="*" name="mm" />


<script>
	function drop() {
		var result = confirm("탈퇴하시겠습니까?");

		if (result) {
			location.href = "delete";
		}
	}
</script>

<table width="100%">
	<tr>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td colspan="2" bgcolor="#bbb"></td>
	</tr>
	<tr>
		<td class="review" colspan="2"><font size="5"><b>프로필</b></font></td>
	</tr>
	<tr>
		<td colspan="2" bgcolor="#bbb" height="3px"></td>
	</tr>
	<tr>
		<td class="review" colspan="2"><b>기본 정보</b></td>
	</tr>
	<tr>
		<td colspan="2" bgcolor="#bbb"></td>
	</tr>
	<tr>
		<td width="25%" class="review">이름</td>
		<td>${mm.name }</td>
	</tr>
	<tr>
		<td class="review">생일</td>
		<td>${mm.birth }</td>
	</tr>
	<tr>
		<td class="review">성별</td>
		<td>${mm.gen }</td>
	</tr>
	<tr>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td colspan="2" bgcolor="#bbb"></td>
	</tr>
	<tr>
		<td class="review" colspan="2"><b>연락처 정보</b></td>
	</tr>
	<tr>
		<td colspan="2" bgcolor="#bbb"></td>
	</tr>
	<tr>
		<td class="review">이메일</td>
		<td>${mm.mail }</td>
	</tr>
	<tr>
		<td class="review">휴대전화</td>
		<td>${mm.phone }</td>
	</tr>
	<tr>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td colspan="2" bgcolor="#bbb"></td>
	</tr>
	<tr>
		<td class="review" colspan="2"><b>선호도</b></td>
	</tr>
	<tr>
		<td colspan="2" bgcolor="#bbb"></td>
	</tr>
	<tr>
		<td class="review">선호 메뉴</td>
		<td>${mm.favMenu }</td>
	</tr>
	<tr>
		<td class="review">선호 지역</td>
		<td>${mm.myLocal }</td>
	</tr>
	<tr>
		<td colspan="2" align="right"><input type="button" value="탈퇴하기" onclick="drop()" />
		<input type="button" value="수정하기" onclick="location.href='uModify'"/></td>
	</tr>
</table>