package eatdeal_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.DealDAO;
import db_p.DealDTO;
import db_p.PayCoupDAO;
import db_p.PayCoupDTO;
import db_p.PaymentDAO;
import db_p.PaymentDTO;
import db_p.UserDAO;
import db_p.UserDTO;
import interface_p.Executor;

public class PointReg implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		int point = Integer.parseInt( request.getParameter("point"));
		request.setAttribute("point", point);
		
		DealDTO dto = new DealDTO();		
		dto.setCno(Integer.parseInt(request.getParameter("cno")));		
		request.setAttribute("data", new DealDAO().detail(dto));
		
		PayCoupDTO pdto = new PayCoupDTO();
		pdto.setPno(Integer.parseInt(request.getParameter("pno")));
		request.setAttribute("code", new PayCoupDAO().detail(pdto));
	
	}

}
