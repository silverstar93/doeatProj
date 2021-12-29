function idCheck() {
	var id = $('#id').val();
	var idcc = /^[a-zA-Z0-9]{3,10}$/;
	var msg = "사용 가능한 아이디입니다";

	if (idcc.test(id)) {

		$.ajax('../ajax_jisu/idCheck?id=' + id, {
			success : function(dd) {

				if (eval(dd)) {
					msg = "이미 사용중인 아이디입니다"
				}

				if (confirm(msg)) {
					if (msg == "사용 가능한 아이디입니다") {
						$('#id').attr('readOnly', true);
						$('#idChk').val('중복확인완료');
						idck = true;
					} else {
						$('#id').removeAttr('readOnly');

					}
				}

			}
		});

	} else {
		msg = "아이디는 3자리 이상 10자리 이하의 영문,숫자 조합만 가능합니다.";
		alert(msg);
	}

}

function mailCheck() {
	var mail1cc = /^([a-zA-Z0-9_\.-]+)$/;
	var mail2cc = /^(\w+)[.](\w+)$/;
	var msg = "가입 가능한 이메일입니다";

	if (mail1cc.test($('#mail1').val()) && mail2cc.test($('#mail2').val())) {

		var mail = $('#mail1').val() + '@' + $('#mail2').val();
		$.ajax('../ajax_jisu/mailCheck?mail=' + mail, {
			success : function(dd) {
				if (eval(dd)) {
					msg = "이미 가입된 이메일입니다"

				}

				if (confirm(msg)) {
					if (msg == "가입 가능한 이메일입니다") {
						$('#mail1').attr('readOnly', true);
						$('#mail2').attr('readOnly', true);
						$('#mailChk').val('중복확인완료');
					} else {
						$('#mail1').removeAttr('readOnly');
						$('#mail2').removeAttr('readOnly');

					}
				}

			}
		});

	} else {
		msg = "이메일을 확인해주세요.";
		alert(msg);
	}

}

function join(cate) {

	
	var today = new Date();
	var yearNow = today.getFullYear();

	var pwcc = /^[a-zA-Z0-9~!#$%^*]{6,12}$/;
	var namecc = /^([가-힣]+)$/;
	var yearcc = /^\d{4}$/;
	var datecc = /^\d{1,2}$/;
	var tel2cc = /^\d{3,4}$/;
	var tel3cc = /^\d{4}$/;

	var rnocc = /^\d{9}$/;
	var rpricecc = /^\d{4,6}$/;

	msg = "";
	var chk = true;

	var yy = Number($('#yy').val());
	var dd = Number($('#dd').val());
	
	


	if (!namecc.test($('#answer').val())) {
		msg = "비밀번호 찾기 답변을 작성해주세요. (답변은 한글만 입력이 가능합니다)"
		chk = false;
	}

	if (cate == '2') {
		
		console.log($('#sample4_roadAddress').val());
		
		if (!rnocc.test($('#rno').val())) {
			msg = "사업자번호를 확인하세요";
			chk = false;
		} else if (!namecc.test($('#rname').val())) {
			msg = "상호명을 확인하세요 (상호명은 한글만 입력이 가능합니다)";
			chk = false;
		} else if (!namecc.test($('#rowner').val())) {
			msg = "대표자를 확인하세요";
			chk = false;
		} else if ($('#sample4_roadAddress').val()=="") {
			msg = "주소를 확인하세요";
			chk = false;
		} else if (!(tel2cc.test($('#rtel2').val()) && tel3cc.test($('#rtel3')
				.val()))) {
			msg = "전화번호를 확인하세요";
			chk = false;
		} else if (!namecc.test($('#rmenu2').val())) {
			msg = "메뉴를 확인하세요 (메뉴는 한글만 입력이 가능합니다)";
			chk = false;
		} else if (!(rpricecc.test($('#rprice1').val()) && rpricecc.test($(
				'#rprice2').val()))) {
			msg = "가격을 확인하세요";
			chk = false;
		} else if (eval($('#rprice1').val()) > eval($('#rprice2').val())) {
			msg = "가격대는 낮은 금액에서 높은 금액 순으로 입력해주세요";
			chk = false;
		} else if (!(namecc.test($('#rmain1').val())
				&& namecc.test($('#rmain2').val()) && namecc.test($('#rmain3')
				.val()))) {
			msg = "주요메뉴를 확인하세요 (주요메뉴는 한글만 입력이 가능합니다)";
			chk = false;
		}
	}

	if ($('#idChk').val() == '중복확인') {
		msg = "아이디 중복확인을 해주세요.";
		chk = false;
	} else if (!pwcc.test($('#pw').val())) {
		msg = "비밀번호가 양식에 맞지 않습니다.";
		chk = false;
	} else if ($('#pw').val() != $('#pwChk').val()) {
		msg = "비밀번호가 일치하지 않습니다.";
		chk = false;
	} else if (!namecc.test($('#name').val())) {
		msg = "이름을 한글로 작성해주세요.";
		chk = false;
	} else if (!(yearcc.test($('#yy').val()) && datecc.test($('#dd').val()))) {
		msg = "생년월일을 확인해주세요";
		chk = false;
	} else if (1900 > yy || yy > yearNow || dd > 31 || dd <= 0) {
		msg = "생년월일을 확인해주세요";
		chk = false;
	} else if (!($('#male').prop('checked') || $('#female').prop('checked'))) {
		msg = "성별을 선택해주세요."
		chk = false;
	} else if ($('#mailChk').val() == '중복확인') {
		msg = "이메일 중복확인을 해주세요.";
		chk = false;
	} else if (!(tel2cc.test($('#tel2').val()) && tel3cc.test($('#tel3').val()))) {
		msg = "휴대전화 번호를  확인해주세요.";
		chk = false;
	}

	if (chk)
		jj.submit();
	else
		alert(msg);

}