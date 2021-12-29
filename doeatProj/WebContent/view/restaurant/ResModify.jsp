<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" rel="stylesheet">
<link rel="stylesheet" href="../css/resdetails.css" type="text/css"/>
<script src="../js/jquery-3.3.1.min.js"></script>
<script src="../scripts/annoyrno.js"></script>
<div>
<form name="modpat" action="ResPatModifyReg" method="post" enctype="multipart/form-data">
<input type="hidden" name="rid" value="${detail.rid }"/>
<table id="info" width="100%">
	<tr>
		<td width="700px" class="title" rowspan="2"><font size="15px">${detail.rname }</font></td>
		<td width="150px" align="right"></td>
		<td width="150px" align="right"></td>
	</tr>
</table>
	<table id="info" width="1000px">
		<tr>
			<td class="title" width="150px">주소</td>
			<td width="350px">${detail.raddr }
			</td>
			<td rowspan="2">
				<!-- <font color="red" size="1">주소 및 전화번호는 사업자등록증과 동일해야합니다<br>
				변경을 원하시는경</font><a href="">여기</a><font color="red" size="1">를 눌러주세요</font> -->
			</td>
			<td rowspan="8"></td>
		</tr>
		<tr>
			<td class="title">전화번호</td>
			<td>${detail.rtel }</td>
		</tr>
		<tr>
			<td class="title">영업시간</td>
			<td>
					<select name="rhour1">
					<c:forEach var="i" begin="0" end="24" step="1">
						<option value="${i }:00">${i }:00</option>
					</c:forEach>
					</select>
					-
					<select name="rhour2">
					<c:forEach var="i" begin="0" end="24" step="1">
						<option value="${i }:00">${i }:00</option>
					</c:forEach>
					</select>
					( ${detail.rhour } )
			</td>
			<td></td>
		</tr>
		<tr>
			<td class="title">메뉴</td>
			<td>
				대분류: ${detail.rmenu1 }
				<input type="hidden" name="rmenu1" value="${detail.rmenu1 }"/>
				소분류:
				<input type="text" id="rmenu2" name="rmenu2" value="${detail.rmenu2 }"/>
			</td>
			<td></td>
		</tr>
		<tr>
			<td class="title">가격</td>
			<td>
			<input type="text" id="rprice1" name="rprice1" value="${detail.rprice1 }"/> - 
			<input type="text" id="rprice2" name="rprice2" value="${detail.rprice2 }"/>
			</td>
			<td></td>
		</tr>
		<tr>
			<td class="title">주요메뉴</td>
			<td><input type="text" id="rmain1" name="rmain1" value="${detail.rmain1 }"/></td>
		</tr>
		<tr>
			<td class="title"></td>
			<td><input type="text" id="rmain2" name="rmain2" value="${detail.rmain2 }"/></td>
		</tr>
		<tr>
			<td class="title"></td>
			<td><input type="text" id="rmain3" name="rmain3" value="${detail.rmain3 }"/></td>
		</tr>
		<tr>
			<td class="title">사진</td>
			<td><input type="file" name="img" /></td>
		</tr>
		<tr>
			<td class="title"></td>
			<td></td>
			<td></td>
			<td class="title" align="right">
				<input type="button" value="변경완료" onclick="modchecked()"/>
				<input type="button" value="식당으로 돌아가기" onclick="goback()"/>
			</td>
		</tr>
	</table>
</form>
</div>