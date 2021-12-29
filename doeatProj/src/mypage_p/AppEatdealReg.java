package mypage_p;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import db_p.DealDAO;
import db_p.DealDTO;
import filepath_p.AllPaths;
import interface_p.Executor;

public class AppEatdealReg implements Executor {

	AllPaths all = new AllPaths();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String path = request.getRealPath("/image");
		path = all.dealpath;

		try {
			MultipartRequest mm = new MultipartRequest(request, path, 10 * 1024 * 1024, "utf-8",
					new DefaultFileRenamePolicy());

			SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd");
			
			Date cs = null;
			Date nn = null;
			String cday =mm.getParameter("cyear")+"-"+mm.getParameter("cmonth")+"-"+mm.getParameter("cday");
			String pday =mm.getParameter("pyear")+"-"+mm.getParameter("pmonth")+"-"+mm.getParameter("pday");
			System.out.println(cday+"Ddd"+pday);
			
			try {
				cs = ss.parse(cday);
				nn = ss.parse(pday);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			cs.setMonth(cs.getMonth() + 3);
		
			nn.setDate(nn.getDay() + 21);
			System.out.println(ss.format(cs)+"달달");
			int org = Integer.parseInt(mm.getParameter("orgprice"));
			int dc = Integer.parseInt(mm.getParameter("cdiscount"));
		  
			DealDTO dto = new DealDTO();
			dto.setCmenu(mm.getParameter("cmenu"));
			dto.setOrgprice(org);
			dto.setCdiscount(dc);
			dto.setCvolume(Integer.parseInt(mm.getParameter("cvolume")));
			dto.setCstartStr(cday);
			dto.setCendStr(ss.format(cs));
			dto.setPubstartStr(pday);
			dto.setPubendStr(ss.format(nn));
			dto.setRimg(mm.getFilesystemName("rimg"));
			dto.setContent(mm.getParameter("content"));
			dto.setCprice((org * (100 - dc)) / 100);

			new DealDAO().write(dto, Integer.parseInt(mm.getParameter("memNo")));
			System.out.println(Integer.parseInt(mm.getParameter("memNo"))+"ㅔㅁㅁ버 넘버");
			// request.setAttribute("list", new DealDAO().codelist());
			request.setAttribute("msg", "작성완료");
			request.setAttribute("goUrl", "eatDealList");// 마이페이지 생기면 마이페이지로 넘기기
			request.setAttribute("mainUrl", "mypage/alert.jsp");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
