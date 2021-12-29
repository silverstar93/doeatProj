package mypage_p;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.RestaurantDTO;
import db_p.ReviewDAO;
import db_p.UserDAO;
import db_p.UserDTO;
import interface_p.Executor;

public class resAcp implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		int page = 1;
		int limit = 7;
		int pageLimit = 4;
		
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int total = new UserDAO().pttotal(92);
		int totalPage = total/limit;
				
				if(total%limit>0)
					totalPage++;
				
				int startPage = (page-1)/pageLimit*pageLimit+1;
				
		int endPage = startPage+pageLimit-1;
			
		if(endPage>totalPage)
			endPage = totalPage;
		
		int start = (page-1)*limit;
		
		request.setAttribute("start", start);
		request.setAttribute("nowPage", page);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalPage", totalPage);
		
		
		ArrayList<UserDTO> daoList = new UserDAO().waitList(start, limit);

		ArrayList<RestaurantDTO> restaurant = new ArrayList<RestaurantDTO>();

		RestaurantDTO ves = null;

		for (UserDTO dd : daoList) {

			ves = new RestaurantDTO();

			ves.setMemNo(dd.getMemNo());
			ves.setGrade(dd.getGrade());

			String[] inf = dd.getFavMenu().split("/");
			Integer.parseInt(inf[0]);
			ves.setRno(Integer.parseInt(inf[0]));
			ves.setRname(inf[1]);
			ves.setRowner(inf[2]);
			ves.setRaddr(inf[3]);
			ves.setRtel(inf[4]);

			restaurant.add(ves);
		}

		request.setAttribute("waitlist", restaurant);

	}

}
