package filepath_p;

import java.util.regex.Pattern;

public class RegexChk {	

	public String namePat = "[가-힣].*"; // 상호명
	public String strPat = "[가-힣].*"; // 사업자명, 위치, 소분류, 주요메뉴
	public String rnoPat = "[0-9]{9}"; // rno(9자 명시)
	public String pricePat = "[0-9]{4,}"; // 가격대(천원부터시작)
	public String [] numPat = {"[0-9]{2,3}", "[0-9]{3,4}", "[0-9]{4}"};

	public static int sno = 0;
	public static int nno = 0;
	
	public boolean strchk(String rname, String ... arr) {	
		
		sno = 0;
		// arr.length = 6이어야함
		// String rowner, String rloc2, String rmenu2, String rmain1, String rmain2, String rmain3 
		
		boolean chk = false;
		// 0이면 상호명이 틀림, 1이면 사업자, 2 장소소분류 3 메뉴 소분류, 4 이상이면 주요메뉴
		if(Pattern.matches(namePat, rname)) {			
			
			sno = 1;
			
			for(String str : arr) {				
				chk = true;		
				sno++;
				if(!Pattern.matches(strPat, str)) {
					chk = false;
					break;	
				}
			}			
		}
		return chk;
	}
	
	public boolean numchk(String rno, String price1, String price2, String num1, String num2, String num3) {
		
		nno = 0;
		
		String [] num = {num1, num2, num3};
		
		boolean chk = false;
		
		// 0이면 사업자 등록번호, 1이면 가격정보, 2면 가격정보, 3이면 전화번호
		
		if(Pattern.matches(rnoPat, rno)) { // && Pattern.matches(pricePat, price1) && Pattern.matches(pricePat, price2)) {
			
			nno = 1;
			// 여기는 배열 돌려서
			
				
				if(!Pattern.matches(pricePat, price1) || !Pattern.matches(pricePat, price2)) {					
					
					return chk;
					
				}else {
					
					nno = 2;
					
					for (int i=0; i<num.length; i++) {
						chk = true;
						nno = 3;	
						
						if(!Pattern.matches(numPat[i], num[i])) {
							chk = false;
							break;
						} 					
					}// 전화번호체크	
									
			}// 가격체크					
		}// 사업자 등록번호 체크
		
		return chk;
	}

}
