package mypage_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.PayCoupDAO;
import db_p.ReviewDAO;
import interface_p.Executor;

public class coup implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		// 페이지 계산
		
				int page = 1;
				int limit = 7;
				int pageLimit = 4;
				
				if(request.getParameter("page")!=null) {
					page = Integer.parseInt(request.getParameter("page"));
				}
				
				int total = new PayCoupDAO().total(0);
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
		
		request.setAttribute("admcoup", new PayCoupDAO().admlist(start, limit));

	}

}
