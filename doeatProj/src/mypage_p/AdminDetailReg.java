package mypage_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.DealDAO;
import interface_p.Executor;

public class AdminDetailReg implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		System.out.println(request.getParameter("cno"));

		new DealDAO().approval(1, Integer.parseInt(request.getParameter("cno")));

		int nowPage = 1;
		
		if(request.getParameter("rfnowPage")!=null) {
			nowPage = Integer.parseInt(request.getParameter("rfnowPage"));
		}
		
		String msg = "승인 처리 되었습니다.";
		String goUrl = "deal?page="+nowPage;
		request.setAttribute("msg", msg);
		request.setAttribute("goUrl", goUrl);
		request.setAttribute("mainUrl", "mypage/alert.jsp");

	}

}
