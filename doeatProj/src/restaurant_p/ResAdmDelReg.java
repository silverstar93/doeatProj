package restaurant_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.DealDAO;
import db_p.ResInfDAO;
import db_p.ResInfDTO;
import interface_p.Executor;

public class ResAdmDelReg implements Executor{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
		int page = 1;
		
		if(request.getParameter("nowPage")!=null || (!request.getParameter("nowPage").equals(""))) {
			page = Integer.parseInt(request.getParameter("nowPage"));
		}else if(request.getParameter("page")!=null || (!request.getParameter("page").equals(""))) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
			int res = Integer.parseInt(request.getParameter("rid"));
			String msg = "식당 삭제가 완료되었습니다";
			String go = "../mypage/resMng?page="+page;
			String mainUrl = "alert.jsp";
			
			DealDAO dd = new DealDAO();					
			
			if(!dd.chkDeal(res).equals("진행 중인 잇딜이 없습니다")) {
				
				msg = dd.chkDeal(res); //+"\n 식당 삭제가 불가능합니다";
				go = "ResDetail?rid="+res+"&nowPage="+page;
				mainUrl = "alert.jsp";
				
			}else if(!dd.chkCoup(res).equals("사용 기간이 있는 이용권이 없습니다")) {
				
				msg = dd.chkCoup(res); //+"\n 식당 삭제가 불가능합니다";
				go = "ResDetail?rid="+res+"&nowPage="+page;
				mainUrl = "alert.jsp";
				
			}else {
				
				ResInfDTO dto = new ResInfDTO();
				
				dto.setMemNo(Integer.parseInt(request.getParameter("memNo")));
				dto.setRno(Integer.parseInt(request.getParameter("rno")));			
						
				new ResInfDAO().delete(dto, res);
				
			}
			System.out.println(msg+go);
			
			request.setAttribute("msg", msg);
			request.setAttribute("goUrl", go);
			request.setAttribute("mainUrl", mainUrl);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
			
	}	

}
