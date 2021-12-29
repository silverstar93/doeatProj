function modchecked(){		
	
		var kor = [$('#rmenu2').val(),$('#rmain1').val(),
			$('#rmain2').val(),$('#rmain3').val()]
		
		var korChk = /^([가-힣]+)$/
		
		if(!(/^\d{4,6}$/.test($('#rprice1').val())) || 
					!(/^\d{4,6}$/.test($('#rprice2').val()))){
			alert('가격 입력값을 확인해주세요')
		}else if(eval($('#rprice1').val())>eval($('#rprice2').val())){
			alert('가격대는 낮은 금액에서 높은 금액 순으로 입력해주세요')
		}else{
					
			for (aa in kor) {
				if(!korChk.test(kor[aa])){
					console.log(korChk+",s"+kor[aa])
					alert('메뉴는 한글만 입력이 가능합니다')
					return;
				}
			}
			
			document.modpat.submit();
		}
		}
		
	function goback(){
		location.href="ResDetail?rid=${detail.rid}";
	}