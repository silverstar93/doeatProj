		
		function dealReqChk() {
			
			var cmenu = $('#cmenu').val();
			var orgprice = $('#orgprice').val();
			var pyear = $('#pyear').val();
			var cyear = $('#cyear').val();
			var pmonth = $('#pmonth').val();
			var cmonth =$('#cmonth').val()
			var pday = $('#pday').val();
			var cday = $('#cday').val();
			
			var date = new Date();
			
			var tt1 = /^[가-힣]/;
			var tt2 =/^\d/;
			var tt3 =/^\d{4}/;
			var tt4 = /^\d{2}/;
			
			
				
			if(!tt1.test(cmenu)){
				alert("매뉴 이름은 한글만 입력 가능합니다.");
			}else if(!tt2.test(orgprice)){
				alert("원가는 숫자 형식으로만 입력 가능합니다.");
			}else if(!tt3.test(pyear)||!tt3.test(cyear)){
				alert("날짜는 숫자 형식(yyyy)으로만 입력 가능합니다.");
				
			}else if(!tt4.test(pmonth)||!tt4.test(cmonth)||pmonth<1||pmonth>12||cmonth<1||cmonth>12){
				alert("날짜는 숫자 형식(mm)으로만 입력 가능합니다.");
				
			}else if(!tt4.test(pday)||!tt4.test(cday)||pday<1||pday>31||cday<1||cday>31){
				alert("날짜는 숫자 형식(dd)으로만 입력 가능합니다.");
				
			}else{
				
				var pdate = new Date($('#pyear').val(),$('#pmonth').val()-1,$('#pday').val());
				var cdate = new Date($('#cyear').val(),$('#cmonth').val()-1,$('#cday').val());
				
				
				if(cdate.getTime()-date.getTime()<86400*1000){
					alert("쿠폰 개시일 날짜를 확인해 주십시오.");
					
				}else if(pdate.getTime()-date.getTime()<86400*1000){
					alert("판매 시작일 날짜를 확인해 주십시오.");
					
				}else if(pdate.getTime()-cdate.getTime()>0){
					alert("쿠폰 개시일과 판매시작일을 확인해 주십시오.");
					
				}else{   
					document.dealReq.submit();
				};
			};
			
			
					
		
			
		}

