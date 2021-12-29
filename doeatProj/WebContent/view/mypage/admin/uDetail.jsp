<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	function del() {
		var result = confirm("회원을 탈퇴시키겠습니까?");

		if (result) {

			udel.submit();
		}
	}
</script>
<h3 class="temptitle">회원 정보 상세</h3>
<form name="udel" action="deleteReg" method="post">
	<input type="hidden" name="cChk" value="${cChk }"> <input
		type="hidden" name="dChk" value="${dChk }"> <input
		type="hidden" name="memNo" value="${dto.memNo }"> <input
		type="hidden" name="pw" value="${dto.pw }">
	<table width="100%">
		<tr>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td class="intro" width="25%">회원 분류</td>
			<c:choose>
				<c:when test="${dto.cate==0 }">
					<td>관리자</td>
				</c:when>
				<c:when test="${dto.cate==2 }">
					<td>파트너회원</td>
				</c:when>
				<c:otherwise>
					<td>일반회원</td>
				</c:otherwise>
			</c:choose>
		</tr>
		<tr>
			<td class="intro" width="25%">회원 등급</td>
			<c:choose>
				<c:when test="${i.grade==9 }">
					<td>미승인</td>
				</c:when>
				<c:when test="${i.grade==8 }">
					<td>탈퇴신청</td>
				</c:when>
				<c:when test="${i.grade==2 }">
					<td>두잇</td>
				</c:when>
				<c:otherwise>
					<td>일반</td>
				</c:otherwise>
			</c:choose>
		</tr>
		<tr>
			<td class="intro" width="25%">회원 번호</td>
			<td>${dto.memNo }</td>
		</tr>
		<tr>
			<td colspan="2" bgcolor="#bbb"></td>
		</tr>
		<tr>
			<td class="intro" width="25%">아이디</td>
			<td>${dto.id }</td>
		</tr>
		<tr>
			<td colspan="2" bgcolor="#bbb"></td>
		</tr>
		<tr>
			<td class="intro" width="25%">이름</td>
			<td>${dto.name }</td>
		</tr>
		<tr>
			<td class="intro" width="25%">생년월일</td>
			<td>${dto.birth }</td>
		</tr>
		<tr>
			<td class="intro" width="25%">성별</td>
			<td>${dto.gen }</td>
		</tr>
		<tr>
			<td class="intro" width="25%">휴대전화</td>
			<td>${dto.phone }</td>
		</tr>
		<tr>
			<td class="intro" width="25%">이메일</td>
			<td>${dto.mail }</td>
		</tr>
		<tr>
			<td colspan="2" bgcolor="#bbb"></td>
		</tr>
		<c:choose>
			<c:when test="${dto.cate == 1 }">
				<tr>
					<td class="intro" width="25%">선호메뉴</td>
					<td>${dto.favMenu }</td>
				</tr>
				<tr>
					<td class="intro" width="25%">선호지역</td>
					<td>${dto.myLocal }</td>
				</tr>
				<tr>
					<td colspan="2" bgcolor="#bbb"></td>
				</tr>
			</c:when>
		</c:choose>
		<tr>
			<td class="intro">비밀번호 찾기 질문</td>
			<td>${dto.question }</td>
		</tr>
		<tr>
			<td class="intro">비밀번호 찾기 답변</td>
			<td>${dto.answer }</td>
		</tr>
		<tr>
			<td colspan="2" bgcolor="#bbb"></td>
		</tr>
		<tr>
			<td class="intro">포인트</td>
			<td>${dto.point }points</td>
		</tr>
		<tr>
			<td colspan="2"></td>
		</tr>
		<c:choose>
			<c:when test="${dto.cate == 2 }">
				<tr>
					<td colspan="2" bgcolor="#bbb"></td>
				</tr>
				<tr>
					<td colspan="2"></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><h2>
							<font size="4" color="#FF4D00">잇딜</font>
						</h2></td>
				</tr>
				<tr>
					<td colspan="2" bgcolor="#bbb"></td>
				</tr>
				<tr>
					<td colspan="2"></td>
				</tr>

				<tr>
					<td colspan="2">파트너 잇딜 상태: ${dChk}<br> 파트너 이용권 상태:
						${cChk}
					</td>
				</tr>
			</c:when>
		</c:choose>
		<tr>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="button" value="탈퇴"
				onclick="del()" /> <input type="button" value="수정"
				onclick="location.href='uModify?mem=${dto.memNo }'" /> <c:choose>
					<c:when test="${dto.cate == 1 }">
						<a class="temptitle" href="user?page=${nowPage }">회원 목록으로</a>
					</c:when>
					<c:when test="${dto.cate == 2 }">
						<a class="temptitle" href="partner?page=${gennowPage }">파트너 회원
							목록으로</a>
					</c:when>
				</c:choose></td>
		</tr>
	</table>
</form>


