
<%@page import="javax.swing.RepaintManager"%>
<%@page import="db_p.PaymentDTO"%>
<%@page import="db_p.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
function refund(pno){
	var chk = confirm("환불하시겠어요?");
	if(chk == true){
		location.href="UserRefund?nowPage=${rfnowPage}&pno="+pno;
	}
}
</script>
<div>
<h3 class="review">쿠폰 구매 내역</h3>
<table width="100%">
	<tr>
		<th class="review">상품명</th>
		<th class="review">쿠폰 코드</th>
		<th class="review">유효 기간</th>
		<th class="review" colspan="2">상태</th>
		<th></th>
		<!-- <td>환불</td> -->
	</tr>
	<tr><td class="cover" colspan="5"></td></tr>
	<c:forEach var="i" items="${mycoupon }">

		<tr>
			<td align="center">${i.cmenu }</td>
			<td align="center">${i.ccode }</td>
			<td align="center">${i.cstartStr }~ ${i.cendStr }</td>
			<%--</tr>
 <c:set value="${i.ccode }"  var="ii"></c:set>
<c:forEach var="j" items="${menu }">
<c:set value="${j.ccode }"  var="jj"></c:set>
<c:if test="${jj==ii }">
<tr>
<td>${j.cmenu }</td>
	</tr>
		</c:if>

	</c:forEach> 
	<tr> --%>
			<td align="right" class="intro"><c:choose>
					<c:when test="${i.cond==2 }">미사용</c:when>
					<c:when test="${i.cond==1 }">사용</c:when>
					<c:when test="${i.cond==5 }">환불</c:when>
					<c:otherwise>x</c:otherwise>
				</c:choose></td>

				<td><c:if test="${i.cond==2 }">
					<input type="button" value="${i.pno } 환불" onclick="refund(${i.pno })">
				</c:if></td>
		</tr>
	<tr><td class="cover" colspan="5"></td></tr>
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
					<a href="?page=${i }&rfpage=${rfnowPage}">${i }</a>
				</c:otherwise>
			</c:choose>				
			</c:forEach>				
			<c:if test="${endPage<totalPage }">
				<a href="?page=${endPage+1 }">다음</a>
			</c:if>
		</td>
	</tr>
</table>

<br><br><br><br><br>

<h3 class="review">쿠폰 환불 내역</h3>
<table width="100%">
	<tr>
		<th class="review">쿠폰 코드</th>
		<th class="review">유효 기간</th>
<!-- 		<td>환불일</td> -->
		<!-- <td>환불</td> -->
	</tr>
	<tr><td class="cover" colspan="3"></td></tr>
	<c:forEach var="i" items="${ref }">

		<tr>
			<td>${i.ccode }</td>
			<td>${i.cstartStr }~ ${i.cendStr }</td>

<%-- 			<td>${i.usedate}</td> --%>

		</tr>
	<tr><td class="cover" colspan="3"></td></tr>
	</c:forEach>
		<tr>
		<td colspan="2" align="center" class="review">
			<c:if test="${rfstartPage>1 }">
				<a href="?rfpage=${rfstartPage-1 }">이전</a>
			</c:if>
			<c:forEach begin = "${rfstartPage }" end="${rfendPage }" var="i">
			<c:choose>
				<c:when test="${i==rfnowPage }">
					${i }
				</c:when>
				<c:otherwise>
					<a href="?rfpage=${i }&page=${nowPage }">${i }</a>
				</c:otherwise>
			</c:choose>				
			</c:forEach>				
			<c:if test="${rfendPage<rftotalPage }">
				<a href="?rfpage=${rfendPage+1 }">다음</a>
			</c:if>
		</td>
	</tr>
</table>
</div>
- 

