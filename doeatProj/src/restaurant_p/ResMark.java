package restaurant_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.ReviewDAO;
import db_p.ReviewDTO;
import db_p.UserDAO;
import interface_p.Executor;

public class ResMark implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String rid = request.getParameter("rid");
		int member = Integer.parseInt(request.getParameter("memNo"));
		//로그인된 사용자는 나중에 추가
		
		new UserDAO().bookmark(rid, member);
		
		request.setAttribute("goUrl", "ResDetail?rid="+rid);
		request.setAttribute("mainUrl", "alert.jsp");
		
	}

}
