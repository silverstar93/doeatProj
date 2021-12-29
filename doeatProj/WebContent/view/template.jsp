<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="ss" class="db_p.SrchDTO" scope="request"/>

<link href="https://fonts.googleapis.com/css?family=Gothic+A1|Noto+Sans+KR" rel="stylesheet">
<link rel="stylesheet" href="../css/template.css" type="text/css">
<header>
	<div id="mainTop"><jsp:include page="inc/top.jsp"/></div>
</header>
<body>
<div class="bigMain">
<c:if test="${mainUrl==null }">
<c:set var="mainUrl" value="eatlist/Home.jsp"/>
</c:if>
	<div class="main" id="theMain"><jsp:include page="${mainUrl }" /></div>
	<div class="main" id="mainBottom"><jsp:include page="inc/bottom.jsp" /></div>
</div>
</body>