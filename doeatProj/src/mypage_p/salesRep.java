package mypage_p;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.PayCoupDAO;
import db_p.ReviewDAO;
import db_p.UserDTO;
import interface_p.Executor;

public class salesRep implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int page = 1;
		int limit = 10;
		int pageLimit = 4;
		
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int total = new PayCoupDAO().total(((UserDTO) request.getSession().getAttribute("mm")).getMemNo());
		int totalPage = total/limit;
				
				if(total%limit>0)
					totalPage++;
				
				int startPage = (page-1)/pageLimit*pageLimit+1;
				
		int endPage = startPage+pageLimit-1;
			
		if(endPage>totalPage)
			endPage = totalPage;
		
		int start = (page-1)*limit;
		


		if (request.getParameter("cnum") != null) {
			request.setAttribute("sales", new PayCoupDAO().schCnum(request.getParameter("cnum")));
		} else {
			request.setAttribute("start", start);
			request.setAttribute("nowPage", page);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("sales",
					new PayCoupDAO().salesList(((UserDTO) request.getSession().getAttribute("mm")).getMemNo()
							, start, limit));
		}

	}

}
