package mypage_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.ResDetailDAO;
import db_p.ReviewDAO;
import db_p.ReviewDTO;
import db_p.UserDTO;
import interface_p.Executor;

public class myReview implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		ReviewDTO dto = new ReviewDTO();
		dto.setMemNo(((UserDTO) request.getSession().getAttribute("mm")).getMemNo());
		
		int page = 1;
		int limit = 10;
		int pageLimit = 4;
		
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int total = new ReviewDAO().idvtotal(dto.getMemNo());
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
		
		request.setAttribute("myreview", new ReviewDAO().list(dto, 1, start, limit));

	}

}
