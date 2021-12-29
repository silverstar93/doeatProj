<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="../../../js/jquery-3.3.1.min.js"></script>
<h3 class="review">잇딜 관리</h3>
<table width="100%">
	<tr>
		<th class="review">딜 ID</th>
		<th class="review">식당 ID</th>
		<th class="review">메뉴</th>
		<th class="review">판매가</th>
		<th class="review">판매현황</th>
		<th class="review">작성일</th>
		<th class="review">상태</th>
	</tr>
	<tr><td colspan="7" class="cover"></td></tr>
	<c:forEach var="dto" items="${chk }" varStatus="no">
		<tr>
			<th><a class="review" href="AdminDetail?cno=${dto.cno}&page=${nowPage}">${dto.cno }</a></th>
			<th><a class="review" href="../restaurant/ResDetail?rid=${dto.rid }">${dto.rid }</a></th>
			<td>${dto.cmenu }</td>
			<td>${dto.cprice }</td>			
			<td>${dto.sale} / ${dto.cvolume }</td>
			<td>${dto.cpubStr }</td>
			<c:choose>
				<c:when test="${dto.ccon == 1 }">
					<td>승인</td>
				</c:when>
				<c:when test="${dto.ccon == 2 }">
					<td>미승인</td>
				</c:when>
				<c:when test="${dto.ccon == 3 }">
					<td>거절</td>
				</c:when>
			</c:choose>
		</tr>
		<tr><td colspan="7" class="cover"></td></tr>
	</c:forEach>
		<tr>
			<td colspan="7" align="center" class="review">
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