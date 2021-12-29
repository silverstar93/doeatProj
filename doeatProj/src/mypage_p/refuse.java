package mypage_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.DealDAO;
import interface_p.Executor;

public class refuse implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		new DealDAO().approval(3, Integer.parseInt(request.getParameter("cno")));

		int nowPage = 1;
		
		if(request.getParameter("rfnowPage")!=null) {
			nowPage = Integer.parseInt(request.getParameter("rfnowPage"));
		}
		
		request.setAttribute("msg", "확인");
		request.setAttribute("goUrl", "deal?page="+nowPage);
		request.setAttribute("mainUrl", "mypage/alert.jsp");
	}

}
