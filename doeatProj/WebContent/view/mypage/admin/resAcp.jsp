<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3 class="review">파트너 가입 신청 확인</h3>
<table width="100%">
	<tr>
		<th class="review">사업자등록번호</th>
		<th class="review">상호명</th>
		<th align="center" class="review">사업자</th>
		<th class="review" colspan="2">주소</th>
		<th align="center" class="review">전화번호</th>
		<th></th>
	</tr>
	<tr><td class="cover" colspan="7"></td></tr>
	<c:forEach var="cp" items="${waitlist }">
		<tr>
			<td align="center">${cp.rno }</td>
			<td align="center">${cp.rname }</td>
			<td align="center">${cp.rowner }</td>
			<td colspan="2">${cp.raddr }</td>
			<td align="center">${cp.rtel }</td>
			<th align="center" class="review">
				<a href="resDetail?memNo=${cp.memNo }&nowPage=${nowPage}">등록심사</a>
			</th>
		</tr>
		<tr><td class="cover" colspan="7"></td></tr>
	</c:forEach>
		<tr>
		<td colspan="7" align="center" class="review">
			<c:if test="${startPage>1 }">
				<a href="?page=${startPage-1 }">이전</a>
			</c:if>
			<c:forEach begin = "${startPage }" end="${endPage }" var="i">
			<c:choose>
				<c:when test="${i==nowPage }">
					${i }
				</c:when>
				<c:otherwise>
					<a href="?page=${i }">${i }</a>
				</c:otherwise>
			</c:choose>				
			</c:forEach>				
			<c:if test="${endPage<totalPage }">
				<a href="?page=${endPage+1 }">다음</a>
			</c:if>
		</td>
	</tr>
</table>