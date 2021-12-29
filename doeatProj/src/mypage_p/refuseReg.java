package mypage_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.DealDAO;
import interface_p.Executor;

public class refuseReg implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("" + request.getParameter("cno"));

		new DealDAO().approval(3, Integer.parseInt(request.getParameter("cno")));

		request.setAttribute("msg", "확인");
		request.setAttribute("goUrl", "deal");
		request.setAttribute("mainUrl", "mypage/alert.jsp");
		System.out.println(request.getParameter("rid"));

		// new DealDAO().refuse(aa);
	}

}
