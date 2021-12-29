package mypage_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.UserDTO;
import interface_p.Executor;

public class UserRefundReg implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		int memNo = ((UserDTO) request.getSession().getAttribute("mm")).getMemNo();
		String cardPw = request.getParameter("cardPw");
		System.out.println("in userRefundReg memNo : " + memNo + " cardPw : " + cardPw);
		// memNo 마이페이지들어올때 확인해서 결제창까지 가져오기
		// new PayCoupDAO().refund(memNo, cardPw);
		// request.setAttribute("buyinfo", new PayCoupDAO().mylist(memNo));
		// 유효성 체크
//		int rid = Integer.parseInt(request.getParameter("rid"));
//		int cvolume = Integer.parseInt(request.getParameter("volume"));
		// new DealDAO().refund(cvolume, rid);
		String msg = "환불 처리 되었습니다.";
		String goUrl = "myCoupon";
		request.setAttribute("msg", msg);
		request.setAttribute("goUrl", goUrl);
		request.setAttribute("mainUrl", "mypage/alert.jsp");
	}

}
