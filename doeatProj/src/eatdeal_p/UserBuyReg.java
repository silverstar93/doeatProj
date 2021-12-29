package eatdeal_p;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import db_p.DealDAO;
import db_p.DealDTO;
import db_p.PayCoupDAO;
import db_p.PayCoupDTO;
import db_p.PaymentDAO;
import db_p.PaymentDTO;
import db_p.UserDTO;
import interface_p.Executor;

public class UserBuyReg implements Executor {
//결제처리해
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
		PaymentDTO pto = new PaymentDTO();
		
		
		dto.setCno(Integer.parseInt(request.getParameter("cno")));
		
		DealDTO dto2 = new DealDAO().detail(dto);
		
		
		UserDTO cc = (UserDTO) request.getSession().getAttribute("mm");
		
		pto.setMemNo(cc.getMemNo());
		
		pto.setPay(dto2.getCprice());
		pto.setCno(dto2.getCno());
		pto.setRid(dto2.getRid());
		
		pto.setCardFirm(request.getParameter("bank"));
		pto.setCardNum(request.getParameter("cd_1")+"-"+
				request.getParameter("cd_2")+"-"+request.getParameter("cd_3")+"-"+
				request.getParameter("cd_4"));
		pto.setCardPw(request.getParameter("card_pass"));
		pto.setIdenNum(request.getParameter("iden_No"));
		pto.setPhoneNum(request.getParameter("ph_1")+"-"+
				request.getParameter("ph_2")+"-"+request.getParameter("ph_3"));
		pto.setValm(request.getParameter("card_mon"));
		pto.setValy(request.getParameter("card_year"));
		
		int pno = new PaymentDAO().purcharse(pto);
		
		//paycoup
		
		
		PayCoupDTO cto = new PayCoupDTO();
		cto.setRid(dto2.getRid());
		cto.setCond(2);
		cto.setPno(pno);
		cto.setCstart(dto2.getCstart());
		cto.setCend(dto2.getCend());
		cto.setMemNo(cc.getMemNo());
		cto.setPrice(dto2.getCprice());
		
		new PayCoupDAO().pur(cto);
		
		
		
		
	
		
		String msg = "결제 처리 되었습니다.";
		String goUrl = "CompletePay?pno="+pno+"&cno="+dto.getCno();
		request.setAttribute("msg", msg);
		request.setAttribute("goUrl", goUrl);
		request.setAttribute("mainUrl", "eatdeal/alert.jsp");
		//결제상세페이지 
	}

}
