<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<form action="delPartReg" method="post">
	<h3 class="intro">파트너 이용권 상태: ${cChk}</h3><br> 
	<input type="submit" value="파트너 탈퇴" /> 
	<input type="hidden" name="rid" value="${rid }" /> 
	<input type="hidden" name="memNo" value="${memNo }" />
	<input type="hidden" name="nowPage" value="${nowPage }"/>
</form>