package db_p;

public class SrchDTO {
	
	String [] locS = {
			 "서울","경기","인천","대구","부산","제주","대전","광주","강원도","경상남도","경상북도",
			 "전라남도","전라북도","충청남도","충청북도","울산","세종"
		}; 

	String [] menuS = {
			"양식","한식","중식","일식","기타"
	};

	public String[] getLocS() {
		return locS;
	}

	public void setLocS(String[] locS) {
		this.locS = locS;
	}

	public String[] getMenuS() {
		return menuS;
	}

	public void setMenuS(String[] menuS) {
		this.menuS = menuS;
	}

	

}
