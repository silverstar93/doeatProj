<%@ page import="db_p.DealDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<jsp:useBean id="mm" class="db_p.UserDTO" scope="session"/>
<script type="text/javascript" src="../js/jquery-3.3.1.min.js" ></script>
<script src="../js/js_eb/cal.js"></script>
<script src="../scripts/dealReq.js"></script>

<h3 class="review">잇딜 신청</h3>


<form action="AppEatdealReg" method="post" enctype="multipart/form-data" name = "dealReq">
<input type="hidden" name="memNo" value="${mm.memNo }"/>
	<table width="70%">
	
	<!-- 쿠폰번호 자동 발급. 식당코드는 따로 끌어오기 -->
		<tr>
			<td class="review">메뉴</td>
			<td><input type="text" name="cmenu" id="cmenu"/></td>
		</tr>
		<tr>
			<td class="review">원가</td>
			<td><input type="text" name="orgprice" id="orgprice" /></td>
		</tr>
		<tr>
			<td class="review">할인율</td>   
			<td><select name="cdiscount">
					<option value="15">15%</option>
					<option value="20">20%</option>
					<option value="30">30%</option>
				</select>
			</td>
		</tr>
	
		<tr>
			<td class="review">판매 시작일(3주간 판매)</td>
			<td><!-- <input type="text" name="pubstart" id ="ddate" /> -->
			<input type="text" name="pyear" maxlength="4" id = "pyear" size="8"><span>년</span>
			<input type="text" name="pmonth" maxlength="2" id = "pmonth" size="8"><span>월</span>
			<input type="text" name="pday" maxlength="2" id = "pday" size="8">일
			</td>
		</tr>
		<tr>
			<td class="review" id = "coup">쿠폰 개시일(3달간 사용)</td>
			<td>
			<input type="text" name="cyear" maxlength="4" id = "cyear" size="8"><span>년</span>
			<input type="text" name="cmonth" maxlength="2" id = "cmonth" size="8"><span>월</span>
			<input type="text" name="cday" maxlength="2" id = "cday" size="8">일
			</td>
		</tr>			
		<tr>
			<td id ="pubAlarm"></td>
		</tr>
		<tr>
			<td class="review">판매수</td>
			<td>
				<select name="cvolume">
					<option value="50">50장</option>
					<option value="100">100장</option>
					<option value="150">150장</option>
				</select>
			</td>
		</tr>
		<tr>
			<td class="review">사진</td>
			<td><input type="file" name="rimg" /></td>
		</tr>
		<tr>
			<td class="review">메뉴소개</td>
			<td><textarea name="content" cols="30" row="5"></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="right">
			<input type="button" value="작성" onclick="dealReqChk()">
			<input type="reset" value="초기화">
			</td>
		</tr>

	</table>
</form>
		