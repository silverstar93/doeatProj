package mypage_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.DealDAO;
import interface_p.Executor;

public class delPartReg implements Executor {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		int nowPage = 1;
		
		if(request.getParameter("rfnowPage")!=null) {
			nowPage = Integer.parseInt(request.getParameter("rfnowPage"));
		}
		
		new DealDAO().outChk(Integer.parseInt(request.getParameter("rid")),
				Integer.parseInt(request.getParameter("memNo")));

		request.setAttribute("msg", "파트너탈퇴가 완료되었습니다");
		request.setAttribute("goUrl", "partner?page="+nowPage);
		// 작성 후, 디테일로 빠지게 나중에 수정!
		request.setAttribute("mainUrl", "mypage/alert.jsp");

	}
}
