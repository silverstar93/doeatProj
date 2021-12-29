<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%if(request.getAttribute("msg")!=null || request.getAttribute("msg")!="" || (!request.getAttribute("msg").equals(""))
|| request.getAttribute("msg")!="null"){ %>
<script>
	alert("${msg}")
	location.href="${goUrl }"
</script>
<% }else{ %>
<script>
	location.href="${goUrl }"
</script>
<% } %>