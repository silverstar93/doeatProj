<%@page import="db_p.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<script src="../js/jquery-3.3.1.min.js"></script>
<script src="../scripts/login.js"></script>

<div class="loginmain" align="center" style="height: 700px;">
<form name="logf" action="loginReg" method="post">
<table style="height:100%; valign:middle;">
<tr><td>
	<table cellpadding="10" >
		<tr>
			<td colspan="3" align="center" class="temptitle"><h1><i>로그인</i></h1></td>
		</tr>
		<tr>
		<td colspan="3">
		<table cellpadding="5">
			<tr>
				<td>ID</td>
				<td><input type="text" name="id" id="id"size="15"/></td>
				<td rowspan="2"><input type="button" height="20px" value="로그인" onclick="log()" style=" height:55px;"/></td>
			</tr>
			<tr>
				<td>PW</td>
				<td><input type="password"  name="pw" id="pw" size="15"/></td>
			</tr>
		</table>
		<tr>	
			<td align="center" colspan="3"><a href="http://localhost:8080/doeatProj/login/join" class="intro">회원가입</a>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;<a href="idpw" class="intro">ID/PW 찾기</a></td>
		</tr>
		<tr>
				<td colspan="3">　</td>
			</tr>
					<tr>
				<td colspan="3">　</td>
			</tr>
								<tr>
				<td colspan="3">　</td>
			</tr>

	</table>
		</td></tr>
	</table>
</form>
</div>