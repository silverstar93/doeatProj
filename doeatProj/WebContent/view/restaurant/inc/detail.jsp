<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="mm" class="db_p.UserDTO" scope="session"/>
<!DOCTYPE html>
<script src="../js/jquery-3.3.1.min.js"></script>
<script src="../scripts/resDetailAdr.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9c8f4b16a0c59154c786a22345c07048&libraries=services,clusterer,drawing"></script>

<div class="rescover">
<table id="info" width="1000px">
	<tr>
		<td width="700px" class="title" rowspan="2"><font size="15px">${detail.rname }</font>${detail.score }</td>
		<td width="150px" align="right"></td>
		<td width="150px" align="right"></td>
	</tr>
	<tr>
		<c:choose>
		<c:when test="${mm.memNo==null }">
			<td></td>
		</c:when>
		<c:when test="${mm.cate==2 }">
			<td></td>
		</c:when>
		<c:when test="${mm.memNo == detail.memNo }">
			<th>북마크 : ${detail.marked } 개</th>
		</c:when>
		<c:when test="${mk== 'old' }">
		<td width="150px" class="title" align="center">
			<a href="MarkOut?rid=${detail.rid }&memNo=${mm.memNo}"><img src="../icons/markout.png" width="50px"></a><br>
			북마크 해제
		</c:when>
		<c:otherwise>
		<td width="150px" class="title" align="center">
			<a href="ResMark?rid=${detail.rid }&memNo=${mm.memNo}"><img src="../icons/sunny.PNG" id="sunny" width="50px"></a><br>
			북마크
		</td>
		</c:otherwise>
		</c:choose>
		
		<c:choose>	
		<c:when test="${mm.memNo==null }">
		<td></td>
		</c:when>
		<c:when test="${mm.memNo==detail.memNo }">
		<td></td>
		</c:when>
		<c:otherwise>		
		<td width="150px" class="title" align="center">
			<a href="ReviewWrite?rid=${detail.rid }&memNo=0002"><img class="viewer" src="../icons/revboy.png" width="50px"></a><br>
			리뷰작성!
		</td>
		</c:otherwise>
		</c:choose>	
		
	</tr>
</table>
</div>
<div class="rescover">
<div class="resdiv">
	<table width="660px">
		<tr>
			<td class="title" width="150px">주소</td>
			<td width="400px">${detail.raddr }</td>
				<input type="hidden" id="txtAdr" name="txtAdr" value="${detail.raddr }"/>
			<td rowspan="8">
			</td>
		</tr>
		<tr>
			<td class="title">전화번호</td>
			<td>${detail.rtel }</td>
		</tr>
		<tr>
			<td class="title">영업시간</td>
			<td>${detail.rhour }</td>
		</tr>
		<tr>
			<td class="title">메뉴</td>
			<td>${detail.rmenu1 } - ${detail.rmenu2 }</td>
		</tr>
		<tr>
			<td class="title">가격</td>
			<td>${detail.rprice1 } - ${detail.rprice2 }</td>
		</tr>
		<tr>
			<td class="title">주요메뉴</td>
			<td>${detail.rmain1 }</td>
		</tr>
		<tr>
			<td class="title"></td>
			<td>${detail.rmain2 }</td>
		</tr>
		<tr>
			<td class="title"></td>
			<td>${detail.rmain3 }</td>
		</tr>
		<tr>
			<td class="title" colspan="2" align="right">
				<br><br>최종 수정일:${detail.rchgdate }
				<c:if test="${mm.memNo == detail.memNo }">
				<a href="ResModify?rid=${detail.rid }" class="ptOnly"><b>식당정보 수정</b></a>
				</c:if>
			</td>
		</tr>
	</table>
	</div>
	<div class="resdiv" id="map"></div>	
	</div>