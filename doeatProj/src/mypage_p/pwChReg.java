package mypage_p;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.UserDAO;
import db_p.UserDTO;
import interface_p.Executor;

public class pwChReg implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		UserDTO cc = (UserDTO) request.getSession().getAttribute("mm");

		UserDTO dto = new UserDTO();

		dto.setMemNo(cc.getMemNo());

		if (cc.getPw().equals(request.getParameter("pw"))) {

			dto.setPw(request.getParameter("chpw"));
			new UserDAO().pwChange(dto);

			request.getSession().invalidate();

			request.getSession().setAttribute("mm", new UserDAO().detail(dto));

			request.setAttribute("msg", "비밀번호가 변경되었습니다.");
			request.setAttribute("goUrl", "uModify");
			request.setAttribute("mainUrl", "mypage/alert.jsp");

		} else {
			request.setAttribute("msg", "비밀번호가 일치하지 않습니다.");
			request.setAttribute("goUrl", "pwCh");
			request.setAttribute("mainUrl", "mypage/alert.jsp");
		}

	}

}
