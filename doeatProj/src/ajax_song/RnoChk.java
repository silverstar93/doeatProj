package ajax_song;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.ResInfDAO;
import interface_p.Executor;

public class RnoChk implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		String chk = request.getParameter("rno");
		String [] rnoChk = new ResInfDAO().rnoChk().split(",");
		Boolean gochk = true;
		
		for (String sss : rnoChk) {
			
			if(sss==chk || sss.equals(chk)) {
				gochk = false;
				System.out.println(sss+","+chk);
			}
		}
		
		request.setAttribute("rnos", gochk);
		
	}

}
