<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="http://localhost:8080/doeatProj/js/jquery-3.3.1.min.js"></script>
<script src="../scripts/search.js"></script>


<h3 class="review">잇딜 판매 내역</h3>
<jsp:useBean id="now" class="java.util.Date"/>
<form name="codeSearch" action="salesRep" method="post">
	<input type="hidden" name="menu" value="salesRep">
	<table width="100%">
		<tr align="center">
			<td class="review" colspan="7">
				쿠폰번호 : <input type="text" name="cnum" id ="code"/> <input type="button" value="검색" onclick="codeChk()"/>
			</td>
		</tr>
		<tr>
			<th class="review">쿠폰번호</th>
			<th class="review">판매번호</th>
			<th class="review">구매자</th>
			<th class="review">상품금액</th>
			<th class="review">사용기간</th>
			<th class="review">사용여부</th>
			<th class="review">판매처리</th>
		</tr>
		<tr><td colspan="7" class="cover"></td></tr>
		<c:forEach var="s" items="${sales }">
			<tr>
				<td align="center">${s.ccode }</td>
				<td align="center">${s.pno }</td>
				<td align="center">${s.memNo }</td>
				<td align="center">${s.price }</td>
				<td>${s.cstartStr }<br>-${s.cendStr }</td>
			
				<fmt:parseDate value="${s.cstartStr }" pattern="yyyy-MM-dd" var="gobefore"/>
				<fmt:formatDate value="${gobefore }" pattern="yyyyMMdd" var="b4cfrm"/>
				<fmt:formatDate value="${now }" pattern="yyyyMMdd" var="stdcfrm"/>
				<c:set var="chkb4" value="${b4cfrm-stdcfrm }"/> 

				<c:choose>
					<c:when test="${s.cond== 1 }">
						<td align="center">사용(${s.usedateStr })</td>
					</c:when>
					<c:when test="${s.cond== 5 }">
						<td align="center">환불</td>
					</c:when>
					<c:when test="${chkb4 > 0 }">
						<td align="center">미개시</td>
					</c:when>
					<c:otherwise>
						<td align="center">미사용</td>
						<td><a href="comReg?ccode=${s.ccode }&nowPage=${nowPage }">
						<input type="button" value="사용" /></a></td>
					</c:otherwise>
				</c:choose>
			</tr>
			<tr><td colspan="7" class="cover"></td></tr>
		</c:forEach>
		<tr>
			<td colspan="7" align="center" class="review">

			 <c:if test="${startPage != null }">
			 
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
			 
			 </c:if>
			
			<c:if test="${startPage == null }">
				<a href="salesRep">목록으로</a>
			</c:if>
			 
			
				
			</td>
		</tr>
	</table>
</form>