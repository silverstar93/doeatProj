package eatlist_p;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.ClickDAO;
import db_p.ClickDTO;
import db_p.HashtagDAO;
import db_p.HashtagDTO;
import db_p.ResDetailDAO;
import db_p.ResDetailDTO;
import db_p.UserDTO;
import interface_p.Executor;

public class SearchReg implements Executor{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		int rid = Integer.parseInt(request.getParameter("rid"));
		
		if(request.getSession().getAttribute("mm")!=null) {
			
		UserDTO udto = (UserDTO)request.getSession().getAttribute("mm");
		if(udto.getMemNo()!=null) {
		System.out.println(udto.getMemNo());
		ClickDTO dto = new ClickDTO();
		dto.setMemNo(udto.getMemNo());
		dto.setRid(rid);
		new ClickDAO().insert(dto);
		}
		}
		
		
		System.out.println("검색 결과 저장");
		
		String gogo = "ResDetail?rid="+rid;
		request.setAttribute("gogo", gogo);
		request.setAttribute("mainUrl", "eatlist/tm.jsp");
}
		
}
