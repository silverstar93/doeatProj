package mypage_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.ResDetailDAO;
import db_p.UserDTO;
import interface_p.Executor;

public class resmine implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		UserDTO cc = (UserDTO) request.getSession().getAttribute("mm");
		int basic = new ResDetailDAO().identify(cc.getMemNo());

		request.setAttribute("goUrl", "../restaurant/ResDetail?rid=" + basic);
		request.setAttribute("mainUrl", "alert.jsp");

	}
}
