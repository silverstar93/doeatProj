package eatdeal_p;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.DealDAO;
import db_p.PayCoupDAO;
import db_p.UserDTO;
import interface_p.Executor;

public class List2 implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		
		if(request.getParameter("search")!=null) {     
			String str = request.getParameter("search");
			request.setAttribute("list", new DealDAO().dealSearch(str));
			
		}else {
			
		request.setAttribute("list", new DealDAO().codelist());}
		
		System.out.println("in list2");
	
	}

}
