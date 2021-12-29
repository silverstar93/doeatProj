
function mod(){
	
	var today = new Date();
	var yearNow = today.getFullYear();
	
	var namecc = /^([가-힣]+)$/;
	var yearcc = /^\d{4}$/;
	var datecc = /^\d{1,2}$/;
	var tel2cc = /^\d{3,4}$/;
	var tel3cc = /^\d{4}$/;
	
	msg = "";
	var chk = true;
	
	var yy = Number($('#yy').val());
	var dd = Number($('#dd').val());
	
	
	
	
	if (!(namecc.test($('#name').val()))) {
		msg = "이름을 확인해주세요"
		chk = false;
	} else if (!(yearcc.test($('#yy').val()) && datecc.test($('#dd').val()))) {
		msg = "생년월일을 확인해주세요";
		chk = false;
	} else if (1900 > yy || yy > yearNow || dd > 31 || dd <= 0) {
		msg = "생년월일을 확인해주세요";
		chk = false;
	} else if (!(tel2cc.test($('#tel2').val()) && tel3cc.test($('#tel3').val()))) {
		msg = "휴대전화 번호를  확인해주세요.";
		chk = false;
	} else if (!(namecc.test($('#answer').val()))) {
		msg = "비밀번호 찾기 답변을 작성해주세요. (답변은 한글만 입력이 가능합니다)"
		chk = false;
	}
	
	
	if (chk)
		modr.submit();
	else
		alert(msg);
}
	