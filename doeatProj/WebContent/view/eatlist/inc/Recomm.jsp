 <%@page import="org.apache.tomcat.jni.Mmap"%>
<%@page import="db_p.UserDAO"%>
<%@page import="db_p.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="eatlistSrch">
	<div class="eatlistRcmm">
	<font color="#ff4d00"><h3>> 추천메뉴 섹션</h3></font>
	<table>	
	<tr>	
		<c:forEach var="i"  items="${stats}" varStatus="no">
		
		<td align="center">
		<img src="../view/img/${i.pic }" width="200px"><br>
	
		<a class="eatlisthlgt" href="../restaurant/ResDetail?rid=${i.rid}">
		${i.rname}
		</a>
		</td>
		
		</c:forEach>
		</tr>
	</table>
	</div>
</div> 
