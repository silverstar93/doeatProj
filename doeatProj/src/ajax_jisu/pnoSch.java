package ajax_jisu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.DealDAO;
import db_p.PayCoupDAO;
import db_p.PaymentDAO;
import db_p.ResDetailDAO;
import db_p.UserDAO;
import db_p.UserDTO;
import interface_p.Executor;

public class pnoSch implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		UserDTO dto = new UserDTO();
		dto.setMemNo(Integer.parseInt(request.getParameter("mem")));
		dto = new UserDAO().detail(dto);
		request.setAttribute("cate",dto.getCate());
		
		if(dto.getCate()==1) {
			ArrayList<Integer> pnos = new PayCoupDAO().pnoSch(dto.getMemNo());
			
			for (Integer pno : pnos) {
				new PayCoupDAO().refund(pno);
				new DealDAO().refund(pno);
				new PaymentDAO().refund(pno);
			}
			
			new UserDAO().delete2(dto.getMemNo());
			
			request.setAttribute("goUrl", "user");
			
			
		} else if(dto.getCate()==2) {
			new UserDAO().deleteReq(dto);
			int rid = new ResDetailDAO().identify(dto.getMemNo());
			new DealDAO().freeze(rid);
			new DealDAO().outChk(rid,dto.getMemNo());
			new UserDAO().delete2(dto.getMemNo());
			request.setAttribute("goUrl", "partner");
		}
		
		request.setAttribute("mainUrl", "mypage/gogo.jsp");

	
	}

}
