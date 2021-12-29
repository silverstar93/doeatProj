package mypage_p;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.UserDAO;
import db_p.UserDTO;
import interface_p.Executor;

public class uModifyReg implements Executor {

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
		UserDAO dao = new UserDAO();
		UserDTO cc = (UserDTO) request.getSession().getAttribute("mm");

		request.setAttribute("goUrl", "index");

		dto.setMemNo(cc.getMemNo());
		dto.setPw(request.getParameter("pw"));

		if (!dao.chk(dto)) {
			request.setAttribute("msg", "비밀번호를 다시 확인해주세요.");
			request.setAttribute("goUrl", "uModify?mem=" + cc.getMemNo());
			request.setAttribute("mainUrl", "mypage/alert.jsp");
			return;
		}

		dto.setPhone(request.getParameter("tel1") + "-" + request.getParameter("tel2") + "-" + request.getParameter("tel3"));
	
		dto.setFavMenu(request.getParameter("favMenu"));
		dto.setMyLocal(request.getParameter("myLocal"));
		dto.setQuestion(request.getParameter("question"));
		dto.setAnswer(request.getParameter("answer"));

		if (cc.getCate() == 2) {
			dto.setFavMenu(cc.getFavMenu());
			dto.setMyLocal(cc.getMyLocal());
		}

		dao.userModify(dto);

		request.getSession().invalidate();

		request.getSession().setAttribute("mm", dao.detail(dto));

		request.setAttribute("mainUrl", "mypage/gogo.jsp");

	}

}
