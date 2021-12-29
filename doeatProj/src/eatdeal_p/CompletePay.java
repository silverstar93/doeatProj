package eatdeal_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.CardDTO;
import db_p.DealDAO;
import db_p.DealDTO;
import db_p.PayCoupDAO;
import db_p.PayCoupDTO;
import db_p.PaymentDAO;
import db_p.PaymentDTO;
import db_p.UserDTO;
import interface_p.Executor;

public class CompletePay implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		PaymentDTO pto = new PaymentDTO();
		pto.setPno(Integer.parseInt(request.getParameter("pno")));
		
		request.setAttribute("cto", new PayCoupDAO().compaylist(pto.getPno()));
		System.out.println(Integer.parseInt(request.getParameter("pno")));
		
		request.setAttribute("pto", new PaymentDAO().detail(pto));
		
		DealDTO dto = new DealDTO();
		dto.setCno(Integer.parseInt(request.getParameter("cno")));
		request.setAttribute("res", new DealDAO().menu(dto.getCno()));
		
		
		
	}

}
