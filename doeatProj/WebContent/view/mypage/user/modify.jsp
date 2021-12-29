<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
	<script src="../js/jquery-3.3.1.min.js"></script>
	<script>
	
	function mod(){
		
		var pwcc = /^[a-zA-Z0-9~!#$%^*]{6,12}$/;
		var chk = true;
		
		  if (!pwcc.test($('#pw').val())) {
				msg = "비밀번호를 다시 확인해주세요.";
				chk = false;
			}
		  
		  
			if (chk)
				umod.submit();
			else
				alert(msg);
	}
	
	
	</script>

<div align="center" style="height: 600px;">
<table style="height:100%; valign:middle;">
<tr><td>
<form name="umod" action="uModifyReg" method="post">
<input type="hidden" name="tel1" value="${param.tel1 }" />
<input type="hidden" name="tel2" value="${param.tel2 }" />
<input type="hidden" name="tel3" value="${param.tel3 }" />
<input type="hidden" name="mail1" value="${param.mail1 }" />
<input type="hidden" name="mail2" value="${param.mail2 }" />
<input type="hidden" name="question" value="${param.question }" />
<input type="hidden" name="answer" value="${param.answer }" />

	<table>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" id="pw" name="pw" /></td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="button" onclick="mod()"  value="수정" />
				<a href="uModify">뒤로</a>
			</td>
		</tr>
	</table>
</form>

	</td></tr>
	</table>
</div>
