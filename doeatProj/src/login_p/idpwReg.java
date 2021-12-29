package login_p;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.UserDAO;
import db_p.UserDTO;
import interface_p.Executor;

public class idpwReg implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		UserDTO dto = new UserDTO();
		dto.setName(request.getParameter("name"));
		String dd=request.getParameter("bdate");
		String mm=request.getParameter("bmonth");
		if(dd.length()<2) {
			dd="0"+dd;
		}
		if(mm.length()<2) {
			mm="0"+mm;
		}
		dto.setBirth(request.getParameter("byear")+"-"+mm+"-"+dd);
		dto.setMail(request.getParameter("mail1")+"@"+request.getParameter("mail2"));
		
		String msg="";
		
		if("1".equals(request.getParameter("kind"))) {
			
			msg = new UserDAO().idSearch(dto);
			if("".equals(msg)) {
				msg="아이디를 찾을 수 없습니다.";
				request.setAttribute("goUrl", "idpwSch?kind=1");
			}else {
				msg="아이디 : "+msg;
				request.setAttribute("goUrl", "index");
			}
			
		} else if("2".equals(request.getParameter("kind"))) {
			dto.setId(request.getParameter("id"));
			dto.setQuestion(request.getParameter("question"));
			dto.setAnswer(request.getParameter("answer"));
			
			msg = new UserDAO().pwSearch(dto);
			if("".equals(msg)) {
				msg="비밀번호를 찾을 수 없습니다.";
				request.setAttribute("goUrl", "idpwSch?kind=2");
			}else {
				msg="비밀번호 : "+msg;
				request.setAttribute("goUrl", "index");
			}
			
			
		}
		

		
		request.setAttribute("msg", msg);
		
		request.setAttribute("mainUrl", "mypage/alert.jsp");
		
		
		

	}

}
