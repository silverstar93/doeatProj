package restaurant_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import db_p.CoReviewDAO;
import db_p.CoReviewDTO;
import db_p.ReviewDAO;
import db_p.ReviewDTO;
import interface_p.Executor;

public class RvReportReg implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		try {					
			
			request.setCharacterEncoding("utf-8");
			
			String content ="NS";
			
			if(request.getParameter("crsdt")!=null)
				content = request.getParameter("crsdt");
			
			CoReviewDTO cr = new CoReviewDTO();
			cr.setRid(Integer.parseInt(request.getParameter("rid")));
			cr.setCno(Integer.parseInt(request.getParameter("cno")));
			cr.setMemNo(Integer.parseInt(request.getParameter("memNo")));
			
			cr.setCrs(request.getParameter("crs"));
			cr.setCrsdt(content);
			
			new CoReviewDAO().insert(cr);
			
			
			request.setAttribute("msg", "신고가 접수되었습니다");
			request.setAttribute("goUrl", "ResDetail?rid="+cr.getRid());
			request.setAttribute("mainUrl", "alert.jsp");
	
		}catch(Exception e) {
			e.printStackTrace();
		}
}
}