<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<div style="height: 500px ;padding-left: 30px ;padding-top: 50px">
<form  action="PointCal" method="post">

<input type="hidden" name="point" value="${point}">
<input type="hidden" name="cno" value="${data.cno}">
<table width="90%" >
	<tr>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td colspan="2" bgcolor="#bbb"></td>
	</tr>
	<tr>
		<td class="review" colspan="2"><font size="5"><b>결제 정보</b></font></td>
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
	<tr>
		<td width="25%" class="review">사용 가능한 두잇 포인트</td>
		<td>${point}원</td>
	</tr>
	<tr>
		<td class="review">주문 금액</td>
		<td> ${data.cprice }원</td>
	</tr>
	<tr>
		<td class="review">결제 후 포인트</td>
		<td> ${point-data.cprice }원</td>
	</tr>
	<tr>
		<td colspan="2"></td>
	</tr>
	
	<c:choose>
	<c:when test="${(point-data.cprice)==0 or (point-data.cprice)>0}">
	<tr>
		<td colspan="2" align="center"><input type="submit" value="결제하기"/></td>
	</tr>
	</c:when>
	<c:otherwise>
	<font class="intro">
	<b>포인트가 부족하여 결제할 수 없습니다</b><br>
	</font>
	</c:otherwise>
	</c:choose>

</table>
</form>
</div>