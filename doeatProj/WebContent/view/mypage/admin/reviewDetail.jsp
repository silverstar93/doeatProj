<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 리뷰 삭제시에는 사유가 crs -->
<!-- 삭제 반려시에는 사유가 chd -->
<!-- 회원이 본인 리뷰가 신고된걸 볼수있 -->
<form action="reviewReport" method="post">
	<table width="100%">
		<tr>
			<td colspan="3"></td>
		</tr>
		<tr>					
			<td class="review" width="100px">작성자:${onrv.memNo }</td>
			<td></td>
			<td class="review" width="300px" align="right">등록일(${onrv.regdateStr })</td>
		</tr>
		<tr>
			<td class="cover" colspan="3"></td>
		</tr>
		<tr>
			<td class="review">내용</td>
			<td>${onrv.contextBr }</td>			
			<td rowspan="3" align="center">
			<c:choose>
			<c:when test="${onrv.pic!='NA' }">
				<img src="../rvpic/${onrv.pic }" width="200px"/>
			</c:when>
			<c:otherwise>
				<i>게시된 사진이 없습니다</i>
			</c:otherwise>
			</c:choose>
			</td>
		</tr>
		<tr>
			<td class="cover" colspan="2"></td>
		</tr>
		<tr>
			<td class="review">별점</td>
			<td class="intro">
			<font size="2">맛점수:${onrv.tsrate } / 분위기점수:${onrv.mdrate } / 서비스점수:${onrv.svrate }</font>
			</td>
		</tr>
		<tr>
			<td class="cover" colspan="2"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="radio" name="result" value="reject">신고반려
				<input type="radio" name="result" value="delete">리뷰삭제
				<input type="submit" value="처리 완료"/>
				<input type="hidden" name="conum" value="${param.conum }"/>
				<input type="hidden" name="rid" value="${onrv.rid }"/>
				<input type="hidden" name="memNo" value="${onrv.memNo }"/>
				<input type="hidden" name="cno" value="${param.cno }"/>
				<input type="hidden" name="pic" value="${onrv.pic }"/>
				<input type="hidden" name="nowPage" value="${nowPage }"/>
			</td>				
		</tr>			
	</table>
</form>