package eatdeal_p;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.DealDAO;
import db_p.DealDTO;
import db_p.PaymentDTO;
import interface_p.Executor;

public class BuyEatdeal implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DealDTO dto = new DealDTO();
		
		
		dto.setCno(Integer.parseInt(request.getParameter("cno")));
		
		request.setAttribute("data", new DealDAO().detail(dto));
		
	}

}
