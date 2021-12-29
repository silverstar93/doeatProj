package eatlist_p;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.HashtagDAO;
import db_p.RecommDAO;
import db_p.RecommDTO;
import db_p.ResDetailDTO;
import db_p.ResInfDAO;
import db_p.UserDAO;
import db_p.UserDTO;
import interface_p.Executor;


public class Home implements Executor {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String [] location = {
				 "서울","경기","인천","대구","부산","제주","대전","광주","강원도","경상남도","경상북도",
				 "전라남도","전라북도","충청남도","충청북도","울산","세종"
			}; 
		
		HashMap<String, String[]> locD = new HashMap<>();
		
		locD.put("서울", new String[] {"강남역","가로수길","서초","이태원","반포","송파/가락","신천/잠실","명동","여의도","역삼/선릉","청담"});
		
		String [] menu = {
				"양식","한식","일식","중식","기타"
		};
		
		System.out.println(locD.get("서울"));
		for (String tt : locD.get("서울")) {
			System.out.println(tt);
		}
		
		
	
		/* 가게 이름 출력 방법
		 * for (RecommDTO dto : hashA) {
				System.out.println(new ResInfDAO().srchName(dto.getRid()));
				
			}*/
		if(request.getSession().getAttribute("mm")!=null) {
			
		UserDTO dto = (UserDTO)request.getSession().getAttribute("mm");
		
		if(dto.getId()!=null) {
		Recomm recomm = new Recomm();
		
		ArrayList<RecommDTO> stats  = (ArrayList<RecommDTO>) recomm.rcCal(dto);
		
		request.setAttribute("stats", stats);
		request.setAttribute("tt",recomm.test());
		}
		}
	
		request.setAttribute("menu", menu);
		request.setAttribute("location", location);
		request.setAttribute("locD", locD);
		
		
	}

}
