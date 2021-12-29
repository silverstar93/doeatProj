package restaurant_p;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import db_p.ReviewDAO;
import db_p.ReviewDTO;
import filepath_p.AllPaths;
import interface_p.Executor;

public class ReviewReg implements Executor {

	AllPaths pp = new AllPaths();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		try {
			
			request.setCharacterEncoding("utf-8");
			
			String path = request.getRealPath("/rvpic");
			path = pp.filepath;
			
			MultipartRequest mr = new MultipartRequest(
					
					request,
					path,
					10*1024*1024,
					"utf-8",
					new DefaultFileRenamePolicy()
					
					);
			
			String content ="NA";
			
			if(mr.getFilesystemName("pic")!=null)
				content = mr.getFilesystemName("pic");
			
			
			ReviewDTO dto = new ReviewDTO();
			dto.setRid(Integer.parseInt(mr.getParameter("rid")));
			dto.setMemNo(Integer.parseInt(mr.getParameter("memNo")));
			dto.setTsrate(Integer.parseInt(mr.getParameter("tsrate")));
			dto.setMdrate(Integer.parseInt(mr.getParameter("mdrate")));
			dto.setSvrate(Integer.parseInt(mr.getParameter("svrate")));
			
			dto.setContext(mr.getParameter("context"));
			dto.setPic(content);
			
			new ReviewDAO().insert(dto);
			
			
			request.setAttribute("msg", "리뷰가 업데이트되었습니다");
			request.setAttribute("goUrl", "ResDetail?rid="+dto.getRid());
			request.setAttribute("mainUrl", "alert.jsp");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
