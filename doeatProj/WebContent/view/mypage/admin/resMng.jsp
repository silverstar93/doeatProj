<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3 class="review">등록 식당 관리</h3>
<table width="105%">
	<tr>
		<th class="review">식당ID</th> <!-- 0이 리턴되면 비회원식당 -->
		<th class="review">사업장명</th>
		<th class="review">사업자</th>
		<th class="review" colspan="2">주소</th>
		<th class="review">전화번호</th>
		<th class="review">파트너구분</th>
	</tr>
	<tr><td colspan="7" class="cover"></td></tr>
	<c:forEach var="cp" items="${list }">
		<tr>
			<td align="center">${cp.rid }</td>
			<td align="center" class="review"><a href="../restaurant/ResDetail?rid=${cp.rid }&nowPage=${nowPage}">${cp.rname }</a></td>
			<td align="center">${cp.rowner }</td>
			<td colspan="2"><font size="2"><i>${cp.raddr }</i></font></td>
			<td align="center">${cp.rtel }</td>
			<c:choose>			
			<c:when test="${cp.memNo==0 }">
			<td align="center">비회원</td>
			</c:when>			
			<c:otherwise>
			<td align="center">${cp.memNo }</td>
			</c:otherwise>			
			</c:choose>
		</tr>
		<tr><td colspan="7" class="cover"></td></tr>
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