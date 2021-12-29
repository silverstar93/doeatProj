<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>Detail</h3>
<form action="refuse" method="post">

<input type="hidden" name="cno" value="${cno }">
<table>
<tr>
<th>refuse</th>
</tr>
<tr>
<td><textarea rows="10" cols="80" name="">aa</textarea></td>
</tr><tr>
		<td colspan="2" align="right">
			<input type="submit" value="제출">
			<a href="deal?page=${nowPage }">목록으로</a>
		</td>
</tr>
</table>
</form>
