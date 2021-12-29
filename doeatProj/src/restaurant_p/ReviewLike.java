package restaurant_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.ReviewDAO;
import db_p.ReviewDTO;
import interface_p.Executor;

public class ReviewLike implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		ReviewDTO dto = new ReviewDTO();
		
		dto.setRid(Integer.parseInt(request.getParameter("rid")));
		dto.setMemNo(Integer.parseInt(request.getParameter("memNo")));
		dto.setcno(Integer.parseInt(request.getParameter("cno")));
		
		new ReviewDAO().addLike(dto);
		
		//request.setAttribute("mainUrl", "restaurant/ResDetail.jsp?rid="+request.getParameter("rid"));		
		
		//request.setAttribute("msg", msg);
		request.setAttribute("goUrl", "ResDetail?rid="+request.getParameter("rid"));
		request.setAttribute("mainUrl", "alert.jsp");
		
	}

}
