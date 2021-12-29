package mypage_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.DealDAO;
import db_p.DealDTO;

import interface_p.Executor;

public class AdminDetail implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		DealDTO dto = new DealDTO();
		dto.setCno(Integer.parseInt(request.getParameter("cno")));

		DealDAO dao = new DealDAO();

		int nowPage = 1;
		
		if(request.getParameter("rfnowPage")!=null) {
			nowPage = Integer.parseInt(request.getParameter("rfnowPage"));
		}
		
		request.setAttribute("dto", dao.detail(dto));
		request.setAttribute("nowPage", nowPage);
	}

}
