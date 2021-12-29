package eatdeal_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;

import com.sun.org.apache.regexp.internal.recompile;

import db_p.DealDAO;
import db_p.DealDTO;
import db_p.PayCoupDTO;
import db_p.PaymentDTO;
import interface_p.Executor;
//ccode 
//최종결제 가격 pay
public class UserBuyForm implements Executor {
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		if(request.getSession().getAttribute("mm")==null) {
			String goUrl = "/doeatProj/login/index";
			request.setAttribute("goUrl", goUrl);
			request.setAttribute("msg","로그인 후 이용해 주세요");
			request.setAttribute("mainUrl", "eatdeal/alert.jsp");
		}else if(request.getSession().getAttribute("mm")!=null) {
		DealDTO dto = new DealDTO();

		dto.setCno(Integer.parseInt(request.getParameter("cno")));

		request.setAttribute("data", new DealDAO().detail(dto));
		
		}
	}

}
