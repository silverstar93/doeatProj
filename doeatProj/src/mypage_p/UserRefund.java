package mypage_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.DealDAO;
import db_p.PayCoupDAO;
import db_p.PaymentDAO;
import interface_p.Executor;

public class UserRefund implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		int pno = Integer.parseInt(request.getParameter("pno"));
		int nowPage = 1;
		
		if(request.getParameter("rfnowPage")!=null) {
			nowPage = Integer.parseInt(request.getParameter("rfnowPage"));
		}
		
		new PayCoupDAO().refund(pno);
		new DealDAO().refund(pno);
		request.setAttribute("ref", new PaymentDAO().refund(pno));

		String msg = "환불 처리 되었습니다.";
		String goUrl = "myCoupon?rfnowPage="+nowPage;
		request.setAttribute("msg", msg);
		request.setAttribute("goUrl", goUrl);
		request.setAttribute("mainUrl", "alert.jsp");

	}

}
