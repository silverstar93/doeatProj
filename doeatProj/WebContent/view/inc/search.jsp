<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<script src="../js/jquery-3.3.1.min.js"></script>
<script src="../scripts/search.js"></script>

<div id="srch">
<form action="Eatlist" method="post" name = "srch">
			지역			
				<select name = "rloc1">
				<c:forEach var="i" items="${ss.locS }">
					<option value="${i }" class="rolc1"> ${i }</option>					
				</c:forEach>
				</select>			
			음식 종류
			<select name = "menu">
				<c:forEach var="i" items="${ss.menuS }">
				<option value="${i }" class="menu"> ${i }</option>			
					<%-- <option type="radio" name="menu" value="${i }"/>${i }&nbsp;&nbsp;&nbsp;			 --%>		
				</c:forEach>
				</select>
				
			<input type="text" width="200px" name="search" id ="search">
			<input type="button" value="검색" onclick="srchChk()">
</form>


</div>
	