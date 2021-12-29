<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<jsp:useBean id="mm" class="db_p.UserDTO" scope="session"/>
<link rel="stylesheet" href="../css/resdetails.css" type="text/css">
<c:choose>
<c:when test="${mm.cate == 0 }">
		<jsp:include page="inc/detailAdmin.jsp"></jsp:include>
		<jsp:include page="inc/review.jsp"></jsp:include>
</c:when>
<c:otherwise>
		<jsp:include page="inc/detail.jsp"></jsp:include>
		<jsp:include page="inc/review.jsp"></jsp:include>
</c:otherwise>
</c:choose>

	