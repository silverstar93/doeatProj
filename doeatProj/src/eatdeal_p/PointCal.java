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

public class PointCal implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		int point = Integer.parseInt(request.getParameter("point"));
		int cno=Integer.parseInt(request.getParameter("cno"));
		
		DealDTO dto = new DealDTO();		
		dto.setCno(cno);		
	
		
		point = point- new DealDAO().detail(dto).getCprice();
		
		String goUrl = "List2?point="+point;
		if(point<0) {			
			request.setAttribute("goUrl", goUrl);
			request.setAttribute("msg","포인트가 부족합니다");
			request.setAttribute("mainUrl", "eatdeal/alert.jsp");
		}else {
			
			PaymentDTO pto = new PaymentDTO();
		
			
			dto = new DealDAO().detail(dto);
			
			
			UserDTO cc = (UserDTO) request.getSession().getAttribute("mm");
			cc.setPoint(point);
			new UserDAO().useP(cc);
			
			pto.setMemNo(cc.getMemNo());			
			pto.setPay(dto.getCprice());
			pto.setCno(dto.getCno());
			pto.setRid(dto.getRid());			
			pto.setCardFirm("포인트");
						
			int pno = new PaymentDAO().purcharse(pto);
			
			//paycoup
			
			
			PayCoupDTO cto = new PayCoupDTO();
			cto.setRid(dto.getRid());
			cto.setCond(2);
			cto.setPno(pno);
			cto.setCstart(dto.getCstart());
			cto.setCend(dto.getCend());
			cto.setMemNo(cc.getMemNo());
			cto.setPrice(dto.getCprice());
			
			new PayCoupDAO().pur(cto);
			
			
			
			goUrl = "PointReg?point="+point+"&cno="+cno+"&pno="+pno;
			request.setAttribute("goUrl", goUrl);
			request.setAttribute("msg","결제가 완료되었습니다.");
			request.setAttribute("mainUrl", "eatdeal/alert.jsp");
		}

	}

}
