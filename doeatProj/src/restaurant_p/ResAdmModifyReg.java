package restaurant_p;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.ResDetailDAO;
import db_p.ResDetailDTO;
import db_p.ResInfDAO;
import db_p.RestaurantDTO;
import filepath_p.RegexChk;
import interface_p.Executor;

public class ResAdmModifyReg implements Executor {

	RegexChk regex;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		try {
			request.setCharacterEncoding("utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int page = 1;
		
		if(request.getParameter("nowPage")!=null) {
			page = Integer.parseInt(request.getParameter("nowPage"));
		}
		
		RestaurantDTO dto = new RestaurantDTO();
		
		dto.setMemNo(Integer.parseInt(request.getParameter("memNo")));
		dto.setRno(Integer.parseInt(request.getParameter("rno")));
		dto.setRname(request.getParameter("rname"));
		dto.setRowner(request.getParameter("rowner"));
		dto.setRaddr(request.getParameter("raddr"));
		dto.setRtel(request.getParameter("rtel"));
		
		
		dto.setRid(Integer.parseInt(request.getParameter("rid")));
		dto.setRloc1(request.getParameter("rloc1"));
		dto.setRloc2(request.getParameter("rloc2"));
		dto.setRmenu1(request.getParameter("rmenu1"));
		dto.setRmenu2(request.getParameter("rmenu2"));
		dto.setRhour(request.getParameter("rhour"));
		dto.setRprice1(request.getParameter("rprice1"));
		dto.setRprice2(request.getParameter("rprice2"));
		dto.setRmain1(request.getParameter("rmain1"));
		dto.setRmain2(request.getParameter("rmain2"));
		dto.setRmain3(request.getParameter("rmain3"));
		
		String rgx = "입력값을 확인해주세요";
		regex = new RegexChk();
		
		if(!regex.strchk(dto.getRname(), dto.getRowner(), "한글이야",
				request.getParameter("rmenu2"), request.getParameter("rmain1"), request.getParameter("rmain2"),
				request.getParameter("rmain3"))) {					
			
			
			
			rgx = "상호명,사업자,장소(#소분류),메뉴(#소분류),주요메뉴".split(",")[RegexChk.sno]+"의 입력값을 다시 확인해주세요";
			
			System.out.println("문자 유효성 순서체크"+RegexChk.sno);

			
		}else if(!regex.numchk(request.getParameter("rno"), request.getParameter("rprice1"), request.getParameter("rprice2"),
				request.getParameter("rtel").split("-")[0], 
				request.getParameter("rtel").split("-")[1], 
				request.getParameter("rtel").split("-")[2])) {
			
			rgx = "사업자 등록번호,가격정보,전화번호".split(",")[RegexChk.nno]+"의 입력값을 다시 확인해주세요";
			
			System.out.println("숫자 유효성 순서체크"+RegexChk.nno);
			
		}else {
			
			new ResDetailDAO().modifyAll(dto);
			rgx = "수정이 완료되었습니다";

		}		
				
		
		
		request.setAttribute("msg", rgx);
		request.setAttribute("goUrl", "ResDetail?rid="+dto.getRid()+"&page="+page);
		request.setAttribute("mainUrl", "alert.jsp");
		
	}

}
