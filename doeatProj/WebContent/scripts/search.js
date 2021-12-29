

function srchChk() {
	
	var search = $('#search').val();
	  
	
	var tt = /^[.!@#$%^&*_]/ ;
	var tt2 = /^ +$/g;		
	if(tt.test(search)){
		alert("특수문자는 검색이 불가능합니다.");
	}else if(search=="null"||tt2.test(search)||search==""){
		alert("검색  내용을 입력하여주십시오.");
	}else{		
			document.srch.submit();
		
	};
		
}


function dealChk() {
	
	var deal = $('#deal').val();
	  
	
	var tt = /^[.!@#$%^&*_]/ ;
	var tt2 = /^ +$/g;		
	if(tt.test(deal)){
		alert("특수문자는 검색이 불가능합니다.");
	}else if(deal=="null"||tt2.test(deal)||deal==""){
		alert("검색  내용을 입력하여주십시오.");
	}else{		
			document.dealsrch.submit();
		
	};
		
}


function codeChk() {
	
	var code = $('#code').val();
	
	var tt = /^[.!@#$%^&*_]/ ;  
	var tt2 = /^ +$/g;
	var tt3 =/^\d+$/;
	
	 if(!tt3.test(code)) {
		alert("쿠폰번호는 숫자로만 검색이 가능합니다.");
	}
	else{		
		document.codeSearch.submit();  
		
	};
		
}

