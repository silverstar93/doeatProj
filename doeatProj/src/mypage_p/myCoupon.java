package mypage_p;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.DealDAO;
import db_p.DealDTO;
import db_p.PayCoupDAO;
import db_p.PayCoupDTO;
import db_p.ReviewDAO;
import db_p.UserDTO;
import interface_p.Executor;

public class myCoupon implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		PayCoupDAO pao = new PayCoupDAO();
		int mem = ((UserDTO) request.getSession().getAttribute("mm")).getMemNo();

		// 나의 잇딜 페이지
		
		int page = 1;
		int limit = 7;
		int pageLimit = 4;
		
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int total = new PayCoupDAO().memtotal(mem);
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
		
		ArrayList<PayCoupDTO> pp = pao.myeatdeal(mem, start, limit);

		// 환불 페이지
		
		int rfpage = 1;
		int rflimit = 7;
		int rfpageLimit = 4;
		
		if(request.getParameter("rfpage")!=null) {
			rfpage = Integer.parseInt(request.getParameter("rfpage"));
		}
		
		int rftotal = new PayCoupDAO().rftotal(mem);
		int rftotalPage = rftotal/rflimit;
				
				if(rftotal%rflimit>0)
					rftotalPage++;
				
				int rfstartPage = (rfpage-1)/rfpageLimit*rfpageLimit+1;
				
		int rfendPage = rfstartPage+rfpageLimit-1;
			
		if(rfendPage>rftotalPage)
			rfendPage = rftotalPage;
		
		int rfstart = (rfpage-1)*rflimit;
		
		// 환불값 세팅
		
		request.setAttribute("rfstart", rfstart);
		request.setAttribute("rfnowPage", rfpage);
		request.setAttribute("rfstartPage", rfstartPage);
		request.setAttribute("rfendPage", rfendPage);
		request.setAttribute("rftotalPage", rftotalPage);
		
		ArrayList<PayCoupDTO> ref = new PayCoupDAO().myrefund(mem, rfstart, rflimit);
		
		/////
		
		ArrayList<DealDTO> dd = new DealDAO().plan(mem);

		System.out.println(dd.toString());

		if (dd != null) {
			request.setAttribute("mycoupon", pp);
			request.setAttribute("ref", ref);
			request.setAttribute("mmm", dd);
		}

	}

}
