<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h3 class="review">이용권 관리</h3>
<table width="100%">
	<tr>
		<th class="review">쿠폰번호</th>
		<th class="review">판매번호</th>
		<th class="review">구매자</th>
		<th class="review">판매자(상호명)</th>
		<th class="review">상품금액</th>
		<th class="review">사용기간</th>
		<th class="review">사용여부</th>
		<th></th>
	</tr>
	<tr><td colspan="8" class="cover"></td></tr>
	<c:forEach var="s" items="${admcoup }">
		<tr>
			<td align="center">${s.ccode }</td>
			<td align="center">${s.pno }</td>
			<td align="center">${s.id }</td>
			<td align="center">${s.rname }</td>
			<td align="center">${s.price }</td>
			<td align="center">${s.cstartStr }<br>-${s.cendStr }
			</td>
			<c:choose>
				<c:when test="${s.cond== 1 }">
					<td align="center">사용(${s.usedateStr }</td>
					<td></td>
				</c:when>
				<c:when test="${s.cond== 5 }">
					<td align="center">환불</td>
					<td></td>
				</c:when>
				<c:otherwise>
					<td align="center">미사용</td>
					<td align="center"><a href="">딜로 이동</a></td>
				</c:otherwise>
			</c:choose>
		</tr>
		<tr><td colspan="8" class="cover"></td></tr>
	</c:forEach>
		<tr>
			<td colspan="8" align="center" class="review">
				<c:if test="${startPage>1 }">
					<a href="?rid=${param.rid }&page=${startPage-1 }">이전</a>
				</c:if>
				<c:forEach begin = "${startPage }" end="${endPage }" var="i">
				<c:choose>
					<c:when test="${i==nowPage }">
						${i }
					</c:when>
					<c:otherwise>
						<a href="?rid=${param.rid }&page=${i }">${i }</a>
					</c:otherwise>
				</c:choose>				
				</c:forEach>				
				<c:if test="${endPage<totalPage }">
					<a href="?rid=${param.rid }&page=${endPage+1 }">다음</a>
				</c:if>
			</td>
		</tr>
</table>