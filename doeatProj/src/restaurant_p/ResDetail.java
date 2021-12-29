package restaurant_p;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.ResDetailDAO;
import db_p.ResDetailDTO;
import db_p.ResInfDAO;
import db_p.ResInfDTO;
import db_p.RestaurantDTO;
import db_p.ReviewDAO;
import db_p.ReviewDTO;
import db_p.UserDAO;
import db_p.UserDTO;
import interface_p.Executor;

public class ResDetail implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		int basic = 0;
		String cckk ="";

		if(request.getParameter("rid")!=null) {			
			basic = Integer.parseInt(request.getParameter("rid"));	
			System.out.println("basic"+ basic);
			cckk = request.getParameter("rid");
		}
		RestaurantDTO dto = new RestaurantDTO();		
		dto.setRid(basic);
		 
		request.setAttribute("detail", new ResDetailDAO().DetailAll(dto));
		System.out.println("서비스 디비 다녀왔슴다");
		
		// 페이지 계산
		
		int page = 1;
		int limit = 7;
		int pageLimit = 4;
		
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int total = new ReviewDAO().total(basic);
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
		
		/// 리뷰불러오기
		ReviewDTO rto = new ReviewDTO();
		rto.setRid(Integer.parseInt(request.getParameter("rid")));

		request.setAttribute("review", new ReviewDAO().list(rto, 0, start, limit));
		
		
		
		/// 북마크 체크
		String mk = "new";
		UserDTO uto = (UserDTO)request.getSession().getAttribute("mm");

		try {
		
		if(uto.getMemNo()==null || uto.getMemNo()==1) {
			mk = "new";
		}else if(uto.getMemNo()!=null) {
	
			if(new UserDAO().markedChk(uto.getMemNo())==null) { // || uto.getBookmark()==null) {
				
			}else {
				for (String mm : new UserDAO().markedChk(uto.getMemNo())) {
					System.out.println(mm);
					if(mm.equals(cckk)) {
						mk = "old";
					}
				}
			}
		}
		}catch(NullPointerException e) {
			System.out.println("그냥 넘어가주라");
		}
		
		request.setAttribute("mk", mk);
			
	}
}