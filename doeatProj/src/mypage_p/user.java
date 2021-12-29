package mypage_p;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.ReviewDAO;
import db_p.UserDAO;
import db_p.UserDTO;
import interface_p.Executor;

public class user implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		UserDAO dao = new UserDAO();
		ArrayList<UserDTO> daoList;

		if (request.getParameter("name") != null&&!"".equals(request.getParameter("name"))) {

				daoList = dao.schName(request.getParameter("name"));
				request.setAttribute("user", daoList);
			
		} else {
			
			int page = 1;
			int limit = 7;
			int pageLimit = 4;
			
			if(request.getParameter("page")!=null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			
			int total = new UserDAO().pttotal(1);
			int totalPage = total/limit;
					
					if(total%limit>0)
						totalPage++;
					
					int startPage = (page-1)/pageLimit*pageLimit+1;
					
			int endPage = startPage+pageLimit-1;
				
			if(endPage>totalPage)
				endPage = totalPage;
			
			int start = (page-1)*limit;
			
			request.setAttribute("start", start);
			request.setAttribute("nowPage", page);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("totalPage", totalPage);
			
			daoList = dao.userList(start, limit);
			request.setAttribute("user", daoList);
		}

	}

}
