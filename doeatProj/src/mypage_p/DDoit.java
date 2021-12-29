package mypage_p;

import java.util.Date;

import db_p.UserDAO;
import db_p.UserDTO;

public class DDoit {

	Date today = new Date();

	public void areU() {

		new UserDAO().audd();

		for (UserDTO dto : new UserDAO().userList(0, 0)) {

			if (dto.getCate() == 1 &&  today.getDate()==1) {
				if ((int) (dto.getGg() / 100) > 0) {
					System.out.println("드르가?");
					dto.setGrade(2);
					new UserDAO().point((int) (dto.getGg() / 100), dto);
				} else {

					dto.setGrade(1);
					new UserDAO().downG(dto);
				}
				;
			}
			;

		}

	}

}
