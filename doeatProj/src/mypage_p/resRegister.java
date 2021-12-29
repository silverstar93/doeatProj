package mypage_p;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import db_p.ResDetailDAO;
import db_p.ResDetailDTO;
import db_p.ResInfDAO;
import db_p.ResInfDTO;
import db_p.UserDAO;
import filepath_p.AllPaths;
import filepath_p.RegexChk;
import interface_p.Executor;

public class resRegister implements Executor {

	RegexChk regex;
	AllPaths all = new AllPaths();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		try {

			request.setCharacterEncoding("utf-8");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String rgx = "입력값을 확인해주세요";
		String goUrl = "resReg";		
		
		
		
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


				
			try {
				
				// 전화번호, 주소 입력값 정리
				
				String [] tel = mq.getParameterValues("rtel");
				String rtel = tel[0]+"-"+tel[1]+"-"+tel[2];
						
				String [] addr = mq.getParameterValues("raddr");
				String raddr = "";
				
				for(int i = 1; i<addr.length; i++) {
					raddr += addr[i];
				}
				System.out.println(raddr);

				// DB로 보낼 준비
				
				ResInfDTO dto = new ResInfDTO();
				
				dto.setRno(Integer.parseInt(mq.getParameter("rno")));
				dto.setRname(mq.getParameter("rname"));
				dto.setRaddr(raddr);
				dto.setRtel(rtel);
				dto.setMemNo(0);

//				mq.setAttribute("inf", dto);
				
				ResDetailDTO dtl = new ResDetailDTO();

				dtl.setRno(Integer.parseInt(mq.getParameter("rno")));

				dtl.setRprice1(mq.getParameter("rprice1"));
				dtl.setRprice2(mq.getParameter("rprice2"));
				
				dtl.setRloc1(mq.getParameter("rloc1"));
				dtl.setRloc2(mq.getParameter("rloc2"));

				dtl.setRmenu1(mq.getParameter("rmenu1"));
				dtl.setRmenu2(mq.getParameter("rmenu2"));

				dtl.setRmain1(mq.getParameter("rmain1"));
				dtl.setRmain2(mq.getParameter("rmain2"));
				dtl.setRmain3(mq.getParameter("rmain3"));

				dtl.setRhour(mq.getParameter("rhour1") + "-" + mq.getParameter("rhour2"));
				
				
				dtl.setPic(mq.getFilesystemName("pic"));
				//mq.setAttribute("detail", dtl);
				
				// 정규식 체크
				
				regex = new RegexChk();
				
				if(!regex.strchk(mq.getParameter("rname"), mq.getParameter("rowner"), mq.getParameter("rloc2"),
						mq.getParameter("rmenu2"), mq.getParameter("rmain1"), mq.getParameter("rmain2"),
						mq.getParameter("rmain3"))) {					
					
					rgx = "상호명,사업자,장소(#소분류),메뉴(#소분류),주요메뉴".split(",")[RegexChk.sno]+"의 입력값을 다시 확인해주세요";
					
					System.out.println("문자 유효성 순서체크"+RegexChk.sno);

					
				}else if(!regex.numchk(mq.getParameter("rno"), mq.getParameter("rprice1"), mq.getParameter("rprice2"),
						tel[0], tel[1], tel[2])) {
					
					rgx = "사업자 등록번호,가격정보,전화번호".split(",")[RegexChk.nno]+"의 입력값을 다시 확인해주세요";
					
					System.out.println("숫자 유효성 순서체크"+RegexChk.nno);
					
				}else {
					
					new ResInfDAO().insert(dto, mq.getParameter("rowner"));
					int rid = new ResDetailDAO().insert(dtl);			
					rgx = "식당등록이 완료되었습니다";
					goUrl = "../restaurant/ResDetail?rid="+rid;

				}
				
				}catch(NullPointerException e){
					e.printStackTrace();
					rgx = "모든 값을 입력해야합니다";
				}

		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
			
		request.setAttribute("msg", rgx);
		request.setAttribute("goUrl", goUrl);
		request.setAttribute("mainUrl", "mypage/alert.jsp");		

	}

}
