<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3 class="review">나의 신고 내역</h3>
<table width="100%">
	<tr>
		<th class="review">접수번호</th>
		<th class="review">작성자</th>		
		<th class="review">신고내용</th>
		<th class="review">접수일</th>
		<th class="review">처리항목</th>
	</tr>
	<tr><td colspan="5" class="cover"></td></tr>
	<c:forEach var="cp" items="${myreport }">
	<tr>
		<td align="center">${cp.conum }</td>
		<td align="center">${cp.memNo }</td>
		<c:choose>
		<c:when test="${cp.crs=='hatred' }">
		<td>혐오감을 조성하는 사진 또는 리뷰</td>
		</c:when>
		<c:when test="${cp.crs=='insult' }">
		<td>인격 모독적인 단어 사용</td>
		</c:when>
		<c:when test="${cp.crs=='terror' }">
		<td>악의적인 별점 부여</td>
		</c:when>
		<c:otherwise>
		<td>그 외 부적절한 언행</td>
		</c:otherwise>
		</c:choose>
		<td align="center">${cp.reqdateStr }</td>
		<c:choose>
		<c:when test="${cp.chd=='yet' }">
		<td align="center">미처리</td>
		</c:when>
		<c:otherwise>
		<td align="center">${cp.chd }(${cp.prcdateStr })</td>
		</c:otherwise>
		</c:choose>
	</tr>
	<tr><td colspan="5" class="cover"></td></tr>
	</c:forEach>
	<tr>
		<td colspan="5" align="center" class="review">
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