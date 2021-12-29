package mypage_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.ReviewDAO;
import interface_p.Executor;

public class reviewDetail implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		int nowPage = 1;
		
		if(request.getParameter("nowPage")!=null) {
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		}
		
		int nono = Integer.parseInt(request.getParameter("cno"));

		request.setAttribute("onrv", new ReviewDAO().oneReview(nono));
		request.setAttribute("nowPage", nowPage);
	}

}
