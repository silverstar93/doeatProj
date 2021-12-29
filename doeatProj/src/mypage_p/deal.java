package mypage_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.DealDAO;
import db_p.ReviewDAO;
import interface_p.Executor;

public class deal implements Executor {

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
				
				int total = new DealDAO().total(0);
				int totalPage = total/limit;
						
						if(total%limit>0)
							totalPage++;
						
						int startPage = (page-1)/pageLimit*pageLimit+1;
						
				int endPage = startPage+pageLimit-1;
					
				if(endPage>totalPage)
					endPage = totalPage;
				// 계산한 endPage가 totalPage보다 크면 totalPage와 같다
				
				int start = (page-1)*limit;
				
				request.setAttribute("start", start);
				request.setAttribute("nowPage", page);
				request.setAttribute("startPage", startPage);
				request.setAttribute("endPage", endPage);
				request.setAttribute("totalPage", totalPage);
		
		request.setAttribute("chk", new DealDAO().list(start, limit));
	}

}
