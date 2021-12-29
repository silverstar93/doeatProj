<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="../js/jquery-3.3.1.min.js"></script>
<script src="../scripts/userBuy.js"></script>


<form action="UserBuyReg" metdod="post" name="userBuyForm">

<input type="hidden" name="cno" value="${data.cno }">
<h3 class="temptitle">결제</h3>
<table width="60%">
	<tr>
		<td class="temptitle">상품정보</td><td></td>
	</tr>
	<tr>
		<td colspan="2" bgcolor="#ff4d00" height="1px"></td>
	</tr>
	<tr>
		<td class="intro" widtd="100px">메뉴</td><td>${data.cmenu }</td>
	</tr><tr>
		<td class="intro">가격</td><td>&#x20a9;${data.cprice }</td>
	</tr><tr>
		<td class="temptitle"></td>
		<td>
			<STRIKE>
			<font color="orange">&#x20a9;${data.orgprice }(${data.cdiscount }% 할인)</font>
			</STRIKE>
		</td>
	</tr><tr>
		<td colspan="2" align="center"></td>
	</tr><tr>
		<td class="intro">유효기간</td><td>${data.cstartStr } ~ ${data.cendStr }</td>
	</tr><tr>
		<td class="intro">가격</td><td>${data.cprice }</td>	
	</tr>
	<tr><td colspan="2" height="5px"></td></tr>
		<tr>
		<td class="temptitle"><font size="4">결제정보입력</font></td><td></td>
	</tr>
	<tr>
		<td colspan="2" bgcolor="#ff4d00" height="1px"></td>
	</tr>
	<tr>
	<td class="intro">카드선택</td>
	<td><select name="bank">
		<option>신한카드</option>
		<option>BC카드</option>
		<option>농협카드</option>
		<option>국민카드</option>
		<option>농협카드</option>
		<option>하나카드</option>
		<option>삼성카드</option>
		<option>현대카드</option>
		<option>롯데카드</option>
		<option>우리카드</option>
		<option>수협은행</option>
		<option>시티카드</option>
		<option>기타카드</option>
	</select></td>
	</tr><tr>
		<td class="intro">카드번호</td>
		<td>
			<div>
				<input type="text" name="cd_1" maxlength="4" size="8" id="cNum1"/>-
				<input type="password" name="cd_2" maxlength="4"  size="8" id="cNum2"/>-
				<input type="password" name="cd_3" maxlength="4"  size="8" id="cNum3"/>-
				<input type="text" name="cd_4" maxlength="4"  size="8" id="cNum4" />
			</div>
		</td>
	</tr><tr>
	
	</tr><tr>
		<td class="intro">유효기간</td>   
		<td class="intro">
			<input type="text" name="card_mon" maxlength="2" size="4" id = "month"/>월
			<input type="text" name="card_year" maxlength="2" size="4"  id = "year"/>년
		</td>
	</tr><tr>
		<td class="intro">카드비밀번호</td>
		<td>
			<input type="password" name="card_pass" maxlength="4" id ="cardPW"/>
		</td>
	</tr><tr>
		<td class="intro">생년월일(yymmdd)</td>
		<td>
			<input type="text" name="iden_No" maxlength="6"  id="idNum"/>
		</td>
	</tr>
	<tr>
		<td class="intro">핸드폰 번호</td>
		<td>
			<input type="text" name="ph_1" maxlength="3" size="5" value="010" id="ph1"/>-
			<input type="text" name="ph_2" maxlength="4" size="8" id="ph2"/>-
			<input type="text" name="ph_3" maxlength="4" size="8" id="ph3"/>
		</td>
	</tr>
	<tr><td></td><td><button type="button" class="btnL" onclick="payChk()">결제하기</button></td></tr>
</table>
</form>
