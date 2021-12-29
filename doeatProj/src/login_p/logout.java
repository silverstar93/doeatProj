package login_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.UserDTO;
import interface_p.Executor;

public class logout implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		try {
			String name = ((UserDTO)(request.getSession().getAttribute("mm"))).getName();
			request.setAttribute("name",name);
			request.getSession().invalidate();
		}catch (Exception e) {
			// TODO: handle exception
			request.getSession().invalidate();
			System.out.println("로그아웃 try-catch @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}
		

	}

}
