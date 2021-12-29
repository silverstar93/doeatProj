<%@page import="java.io.Console"%>
<%@page import="db_p.UserDTO"%>
<%@page import="java.util.LinkedHashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="../css/mypggeneral.css" type="text/css">
<div>
<table width="100%" cellspacing="0" cellpadding="30px">
	<tr >
		<td valign="top" width="200px" height="700px" class="mypgmenu"><jsp:include page="menu.jsp"></jsp:include></td>
  		<td valign="top" align="center" width="800px"><jsp:include page="${main }"></jsp:include></td> 
	</tr>
</table>
</div>