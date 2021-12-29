package mypage_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.PayCoupDAO;
import interface_p.Executor;

public class comReg implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		String ccode = (String) request.getParameter("ccode");

		new PayCoupDAO().usedCoup(ccode);
		
		int nowPage = 1;
		if(!(request.getParameter("nowPage")== null||request.getParameter("nowPage").equals(""))) {
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		}
			

		request.setAttribute("msg", "사용 처리가 완료되었습니다.");
		request.setAttribute("goUrl", "salesRep?nowPage="+nowPage);
		request.setAttribute("mainUrl", "mypage/alert.jsp");

	}

}
