package restaurant_p;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.ReviewDAO;
import db_p.ReviewDTO;
import filepath_p.AllPaths;
import interface_p.Executor;

public class ReviewDelete implements Executor{

	AllPaths pp = new AllPaths();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	
		String path = request.getRealPath("/pic");
		path = pp.filepath;
		
		ReviewDTO dto = new ReviewDTO();
		
		dto.setRid(Integer.parseInt(request.getParameter("rid")));
		dto.setMemNo(Integer.parseInt(request.getParameter("memNo")));
		dto.setcno(Integer.parseInt(request.getParameter("cno")));
		
		ReviewDAO dao = new ReviewDAO();
		int i = dao.delete(dto);
		
		String msg = "오류가 발생하여 리뷰를 삭제할 수 없습니다";
		
		if(i>0)
			msg = "작성하신 리뷰가 삭제되었습니다";
		
		if((!request.getParameter("pic").equals("NA")) && i>0) {
			File ff = new File(path+"/"+request.getParameter("pic"));
			ff.delete();
		};		
		
		request.setAttribute("msg", msg);
		request.setAttribute("goUrl", "ResDetail?rid="+request.getParameter("rid"));
		request.setAttribute("mainUrl", "alert.jsp");
		System.out.println(msg);
	}
	
}
