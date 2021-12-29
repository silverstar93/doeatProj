function doubleChk(){	
	 var u = $('#rno').val() 
	 var tt = /^\d{9}$/ 
		 // 앞뒤로 막아주어야함
		if(!tt.test(u)){
			
			alert("9자리의 숫자만 입력이 가능합니다")
			$('#rno').removeAttr('readOnly');
			
		}else{
		 
		$.ajax('../ajax_song/RnoChk?rno='+u, {	
			success:function(dd){
				
				var msg = "등록 가능한 번호입니다"
				
				if(eval(dd)==false){
					msg = "이미 등록된 사업자 번호입니다"						
				}
				
				if(confirm(msg)){ 					
					if(msg=="이미 등록된 사업자 번호입니다"){
						$('#rno').val('')						
						$('#rno').removeAttr('readOnly');
					}else{
						$('#rno').attr('readOnly', true);
						$('#btn').val('식당등록')
						$('#dck').val('중복확인완료')
					}	                
	            }else{
	                
	            } 			
			},
			error:function(){
				alert("중복체크에 실패했습니다")
			}								
		}); // ajax
		}
}

function regchecked(){			

		var numChk = {  '/^([가-힣]+)$/':$('#rname').val(),
						'/^([가-힣]+)$/':$('#rowner').val()} //,
//						'/^\\d{2,3}$/':$('#rtel1').val(),
//						'/^\\d{3,4}$/':$('#rtel2').val(),
//						'/^\\d{4}$/':$('#rtel3').val(),
//						'/^[가-힣]$/':$('#rloc2').val(),
//						'/^[가-힣]$/':$('#rmenu2').val(),			
//						'/^\\d{4,6}$/' :$('#rprice1').val(),
//						'/^\\d{4,6}$/':$('#rprice2').val(),
//						'/^[가-힣]/':$('#rmain1').val(),
//						'/^[가-힣]/':$('#rmain2').val(),
//						'/^[가-힣]/':$('#rmain3').val()}	
		
		var rePttn = ['/^([가-힣]+)$/','/^([가-힣]+)$/','/^\\d{2,3}$/','/^\\d{3,4}$/',
			'/^\\d{4}$/','/^([가-힣]+)$/',
			'/^([가-힣]+)$/','/^\\d{4,6}$/','/^\\d{4,6}$/',
			'/^([가-힣]+)$/','/^([가-힣]+)$/','/^([가-힣]+)$/']
		
		var chkObj = [$('#rname').val(),$('#rowner').val(),$('#rtel1').val(),$('#rtel2').val(),$('#rtel3').val(),
			$('#rloc2').val(),$('#rmenu2').val(),$('#rprice1').val(),
			$('#rprice2').val(),$('#rmain1').val(),$('#rmain2').val(),$('#rmain3').val()]
		
		
		
		if($('#dck').val()!='중복확인완료'){
			alert('사업자 등록번호 중복체크를 해주세요')
		}else if(eval($('#rprice1').val())>eval($('#rprice2').val())){
			alert('가격대는 낮은 금액에서 높은 금액 순으로 입력해주세요')
		}else{
			
			for (var i = 0; i <chkObj.length; i++) {
				if(eval(rePttn[i]).test(chkObj[i])){
					console.log(eval(rePttn[i]).test(chkObj[i]))
					console.log(rePttn[i]+chkObj[i])
				}else{
					console.log(i)
					alert('상호명,사업자,전화번호,전화번호,전화번호,장소(#소분류),메뉴(#소분류),가격정보,가격정보,주요메뉴,주요메뉴,주요메뉴'.split(",")[i]+'의 입력값을 확인해주세요')
					return;
				}
				
			}
			
			if($('#sample4_roadAddress').val()=="" || $('#sample4_roadAddress').val()==null){
				alert('주소를 입력해주세요')
			}else{			
			document.frm.submit();
			}
		}
}

function reqchecked(){		
	
	if($('#dck').val()!='중복확인완료'){
		alert('사업자 등록번호 중복체크를 해주세요')
	}else{		
		document.acfrm.submit();
	}
}

$(document).ready(function(){
	$('#regpic').on({
		
	 	'mouseover':function(){
				$('.regpic').css({
					'display':'inline'
				})
		},
		'mouseout':function(){
				$('.regpic').css({
					'display':'none'
				})
		} 
	})

	$('#regpic2').on({
		
		'mouseover':function(){
			$('.regpic2').css({
				'display':'inline'
			})
	},
	'mouseout':function(){
			$('.regpic2').css({
				'display':'none'
			})
	} 
		
	})
})