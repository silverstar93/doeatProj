package mypage_p;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.UserDAO;
import db_p.UserDTO;
import interface_p.Executor;

public class uModify implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		UserDTO dto = new UserDTO();
		UserDAO dao = new UserDAO();
		
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
		
		

		UserDTO cc = (UserDTO) request.getSession().getAttribute("mm");

		
		
		request.setAttribute("location", location);
		request.setAttribute("menu", menu);
		request.setAttribute("que", que);
		request.setAttribute("tel", tel);
		
		
		
		if (cc.getCate() == 0) {
			dto.setMemNo(Integer.parseInt(request.getParameter("mem")));
			
		} else {
			dto.setMemNo(cc.getMemNo());
		}

		UserDTO dd = (UserDTO)dao.detail(dto);
		String [] barr = dd.getBirth().split("-");
		String [] parr = dd.getPhone().split("-");
		String [] earr = dd.getMail().split("@");
		request.setAttribute("dto", dd);
		request.setAttribute("barr", barr);
		request.setAttribute("parr", parr);
		request.setAttribute("earr", earr);
	}

}
