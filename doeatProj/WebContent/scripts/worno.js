function modchecked(){		
	
		var kor = [$('#rname').val(),$('#rowner').val(),
			$('#rloc2').val(),$('#rmenu2').val(),$('#rmain1').val(),
			$('#rmain2').val(),$('#rmain3').val()]
		
		var korChk = /^([가-힣]+)$/
		
		if(eval($('#rprice1').val())>eval($('#rprice2').val())){
			alert('가격대는 낮은 금액에서 높은 금액 순으로 입력해주세요')
			
		}else if(!(/^\d{2,3}-\d{3,4}-\d{4}$/.test($('#rtel').val()))){
			alert('전화번호는 다음과 같이 입력이 가능합니다\n'+
					'010-0000-0000 또는 02-000-0000')
		}else if(!(/^\d{1,2}:\d{1,2}-\d{1,2}:\d{1,2}$/.test($('#rhour').val()))){
			alert('시간은 다음과 같이 입력이 가능합니다\n'+
			'1:10 또는 13:55')
		}else{
					
			for (aa in kor) {
				if(!korChk.test(kor[aa])){
					console.log(korChk+",s"+kor[aa])
					alert('상호명,사업자,장소(#소분류),메뉴(#소분류),주요메뉴,주요메뉴,주요메뉴'.split(",")[aa]+
							'(는)은 한글만 입력이 가능합니다')
					return;
				}
			}
			
			document.modfrm.submit();
		}
		}


//else if(!(/^\d{4,6}$/.test($('#rprice1').val())) && 
//		!(/^\d{4,6}$/.test($('#rprice2').val()))){
//alert('가격 정보 입력값을 확인해주세요')
//
//}