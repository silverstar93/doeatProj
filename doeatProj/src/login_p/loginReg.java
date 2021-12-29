package login_p;

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
import mypage_p.DDoit;

public class loginReg implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

	
		UserDAO dao = new UserDAO();
		
		ArrayList<UserDTO> daoList =  dao.list();
		
		boolean logchk=true;
		
		
		for(UserDTO mm : daoList){
			
			if(id.equals(mm.getId())&&pw.equals(mm.getPw())){
				
				//if(mm.getGrade()==0&&today.getDate()==1) {
				if(mm.getGrade()==0) {
				DDoit dd = new DDoit();
				dd.areU();
				} else if(mm.getCate()==1) {
					ArrayList<Integer> pnos = new PayCoupDAO().refundPno(mm.getMemNo());
					
					for (Integer pno : pnos) {
						new PayCoupDAO().refund(pno);
						new DealDAO().refund(pno);
						new PaymentDAO().refund(pno);
					}
				} else if(mm.getCate()==2) {
					
					int basic = new ResDetailDAO().identify(mm.getMemNo());
					new PayCoupDAO().refundAuto(basic);
				}
				request.getSession().setAttribute("mm",mm);

				
				request.setAttribute("goUrl", "http://localhost:8080/doeatProj/eatlist/Home");
				request.setAttribute("mainUrl", "mypage/gogo.jsp");
				
				logchk=false;
				
				break;
			}
		}

	if(logchk) {
			request.setAttribute("msg", "아이디 또는 비밀번호를 다시 확인하세요.");
			request.setAttribute("goUrl", "index");
			request.setAttribute("mainUrl", "mypage/alert.jsp");
	}


	}

}
