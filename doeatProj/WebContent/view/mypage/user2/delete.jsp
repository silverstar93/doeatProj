<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<script src="../js/jquery-3.3.1.min.js"></script>
	<script>
	
	function del(){
		
		var pwcc = /^[a-zA-Z0-9~!#$%^*]{6,12}$/;
		var chk = true;
		
		  if (!pwcc.test($('#pw').val())) {
				msg = "비밀번호를 다시 확인해주세요.";
				chk = false;
			}
		  
		  
			if (chk)
				delr.submit();
			else
				alert(msg);
	}
	
	
	</script>
	
<div align="center" style="height: 600px;">
<table style="height:100%; valign:middle;">
<tr><td>
<form name="delr" action="deleteReg" method="post">
	<table width="100%">
		<tr>
			<td>암호</td>
			<td><input type="password" id="pw" name="pw" /></td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="button" onclick="del()" value="탈퇴" />
				<a href="index">뒤로</a>
			</td>
		</tr>
	</table>
</form>

	</td></tr>
	</table>
</div>