<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<script src="../js/jquery-3.3.1.min.js"></script>
	<script>
	
	function ch(){
		
		var pwcc = /^[a-zA-Z0-9~!#$%^*]{6,12}$/;
		var chk = true;
		
		  if (!pwcc.test($('#pw').val())) {
				msg = "현재 비밀번호를 다시 확인해주세요.";
				chk = false;
			} else if(!pwcc.test($('#chpwChk').val())||!pwcc.test($('#chpw').val())){
				msg = "비밀번호는 6자리 이상, 12자 이하로 설정해 주시기 바랍니다. (허용특수문자: ~!#$%^*)";
				chk = false;
			} else if ($('#chpw').val() != $('#chpwChk').val()) {
				msg = "변경 비밀번호가 일치하지 않습니다.";
				chk = false;
			}
		  
		  
			if (chk)
				pwch.submit();
			else
				alert(msg);
	}
	
	
	</script>

<div align="center" style="height: 600px;">
<table style="height:100%; valign:middle;">
<tr><td>
<form name="pwch" action="pwChReg" method="post">
	<table cellspacing="5">
		<tr>
			<td>현재 비밀번호</td>
			<td><input type="password" id="pw" name="pw" /></td>
		</tr>
		<tr>
			<td>새 비밀번호</td>
			<td><input type="password" id="chpw" name="chpw" /></td>
		</tr>
		<tr>
			<td>새 비밀번호 확인</td>
			<td><input type="password" id="chpwChk" name="chpwChk" /></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="button" onclick="ch()" value="변경하기" /></td>
		</tr>
	</table>
</form>

	</td></tr>
	</table>
</div>