package login_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import interface_p.Executor;

public class joinForm implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String [] location = {
				 "서울","경기","인천","대구","부산","제주","대전","광주","강원도","경상남도","경상북도",
				 "전라남도","전라북도","충청남도","충청북도","울산","세종"
			}; 


		String [] menu = {
				"한식","양식","중식","일식","기타"
		};

		String [] que = {
				"기억에 남는 추억의 장소는?","자신의 보물 제1호는?","가장 기억에 남는 선생님 성함은?","유년시절 가장 생각나는 친구 이름은?",
				"인상깊게 읽은 책 이름은?","가장 존경하는 인물은?"
		};

		String [] tel = {
				"010","011","016","017","018","019"
		};
		
		String [] rtel = {
				"02","031","032","033","041","042","043","044","051","052","053","054","055","061","062","063","064"
		};

		
		request.setAttribute("location", location);
		request.setAttribute("menu", menu);
		request.setAttribute("que", que);
		request.setAttribute("tel", tel);
		request.setAttribute("rtel", rtel);

	}

}
