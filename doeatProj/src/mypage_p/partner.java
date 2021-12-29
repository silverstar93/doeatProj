package mypage_p;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.ReviewDAO;
import db_p.UserDAO;
import db_p.UserDTO;
import interface_p.Executor;

public class partner implements Executor {

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
		
		int page = 1;
		int limit = 7;
		int pageLimit = 4;
		
		if(request.getParameter("page")!=null) {			
			page = Integer.parseInt(request.getParameter("page"));
		}

		
		
		if(request.getParameter("tap")!=null) {
			
			// 탈퇴 회원 관리
			
			if(Integer.parseInt(request.getParameter("tap"))==1) {
				
				int total = new UserDAO().pttotal(28);
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
				
				daoList = dao.partnerList(start, limit);
				request.setAttribute("partner", daoList);
			}
		
		}else {
			if (request.getParameter("name") != null&&!"".equals(request.getParameter("name"))) {
				daoList = dao.schName2(request.getParameter("name"));
				request.setAttribute("user", daoList);
			} else {
								
				// 일반 파트너회원
				
				int gentotal = new UserDAO().pttotal(2);
				int gentotalPage = gentotal/limit;
						
						if(gentotal%limit>0)
							gentotalPage++;
						
						int genstartPage = (page-1)/pageLimit*pageLimit+1;
						
				int genendPage = genstartPage+pageLimit-1;
					
				if(genendPage>gentotalPage)
					genendPage = gentotalPage;
				
				int genstart = (page-1)*limit;
				
				request.setAttribute("genstart", genstart);
				request.setAttribute("gennowPage", page);
				request.setAttribute("genstartPage", genstartPage);
				request.setAttribute("genendPage", genendPage);
				request.setAttribute("gentotalPage", gentotalPage);
				
				daoList = dao.userList2(genstart, limit);
				request.setAttribute("user", daoList);
			}
		}


	}

}
