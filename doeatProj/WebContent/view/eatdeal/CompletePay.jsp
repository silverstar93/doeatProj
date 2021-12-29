<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<table width="90%" >
	<tr>
		<td colspan="2"></td>
	</tr>
	<tr>   
		<td colspan="2" bgcolor="#bbb"></td>
	</tr>
	<tr>
		<td class="review" colspan="2"><font size="5"><b>결제 내역</b></font></td>
	</tr>
	<tr>
		<td colspan="2" bgcolor="#bbb" height="3px"></td>
	</tr>
	<tr>
		<td class="review" colspan="2"><b>기본 정보</b></td>
	</tr>
	<tr>
		<td colspan="2" bgcolor="#bbb"></td>
	</tr>
	<c:forEach var="i" items="${cto }" varStatus="no">
	<tr>
	<th>쿠폰 코드</th><td>${i.ccode }</td>
	</tr>
	</c:forEach>
	<tr>
		<td class="review">쿠폰 정보</td>
		<td> ${res.cmenu }</td>
	</tr>
	<tr>
		<td class="review">쿠폰 금액</td>
		<td> ${pto.pay }원</td>
	</tr>
	<tr>
		<td class="review">결제일</td>
		<td> ${pto.pubd }</td>
	</tr>
	<tr>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="button" value="잇딜로 돌아가기" onclick="location.href='List2'"/></td>
	</tr>

</table> 