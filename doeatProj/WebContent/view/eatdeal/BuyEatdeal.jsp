<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../css/eatdealList.css" style="text/css"/>
<div class="outdetail"><br>
<form action="UserBuyForm" method="post">
<input type="hidden" name="cno" value="${data.cno }">
<table width="90%">
	<tr>
		<td rowspan="9" align="center" width="500px">
		<img src="../image/${data.rimg }" width="400px">
		</td>		
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td colspan="2" class="dealline"><h2 class="dealtxt">${data.cmenu }</h2></td>
	</tr>
	<tr>	
		<td class="dealtxt">가격</td>
		<td>&#x20a9;${data.cprice }</td>
	</tr>
	<tr>
	
		<td></td>
		<td>
			<STRIKE>
			<font color="orange">&#x20a9;${data.orgprice }(${data.cdiscount }% 할인)</font>
			</STRIKE>
		</td>
	</tr>
	<tr>	
		<td class="dealtxt">쿠폰 사용 기간</td>
		<td>${data.cstartStr } ~ ${data.cendStr }</td>		
	</tr>
	<tr>
	
		<td class="dealtxt">남은매수</td><td>${data.cvolume + data.paycnt } / ${data.cvolume }</td>
		
	</tr>
	<tr>
		<td colspan="2" class="thinline"></td>
	</tr>
	<tr>
		
		<td class="dealtxt">메뉴 소개</td><td>${data.contentBr }</td>
		
	</tr>
	<tr>
		<td align="right">
			<button type="submit" class="btnL">구매하기</button>			
		</td>
		<td align="left">
			<input type="button" value="포인트로 구매하기" onclick="location.href='Point?cno=${data.cno }'"/>
		</td>
	</tr>
</table>
</form>
</div>
<div class="indetail">
	<a href="../restaurant/ResDetail?rid=${data.rid }"><i>${param.name }<br>${param.addr }</i></a>
</div>