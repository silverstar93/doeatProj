package mypage_p;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.PayCalDTO;
import db_p.PaymentDAO;
import db_p.ReviewDAO;
import db_p.ReviewDTO;
import db_p.UserDTO;
import interface_p.Executor;

public class MyDealCal implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		UserDTO dto = (UserDTO) request.getSession().getAttribute("mm");


		// 페이지 계산
		
		int page = 1;
		int limit = 10;
		int pageLimit = 4;
		
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int total = new ReviewDAO().total(dto.getMemNo());
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

		ArrayList<PayCalDTO> mdc = new PaymentDAO().cal(dto, start, limit);
		request.setAttribute("mdc", mdc);

	}

}
