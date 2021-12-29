function log() {

	
	var idcc = /^[a-zA-Z0-9]{3,10}$/;
	var pwcc = /^[a-zA-Z0-9~!#$%^*]{6,12}$/;

	var chk = true;



	if (!idcc.test($('#id').val())) {
		chk = false;
	} else if (!pwcc.test($('#pw').val())) {
		chk = false;
	}

	if (chk)
		logf.submit();
	else
		alert("아이디 또는 비밀번호를 다시 확인하세요.");

}