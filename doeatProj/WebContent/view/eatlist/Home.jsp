<%@page import="db_p.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="../css/maineatlist.css" type="text/css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="inc/SrhHome.jsp"/>

<c:choose>
	<c:when test="${mm.cate==1 }">
	<jsp:include page="inc/Recomm.jsp"/>
	</c:when>
	<c:otherwise>
			<table width="100%" height="200px">
		<tr>
			<td align="center"><img src="../icons/justdoit.png" width="80px"></td>
		</tr>
		<tr>
			<td align="center"> 
				<h2><font color="#ff4d00">JUST<br>
				 DO IT DO EAT!</font></h2>
			</td>
		</tr>
		</table>
		 
	</c:otherwise>
</c:choose>

