<%@page import="java.util.Set"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
<c:choose>
	<c:when test="${imin=='AdminDetail' }">
	<c:set var="a" value="deal"/>
	</c:when>
	<c:when test="${imin=='partnerDetail' }">
	<c:set var="a" value="partner"/>
	</c:when>
	<c:when test="${imin=='uDetail' }">
		<c:choose>
			<c:when test="${dto.cate==1 }">
			<c:set var="a" value="user"/>
			</c:when>
			<c:otherwise>
			<c:set var="a" value="partner"/>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:when test="${imin=='uModify' }">
		<c:choose>
			<c:when test="${dto.cate==1 }">
			<c:set var="a" value="user"/>
			</c:when>
			<c:otherwise>
			<c:set var="a" value="partner"/>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:when test="${imin=='resReg' }">
		<c:set var="a" value="res"/>
	</c:when>
	<c:when test="${imin=='resAcp' }">
		<c:set var="a" value="res"/>
	</c:when>
	<c:when test="${imin=='resMng' }">
		<c:set var="a" value="res"/>
	</c:when>
	<c:when test="${imin=='resDetail' }">
		<c:set var="a" value="res"/>
	</c:when>
	<c:when test="${imin=='replist' }">
		<c:set var="a" value="myRes"/>
	</c:when>
	<c:when test="${imin=='reviewDetail' }">
		<c:set var="a" value="review"/>
	</c:when>
	<c:otherwise>
		<c:set var="a" value="${imin }"/>
	</c:otherwise>
</c:choose>

<table width="100%">
	<c:forEach items="${menuList}" var = "i">
	<tr height="50px">
		<c:choose>
		<c:when test="${a==i.key }">
		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="white" face="Noto Sans KR" size="4">${i.value }</font></td>
		</c:when>
		<c:otherwise>
		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="menutitle" href="${i.key }">${i.value }</a></td>
		</c:otherwise>
		</c:choose>		
	</tr>
</c:forEach>
</table>
</div>