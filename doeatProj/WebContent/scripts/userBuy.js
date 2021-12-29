
  
function payChk() {
	
	var cNum1 = $('#cNum1').val();
	var cNum2 = $('#cNum2').val();
	var cNum3 = $('#cNum3').val();
	var cNum4 = $('#cNum4').val();
	var ph1 = $('#ph1').val();
	var ph2 = $('#ph2').val();
	var ph3 = $('#ph3').val();

	var cardPW = $('#cardPW').val();
	var idNum = $('#idNum').val();
	var month = $('#month').val();
	var year = $('#year').val();
	
	var today = new Date();

	var tt = /^\d{4}$/;
	var tt2 =/^\d{3}$/;
	var tt3 =/^\d{6}$/;
	var tt4 =/^\d{2}$/;
	
	
	if(!tt.test(cNum1)||!tt.test(cNum2)||!tt.test(cNum3)||!tt.test(cNum4)){
		alert("카드 번호는 숫자만 입력 가능합니다.");
	
	}else if(!tt4.test(year)){
		alert("날짜는 숫자 형식(yy)으로만 입력 가능합니다.");
		
		
	}else if(!tt4.test(month)||month<1||month>12){
		alert("날짜는 숫자 형식(mm)으로만 입력 가능합니다.");
		
	}else if(!tt.test(cardPW)){  
		alert("카드 비밀 번호는 숫자만 입력 가능합니다.");
		
	}else if(!tt3.test(idNum)){
		alert("생년월일은 숫자만 입력 가능합니다.");
		;
	}else if(!tt.test(ph3)||!tt.test(ph2)||!tt2.test(ph1)){
		alert("핸드폰 번호는 숫자만 입력 가능합니다.");
		
	}else{
		
		var date = new Date(eval($('#year').val())+2000,eval($('#month').val())-1);
		if(today.getTime()-date.getTime()>0){
			alert("카드 유효기간을 확인해 주십시오.");
			
		}else{
			document.userBuyForm.submit();
			}
		
	}
}
