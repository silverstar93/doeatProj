package mypage_p;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.CoReviewDAO;
import db_p.CoReviewDTO;
import db_p.ReviewDAO;
import db_p.ReviewDTO;
import filepath_p.AllPaths;
import interface_p.Executor;

public class reviewReport implements Executor {

	AllPaths pp = new AllPaths();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String path = request.getRealPath("/pic");
		path = pp.filepath;

		int restaurant = Integer.parseInt(request.getParameter("rid"));
		int member = Integer.parseInt(request.getParameter("memNo"));
		int complain = Integer.parseInt(request.getParameter("conum"));
		int no = Integer.parseInt(request.getParameter("cno"));

		CoReviewDAO crdao = new CoReviewDAO();
		CoReviewDTO crto = new CoReviewDTO();

		crto.setConum(complain);
		
		int nowPage = 1;
		
		if(request.getParameter("nowPage")!=null) {
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		}

		if (request.getParameter("result").equals("reject")) {

			crto.setRid(restaurant);
			crto.setMemNo(member);

			crdao.reject(crto);

		} else if (request.getParameter("result").equals("delete")) {

			ReviewDTO rto = new ReviewDTO();

			crdao.accept(crto);

			rto.setcno(no);
			rto.setRid(restaurant);
			rto.setMemNo(member);

			new ReviewDAO().delete(rto);
			System.out.println("들어옴?");
			if ((!request.getParameter("pic").equals("NA"))) {

				try {

					File ff = new File(path + "/" + request.getParameter("pic"));
					ff.delete();

				} catch (Exception e) {
					System.out.println("파일이없으면여기로들어오겠지?");
				}
			}
			;
		}

		request.setAttribute("msg", "처리가 완료되었습니다");
		request.setAttribute("goUrl", "review?page="+nowPage);
		request.setAttribute("mainUrl", "mypage/alert.jsp");

	}
}
