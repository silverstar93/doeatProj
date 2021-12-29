<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" href="../css/resdetails.css" type="text/css"/>
<script src="../js/jquery-3.3.1.min.js"></script>
<form action="RvReportReg" method="post">
	<table width="400px" align="center">
		<tr>
			<td colspan="2" class="title" align="center"><h3>리뷰 신고 페이지</h3></td>
		</tr>
		<tr>
			<td class="title" width="250px">신고 사유를 선택하세요</td>
			<td>
				<select name="crs">
					<option value="hatred">혐오감을 조성하는 사진 또는 리뷰</option>				
					<option value="insult">인격 모독적인 단어 사용</option>
					<option value="terror">악의적인 별점 부여</option>
					<option value="improper">그 외 부적절한 언행</option>
				</select>
			</td>
		</tr>
		<tr>
			<td class="title">기타 내용을 작성해주세요</td>
		</tr>
		<tr>
			<td colspan="2">
				<textarea name="crsdt" cols="60" rows="2"></textarea>				
			</td>
		</tr>
		<tr>
			<td></td>
			<td align="right">
				<input type="submit" value="신고접수">
				<input type="reset" value="초기화">
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center" class="title"><a href="ResDetail?rid=${param.rid }">식당페이지로 돌아가기</a></td>
		</tr>
	</table>
	<input type="hidden" name="rid" value="${param.rid }"/>
	<input type="hidden" name="memNo" value="${param.memNo }"/>
	<input type="hidden" name="cno" value="${param.cno }"/>
</form>
<!-- 항목 3가지 내용 사진업로 -->