<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="http://localhost:8080/doeatProj/js/jquery-3.3.1.min.js"></script>
<!-- 메인 검색창 -->
<script src="../scripts/search.js"></script>

<link rel="stylesheet" href="../css/eatdealList.css" type="text/css"/>
	<font size="8" class="dealtxt"><i>Would you like to EATDEAL...?</i> </font><br>
	&nbsp;&nbsp;<font size="4" face="Noto Sans KR">두잇두잇에서 특별한 가격의 이용권을 구매해보세요!</font>
	<br><br>
	     
<div class="eatlistSrch">
  <font color="#ff4d00" ><h3>> DO IT! SEARCH DEAL!</h3></font>


<form action="List2" method="get" name = "dealsrch">
	<table>
		<tr>			
			<td>
			
			<input type="text" width="200px" name="search" id ="search">
			
			</td>
			<td><input type="button" value="검색" onclick="dealChk()"></td>
		</tr>
	</table>
</form>
</div>
	
	
	
	
<c:forEach var="i" items="${list}" varStatus="no" >
	<div class="outv">
	<table width="100%">
		<tr>
			<td align="center" colspan="3">
				<img src="../image/${i.rimg}" width="900px" height="530px">
			</td>
		</tr>
		<tr>
			<td width="50px"></td>
			<td class="dealline"></td>
			<td width="50px"></td>
		</tr>
	</table>
	<a href="BuyEatdeal?cno=${i.cno}&name=${i.rname }&addr=${i.raddr }">
	<div class="insv">
	<br>
		<table width="100%">
		<tr>
			<td class="dealtxt" colspan="2" width="70%">
				<c:forTokens var="aa" items="${i.orgprice*(100-i.cdiscount)/100 }" delims="." varStatus="a">
				<c:if test="${a.index==0 }">
				<h2 class="dealresname"><b>${i.cmenu}</b> - ${aa }원(${i.cdiscount}% 할인)</h2>
				</c:if>
				</c:forTokens>	
			</td>
		</tr>
		<tr>
		<td></td>
		<td class="dealtxt" align="right"><i>${i.rname }(${i.raddr })</i></td>
		<td></td>
		</tr>
		</table>
	<br>
	</div>
	</a>
	</div>
	<br>
</c:forEach>