<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h3 class="review">나의 리뷰</h3>
<table width="100%">
	<tr>
		<th class="review">리뷰번호</th>
		<th class="review">식당</th>
		<th class="review">맛점수</th>
		<th class="review">분위기점수</th>
		<th class="review">서비스점수</th>
		<th class="review">작성일</th>
		<th class="review">좋아요</th>
	</tr>
	<tr><td class="cover" colspan="7"></td></tr>
	<c:forEach var="cp" items="${myreview }">
		<tr>
			<td align="center">${cp.cno }</td>
			<td align="center" class="review"><a href="../restaurant/ResDetail?rid=${cp.rid }">${cp.rname }</a></td>
			<td align="center">${cp.tsrate }</td>
			<td align="center">${cp.mdrate }</td>
			<td align="center">${cp.svrate }</td>
			<td align="center">${cp.regdateStr }</td>
			<td align="center">${cp.likes }</td>
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