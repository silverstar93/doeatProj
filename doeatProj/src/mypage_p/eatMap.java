package mypage_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.ResInfDAO;
import db_p.ResInfDTO;
import db_p.UserDAO;
import db_p.UserDTO;
import interface_p.Executor;

public class eatMap implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		UserDTO dto = new UserDAO().detail((UserDTO) request.getSession().getAttribute("mm"));
		
		String rid = dto.getBookmark();
		if (rid != null) {
			String[] rr = rid.split(",");

			String addr = "";
			String tit = "";

			for (String ss : rr) {
				ResInfDTO dd = new ResInfDAO().getAddr(ss);
				addr += dd.getRaddr() + ",";
				tit += dd.getRname() + ",";
			}

			System.out.println(addr);
			request.setAttribute("addr", addr.substring(0, addr.length() - 1));
			request.setAttribute("tit", tit.substring(0, tit.length() - 1));
			request.setAttribute("rid", rid.substring(0, rid.length() - 1));
		}

	}

}
