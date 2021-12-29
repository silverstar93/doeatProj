<%@page import="db_p.UserDTO"%>
<%@page import="db_p.PaymentDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3 class="review">나의 판매 금액</h3>
<table width="100%">
	<tr>
		<th class="review">판매일</th>
		<th class="review">총 판매금액</th>
		<th class="review">수수료</th>
		<th class="review">합계</th>
		
	</tr>
	<tr><td colspan="4" class="cover"></td></tr>
<c:forEach var = "i" items="${mdc }" varStatus="no">
	
	
	<tr>
		<td align="center">${i.pubdff }</td>
		<td align="center">${i.pay }</td>
		<td align="center">${i.pay*0.05}</td>
		<td align="center">${i.pay-i.pay*0.05}</td>
	</tr>
	<tr><td colspan="4" class="cover"></td></tr>
</c:forEach>
	<tr>
		<td colspan="4" align="center" class="review">
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