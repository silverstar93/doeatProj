package mypage_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.DealDAO;
import db_p.ResDetailDAO;
import interface_p.Executor;

public class partnerDetail implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		int nowPage = 1;
		
		if(request.getParameter("rfnowPage")!=null) {
			nowPage = Integer.parseInt(request.getParameter("rfnowPage"));
		}
		
		int basic = new ResDetailDAO().identify(Integer.parseInt(request.getParameter("memNo")));

		DealDAO dd = new DealDAO();

		request.setAttribute("dChk", dd.chkDeal(basic));
		request.setAttribute("cChk", dd.chkCoup(basic));

		System.out.println(dd.chkCoup(basic));

		request.setAttribute("nowPage", nowPage);
		request.setAttribute("rid", basic);
		request.setAttribute("memNo", request.getParameter("memNo"));

		dd.close();
	}
}
