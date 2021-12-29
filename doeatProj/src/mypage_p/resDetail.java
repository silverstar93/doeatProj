package mypage_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.RestaurantDTO;
import db_p.UserDAO;
import db_p.UserDTO;
import interface_p.Executor;

public class resDetail implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		UserDTO dto = new UserDAO().handle(Integer.parseInt(request.getParameter("memNo")));

		RestaurantDTO ves = new RestaurantDTO();

		ves.setMemNo(dto.getMemNo());

		String[] inf = dto.getFavMenu().split("/");

		ves.setRno(Integer.parseInt(inf[0]));
		ves.setRname(inf[1]);
		ves.setRowner(inf[2]);
		ves.setRaddr(inf[3]);
		ves.setRtel(inf[4]);

		String[] dtl = dto.getMyLocal().split("/");

		ves.setRprice1(dtl[0].split(",")[0]);
		ves.setRprice2(dtl[0].split(",")[1]);
		ves.setRmenu1(dtl[1].split(",")[0]);
		ves.setRmenu2(dtl[1].split(",")[1]);
		ves.setRhour(dtl[2]);
		ves.setRmain1(dtl[3].split(",")[0]);
		ves.setRmain2(dtl[3].split(",")[1]);
		ves.setRmain3(dtl[3].split(",")[2]);

		request.setAttribute("waithandle", ves);

		String addr = inf[3].substring(0, 4);
		if (addr.equals("경상남도")) {
			addr = "경남";
		} else if (addr.equals("경상북도")) {
			addr = "경북";
		} else if (addr.equals("전라남도")) {
			addr = "전남";
		} else if (addr.equals("전라북도")) {
			addr = "전북";
		} else if (addr.equals("충청북도")) {
			addr = "충북";
		} else if (addr.equals("충청남도")) {
			addr = "충남";
		} else {
			addr = addr.substring(0, 2);
		}
		
		String foto = null;
		if(dto.getBookmark()!=null) {
			foto = dto.getBookmark();
		}
		
		String foto2 = null;
		if(dto.getOffpic()!=null) {
			foto2 = dto.getOffpic();
		}
		
		request.setAttribute("bigaddr", addr);
		request.setAttribute("pic", foto);
		request.setAttribute("pic2", foto2);
		
	}

}
