<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h3 class="review">잇딜 신청 내역</h3>
<table width="100%">
	<tr>    		
		<th class="review">판매제품</th>
		<th class="review">판매가(할인가)</th>
		<th class="review">수량</th>		
		<th class="review">판매기간</th>
		<th class="review">개시기간</th>
		<th class="review">처리</th>
		<th class="review">신청일</th>
	</tr>
	<tr><td colspan="8" class="cover"></td></tr>
	<c:forEach var="r" items="${reqdeal }">
	<tr>
		<td align="center">${r.cmenu }</td>
		<td align="center">${r.cprice }(${r.cdiscount }%)</td>
		<td align="center" width="80px">${r.sale} /${r.cvolume } </td>	
		<td>${r.pubstartStr }<br>
			${r.pubendStr }</td>
		<td>${r.cstartStr }<br>
			${r.cendStr }</td>
		<c:choose>
		<c:when test="${r.ccon==1 }">
		<td align="center" class="intro">수락</td>
		</c:when>
		<c:when test="${r.ccon==3 }">
		<td align="center" width="50px"><i>거절</i></td>
		</c:when>
		<c:otherwise>
		<td align="center">미승인</td>
		</c:otherwise>
		</c:choose>	
		<td align="center">${r.cpubStr }</td>
	</tr>
	<tr><td colspan="8" class="cover"></td></tr>
	</c:forEach>
	<tr>
		<td colspan="8" align="center" class="review">
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