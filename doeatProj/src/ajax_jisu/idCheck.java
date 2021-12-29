package ajax_jisu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.UserDAO;
import interface_p.Executor;

public class idCheck implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		request.setAttribute("ids", new UserDAO().idChk(request.getParameter("id")));
		
	}

}
