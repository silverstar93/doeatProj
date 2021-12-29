package eatdeal_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.DealDAO;
import db_p.DealDTO;
import db_p.UserDAO;
import db_p.UserDTO;
import interface_p.Executor;

public class Point implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		if(request.getSession().getAttribute("mm")==null) {
			String goUrl = "/doeatProj/login/index";
			request.setAttribute("goUrl", goUrl);
			request.setAttribute("msg","로그인 후 이용해 주세요");
			request.setAttribute("mainUrl", "eatdeal/alert.jsp");
		}else if(request.getSession().getAttribute("mm")!=null) {
		int point = new UserDAO().detail((UserDTO)request.getSession().getAttribute("mm")).getPoint();		
		request.setAttribute("point", point);
		DealDTO dto = new DealDTO();		
		dto.setCno(Integer.parseInt(request.getParameter("cno")));	
		request.setAttribute("data", new DealDAO().detail(dto));
		}
		
	
	}

}
