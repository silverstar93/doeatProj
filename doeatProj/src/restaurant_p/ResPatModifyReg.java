package restaurant_p;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import db_p.ResDetailDAO;
import db_p.ResDetailDTO;
import db_p.UserDAO;
import db_p.UserDTO;
import filepath_p.AllPaths;
import interface_p.Executor;

public class ResPatModifyReg implements Executor {

	AllPaths all = new AllPaths();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		
		UserDTO udto = (UserDTO)request.getSession().getAttribute("mm");
		System.out.println(all.srchpath+"\\"+udto.getBookmark());
		String file =all.srchpath+"\\"+ udto.getBookmark();
		File bb = new File(file);
		if(bb.exists()) {
			bb.delete();
			System.out.println("파일 삭제 완료");
		}
		
        
        
		try {
			request.setCharacterEncoding("utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ResDetailDTO dto = new ResDetailDTO();
		
		
		String path = request.getRealPath("/img");
		path = all.srchpath;
		

		try {
			MultipartRequest mq = new MultipartRequest(
					request,
					path, 
					10 * 1024 * 1024, 
					"utf-8",
					new DefaultFileRenamePolicy()
			);

			
		
			dto.setRid(Integer.parseInt(mq.getParameter("rid")));
			dto.setRmenu1(mq.getParameter("rmenu1"));
			dto.setRmenu2(mq.getParameter("rmenu2"));
			dto.setRhour(mq.getParameter("rhour1")+"-"+mq.getParameter("rhour2"));
			System.out.println(mq.getParameter("rhour1")+"-"+mq.getParameter("rhour2"));
			dto.setRprice1(mq.getParameter("rprice1"));
			dto.setRprice2(mq.getParameter("rprice2"));
			dto.setRmain1(mq.getParameter("rmain1"));
			dto.setRmain2(mq.getParameter("rmain2"));
			dto.setRmain3(mq.getParameter("rmain3"));
			
			udto.setBookmark(mq.getFilesystemName("img"));
			
			new UserDAO().modifyImg(udto);
			new ResDetailDAO().modify(dto);
		
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		
		request.setAttribute("msg", "수정이 완료되었습니다");
		request.setAttribute("goUrl", "ResDetail?rid="+dto.getRid());
		request.setAttribute("mainUrl", "alert.jsp");
		
	}

}
