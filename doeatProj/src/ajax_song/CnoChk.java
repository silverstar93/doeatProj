package ajax_song;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.CoReviewDAO;
import db_p.ResInfDAO;
import interface_p.Executor;

public class CnoChk implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		String chk = request.getParameter("cno");
		String [] cnoChk = new CoReviewDAO().cnoChk().split(",");
		Boolean gochk = true;
		
		for (String sss : cnoChk) {
			System.out.println(sss+","+chk);
			if(sss==chk || sss.equals(chk)) {
				gochk = false;
				System.out.println(sss+","+chk);
			}
		}
		
		request.setAttribute("cnos", gochk);
		
	}

}
