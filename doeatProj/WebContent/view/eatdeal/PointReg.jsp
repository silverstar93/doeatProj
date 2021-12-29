<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>



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
	<tr>
		<td width="25%" class="review">쿠폰 번호</td>
		<td>${code.ccode }</td>
	</tr>
	<tr>
		<td class="review">쿠폰 정보</td>
		<td> ${data.cmenu }</td>
	</tr>
	<tr>
		<td class="review">쿠폰 금액</td>
		<td> ${data.cprice }원</td>
	</tr>
	<tr>
		<td class="review">남은 두잇 포인트</td>
		<td> ${point}원</td>
	</tr>
	<tr>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="button" value="잇딜로 돌아가기" onclick="location.href='List2'"/></td>
	</tr>

</table>

