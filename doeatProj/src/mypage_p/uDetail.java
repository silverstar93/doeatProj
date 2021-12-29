package mypage_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.DealDAO;
import db_p.ResDetailDAO;
import db_p.UserDAO;
import db_p.UserDTO;
import interface_p.Executor;

public class uDetail implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		int nowPage = 1;
		if(request.getParameter("nowPage")==null||request.getParameter("nowPage").equals("")) {
			request.setAttribute("nowPage",1);
			
		}else {
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
			request.setAttribute("nowPage",nowPage);
		}
			
			
		UserDTO dto = new UserDTO();
		UserDAO dao = new UserDAO();

		dto.setMemNo(Integer.parseInt(request.getParameter("mem")));
		dto = dao.detail(dto);
		request.setAttribute("dto", dto);
		request.setAttribute("gennowPage",nowPage);
		
		if(dto.getCate()==2) {
			int basic = new ResDetailDAO().identify(Integer.parseInt(request.getParameter("mem")));

			DealDAO dd = new DealDAO();

			request.setAttribute("dChk", dd.chkDeal(basic));
			request.setAttribute("cChk", dd.chkCoup(basic));
		}
		

	}

}
