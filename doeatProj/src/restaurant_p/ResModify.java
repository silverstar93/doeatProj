package restaurant_p;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.ResDetailDAO;
import db_p.ResDetailDTO;
import db_p.RestaurantDTO;
import db_p.UserDAO;
import db_p.UserDTO;
import interface_p.Executor;

public class ResModify implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		RestaurantDTO dto = new RestaurantDTO();		
		dto.setRid(Integer.parseInt(request.getParameter("rid")));
		
		request.setAttribute("detail", new ResDetailDAO().DetailAll(dto));
		System.out.println("서비스 디비 다녀왔슴다");
				
		
	}

}