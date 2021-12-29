<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table width="100%">
	<tr>
		<th class="review">식당</th>
		<th class="review">작성자</th>
		<th class="review">접수내용(접수일)</th>
		<th class="review">처리내용(처리일)</th>
		<th></th>
	</tr>
	<tr><td colspan="7" class="cover"></td></tr>
	<c:forEach var="cp" items="${corv }">
		<tr>
			<td align="center">${cp.rname }(${cp.rid })</td>
			<td align="center">${cp.memNo }</td>
			<c:choose>
			<c:when test="${cp.crs=='terror' }">
			<td align="center">악의적인 별점<br>(${cp.reqdateStr })</td>
			</c:when>
			<c:when test="${cp.crs=='hatred' }">
			<td align="center">혐오감을 조성<br>(${cp.reqdateStr })</td>
			</c:when>
			<c:when test="${cp.crs=='insult' }">
			<td align="center">인격모독<br>(${cp.reqdateStr })</td>
			</c:when>
			<c:when test="${cp.crs=='improper' }">
			<td align="center">그 외 부적절한 언행<br>(${cp.reqdateStr })</td>
			</c:when>
			</c:choose>
			<c:choose>
			<c:when test="${cp.chd=='yet' }">
				<td align="center"><a class="review" href="reviewDetail?cno=${cp.cno }&conum=${cp.conum }&nowPage=${nowPage }">처리하기</a></td>	
			</c:when>
			<c:otherwise>
				<td align="center">${cp.chd }<br>(${cp.prcdate })</td>
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