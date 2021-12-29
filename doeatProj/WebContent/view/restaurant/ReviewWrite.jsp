<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="mm" class="db_p.UserDTO" scope="session"/>
<!DOCTYPE html>
<link rel="stylesheet" href="../css/resdetail.css" type="text/css"/>
<script src="../js/jquery-3.3.1.min.js"></script>
<script src="../scripts/reviewWrite.js"></script>
<style>
.title{
	color: #ff4d00;
	font-family: 'Noto Sans KR', sans-serif;
}
</style>
<div>
<form action="ReviewReg" method="post" enctype="multipart/form-data">
	<table width="400px" align="center">
		<tr>
			<td colspan="2" class="title" align="center"><h3>DO IT DO EAT과 경험을 공유해주세요!</h3></td>
		</tr>
		<tr>
			<td class="title" width="350px">맛은 어땠나요?</td>
			<td>
				<a class="taste" id="taste1">☆</a><a class="taste" id="taste2">☆</a><a class="taste" id="taste3">☆</a><a class="taste" id="taste4">☆</a><a class="taste" id="taste5">☆</a>
			</td>
		</tr>
		<tr>
			<td class="title">분위기는 좋았나요?</td>
			<td>
				<a class="mood" id="mood1">☆</a><a class="mood" id="mood2">☆</a><a class="mood" id="mood3">☆</a><a class="mood" id="mood4">☆</a><a class="mood" id="mood5">☆</a>
			</td>
		</tr>
		<tr>
			<td class="title">서비스는 좋았나요?</td>
			<td>
				<a class="cs" id="cs1">☆</a><a class="cs" id="cs2">☆</a><a class="cs" id="cs3">☆</a><a class="cs" id="cs4" >☆</a><a class="cs" id="cs5">☆</a>
			</td>
		</tr>
		<tr>
			<td></td>
			<td align="right"><input type="button" onclick="resetStar()" value="별점 지우기!"/></td>
		</tr>
		<tr>
			<td class="title" colspan="2">코멘트를 작성해주세요</td>
		</tr>
		<tr>
			<td colspan="2">
				<textarea name="context" cols="67" rows="5"></textarea>				
			</td>
		</tr>
		<tr>
			<td class="title">사진을 공유해주세요!</td>
			<td></td>
		</tr>
		<tr>
			<td><input type="file" name="pic"/></td>
			<td><input type="submit" value="작성완료"/></td>
		</tr>
		<tr>
			<td colspan="2" align="right" class="title"><a class="title" href="ResDetail?rid=1234">식당페이지로 돌아가기</a></td>
		</tr>
	</table>
	<input type="hidden" id="taste" name="tsrate" value="0"/>
	<input type="hidden" id="mood" name="mdrate" value="0"/>
	<input type="hidden" id="cs" name="svrate" value="0"/>
	<input type="hidden" name="rid" value="${param.rid }"/>
	<input type="hidden" name="memNo" value="${mm.memNo }"/>
</form>
</div>