<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="../js/jquery-3.3.1.min.js"></script>
<script src="../scripts/admod.js"></script>


<form name="modr" action="adModifyReg" method="post">

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
					<c:set value="${dto.cate }" var="cate"></c:set>
			<c:set value="${dto.grade }" var="grade"></c:set>
		<tr>
			<td width="25%">회원 분류</td>
			<td>

			<c:choose>
			<c:when test="${cate==1 }">
				일반
			</c:when>
			<c:when test="${cate==2 }">
				파트너
			</c:when>
			</c:choose>
			</td>
		</tr>
		<tr>
			<td>회원 등급</td>
			<td>
			
			<c:choose>
			<c:when test="${cate==1 }">
				<c:choose>
					<c:when test="${grade==1 }">
						일반
					</c:when>
					<c:when test="${grade==2 }">
						두잇
					</c:when>
				</c:choose>
			</c:when>
			<c:when test="${cate==2 }">
				<c:choose>
					<c:when test="${grade==1 }">
						일반
					</c:when>
					<c:when test="${grade==9 }">
						가회원
					</c:when>
				</c:choose>
			</c:when>
			</c:choose>
			</td>
		</tr>

		<tr>
			<td>회원 번호</td>
			<td><input type="hidden" name="memNo" value="${dto.memNo }" />${dto.memNo }</td>
		</tr>
				<tr>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td colspan="2" bgcolor="#bbb"></td>
		</tr>
				<tr>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td>아이디</td>
			<td>${dto.id }</td>
		</tr>
				<tr>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td colspan="2" bgcolor="#bbb"></td>
		</tr>
				<tr>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" id="name" name="name" value="${dto.name }" /></td>
		</tr>
		<tr>
			<td>생년월일</td>
					<td><input type="text" id="yy" name="byear" maxlength="4"
				size="5" value="${barr[0]}" />년 <select id="month" name="bmonth">
				<c:set value="${barr[1] }" var="mon"></c:set>
					<c:forEach begin="1" end="12" var="i">
					<c:choose>
					<c:when test="${i==mon }">
					<option selected>${i }</option>
					</c:when>
					<c:otherwise>
						<option>${i }</option>
					</c:otherwise>
					</c:choose>
					</c:forEach>
			</select>월 <input type="text" id="dd" name="bdate" maxlength="2" size="3" value="${barr[2]}" />일</td>
		</tr>
		<tr>
			<td>성별</td>
			<td>
			<c:set value="${dto.gen }" var="gen"></c:set>
			<c:set value="남자" var="m"></c:set>
			<c:set value="여자" var="f"></c:set>
			<c:choose>
			<c:when test="${gen==m }">
			<input type="radio" name="gen" value="남자" checked/>남자
			<input type="radio" name="gen" value="여자" />여자
			 </c:when>
			 <c:when test="${gen==f }">
			<input type="radio" name="gen" value="남자" />남자
			<input type="radio" name="gen" value="여자" checked/>여자
			 </c:when>
			 </c:choose>
			 </td>
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
			<td colspan="2"></td>
		</tr>
		<tr>
			<td>좋아하는 음식</td>
			<td><select name="favMenu">
			<c:set value="${dto.favMenu }" var="fav"></c:set>
							<c:forEach var="i" items="${menu}">
							<c:choose>
								<c:when test="${i==fav }">
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
			<td>자주가는 지역</td>
			<td><select name="myLocal">
			<c:set value="${dto.myLocal }" var="loc"></c:set>
							<c:forEach var="i" items="${location }">
								<c:choose>
								<c:when test="${i==loc }">
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
			<td colspan="2"></td>
		</tr>
		<tr>
			<td colspan="2" bgcolor="#bbb"></td>
		</tr>
				<tr>
			<td colspan="2"></td>
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
			<td><input type="text" id="answer" name="answer" value="${dto.answer }" /></td>
		</tr>
				<tr>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td colspan="2" bgcolor="#bbb"></td>
		</tr>
				<tr>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td>포인트</td>
			<td><input type="text" name="point" value="${dto.point }" /></td>
		</tr>
		<tr>
			<td colspan="2">　</td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="button" value="수정" onclick="mod()" />
				<a href="uModify?mem=${dto.memNo }"><input type="button" value="초기화" /></a> 
				<a href="uDetail?mem=${dto.memNo }">돌아가기</a>
			</td>
		</tr>
	</table>
</form>