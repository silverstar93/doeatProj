package mypage_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.ResDetailDAO;
import db_p.ResDetailDTO;
import db_p.ResInfDAO;
import db_p.ResInfDTO;
import db_p.UserDAO;
import filepath_p.RegexChk;
import interface_p.Executor;

public class resAccept implements Executor {

	RegexChk regex;		
		
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		try {

			request.setCharacterEncoding("utf-8");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		int nowPage = 1;
		
		if(request.getParameter("nowPage")!=null) {
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		}
		
		
		int mem = Integer.parseInt(request.getParameter("memNo"));
		int rst = Integer.parseInt(request.getParameter("rno"));

		ResInfDTO ves = new ResInfDTO();

		ves.setMemNo(mem);
		ves.setRno(rst);
		ves.setRname(request.getParameter("rname"));
		ves.setRowner(request.getParameter("rowner"));
		ves.setRaddr(request.getParameter("raddr"));
		ves.setRtel(request.getParameter("rtel"));

		ResDetailDTO sel = new ResDetailDTO();

		sel.setRno(rst);
		sel.setRloc1(request.getParameter("rloc1"));
		sel.setRloc2(request.getParameter("rloc2"));
		sel.setRprice1(request.getParameter("rprice1"));
		sel.setRprice2(request.getParameter("rprice2"));
		sel.setRmenu1(request.getParameter("rmenu1"));
		sel.setRmenu2(request.getParameter("rmenu2"));
		sel.setRhour(request.getParameter("rhour"));
		sel.setRmain1(request.getParameter("rmain1"));
		sel.setRmain2(request.getParameter("rmain2"));
		sel.setRmain3(request.getParameter("rmain3"));
		sel.setPic(request.getParameter("pic"));
		
		String rgx = "입력값을 확인해주세요";
		String goUrl = "resDetail?memNo="+request.getParameter("memNo")+
					"&nowPage="+nowPage;
		
		regex = new RegexChk();
		
		if (request.getParameter("result").equals("accept")) {
			
			if(!regex.strchk(ves.getRname(), ves.getRowner(), sel.getRloc2(),
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
				
				new ResInfDAO().insert(ves, request.getParameter("rowner"));
				int rid = new ResDetailDAO().insert(sel);			
				rgx = "처리가 완료되었습니다";
				goUrl = "../restaurant/ResDetail?rid="+rid+"&page="+nowPage;

			}		

		} else if (request.getParameter("result").equals("reject")) {

			new UserDAO().reqReject(mem);
			rgx = "처리가 완료되었습니다";
			goUrl = "resAcp?page="+nowPage;

		}

		request.setAttribute("msg", rgx);
		request.setAttribute("goUrl", goUrl);
		// 작성 후, 수락은 디테일로 빠지게, 거절은 목록으로가게 수정!
		request.setAttribute("mainUrl", "mypage/alert.jsp");

	}

}
