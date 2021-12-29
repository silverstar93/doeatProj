<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="http://localhost:8080/doeatProj/js/jquery-3.3.1.min.js"></script>
<!-- 메인 검색창 -->
<script src="../scripts/search.js"></script>
<div class="eatlistHome">
<img alt="" src="../view/img/home.jpg" style="width: 980px; height: 500px;">
</div>
<div class="eatlistSrch">
  <font color="#ff4d00"><h3>> DO IT! SEARCH IT!</h3></font>
</div>  
<div class="eatlistSrch">

<form action="Eatlist" method="post" name = "srch">
	<table>
		<tr>
			<td class="eatlisthlgt">지역</td>
			<td>
				<select name = "rloc1">
				<c:forEach var="i" items="${location }">
					<option value="${i }" class="rolc1"> ${i }</option>					
				</c:forEach>
				</select>
			</td>			
		</tr>		
		<tr>
			<td class="eatlisthlgt">음식 종류</td>
			<td>
				<c:forEach var="i" items="${menu }" varStatus="no">				
						<input type="radio" name="menu" value="${i }" checked="checked"/>${i }&nbsp;&nbsp;&nbsp;								
				</c:forEach>
			</td>
		</tr>
		
		<tr>			
			<td>
			
			<input type="text" width="200px" name="search" id ="search">
			
			</td>
			<td><input type="button" value="검색" onclick="srchChk()"></td>
		</tr>
	</table>
</form>
</div>
<!-- 추천메뉴 칸 -->

