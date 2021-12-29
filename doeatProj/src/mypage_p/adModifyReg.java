package mypage_p;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.UserDAO;
import db_p.UserDTO;
import interface_p.Executor;

public class adModifyReg implements Executor {

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

		request.setAttribute("goUrl", "uDetail?mem=" + request.getParameter("memNo"));

		dto.setMemNo(Integer.parseInt(request.getParameter("memNo")));
		dto.setName(request.getParameter("name"));
		String dd = request.getParameter("bdate");
		String mm = request.getParameter("bmonth");
		if (dd.length() < 2) {
			dd = "0" + dd;
		}
		if (mm.length() < 2) {
			mm = "0" + mm;
		}
		dto.setBirth(request.getParameter("byear") + "-" + mm + "-" + dd);
		dto.setGen(request.getParameter("gen"));
		dto.setPhone(request.getParameter("tel1") + "-" + request.getParameter("tel2") + "-" + request.getParameter("tel3"));
		dto.setFavMenu(request.getParameter("favMenu"));
		dto.setMyLocal(request.getParameter("myLocal"));
		dto.setQuestion(request.getParameter("question"));
		dto.setAnswer(request.getParameter("answer"));
		dto.setPoint(Integer.parseInt(request.getParameter("point")));

		UserDAO dao = new UserDAO();
		dao.modify(dto);

		request.setAttribute("mainUrl", "mypage/gogo.jsp");

	}

}
