package mypage_p;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.DealDAO;
import db_p.PayCoupDAO;
import db_p.ResDetailDAO;
import db_p.ResInfDAO;
import db_p.UserDAO;
import db_p.UserDTO;
import interface_p.Executor;

public class deleteReg implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		UserDTO dto = new UserDTO();

		dto.setPw(request.getParameter("pw"));

		UserDTO cc = ((UserDTO) request.getSession().getAttribute("mm"));

		if (cc.getCate() == 0) {
			dto.setMemNo(Integer.parseInt(request.getParameter("memNo")));
			request.setAttribute("goUrl", "user");
			
			UserDTO dd = new UserDAO().detail(dto);
			
			if(dd.getCate()==1) {
				if (new PayCoupDAO().deleteReq(dd.getMemNo())) {
					new UserDAO().delete(dd);
				} else {
					request.setAttribute("msg", "사용하지 않은 이용권이 있습니다. 환불 후에 탈퇴 시키겠습니까?");
					request.setAttribute("mainUrl", "mypage/confirm.jsp?mem="+dd.getMemNo());
					return;

				}
			} else if(dd.getCate()==2) {
				
				if(request.getParameter("dChk").equals("진행 중인 잇딜이 없습니다")&&request.getParameter("cChk").equals("사용 기간이 있는 이용권이 없습니다")) {
					new ResInfDAO().deleteMem(dd.getMemNo());
					new UserDAO().delete(dd);
					request.setAttribute("goUrl", "partner");
				} else {
					request.setAttribute("msg", "진행 중인 잇딜 또는 사용기간이 있는 이용권이 있습니다. 잇딜 삭제 /이용권 환불 후에 탈퇴 시키겠습니까?");
					request.setAttribute("mainUrl", "mypage/confirm.jsp?mem="+dd.getMemNo());
					return;
				}
				
				
			}
		

		} else {
			dto.setMemNo(cc.getMemNo());

			if (!new UserDAO().chk(dto)) {
				request.setAttribute("msg", "비밀번호를 다시 확인해주세요.");
				request.setAttribute("goUrl", "index");
				request.setAttribute("mainUrl", "mypage/alert.jsp");
				return;
			}

			if (cc.getCate() == 1) {
				request.setAttribute("goUrl", "../eatlist/Home");
				if (new PayCoupDAO().deleteReq(cc.getMemNo())) {
					request.getSession().invalidate();
					new UserDAO().delete(dto);
				} else {
					request.setAttribute("msg", "사용하지 않은 이용권이 있습니다.");
					request.setAttribute("goUrl", "index");
					request.setAttribute("mainUrl", "mypage/alert.jsp");
					return;

				}

			} else if (cc.getCate() == 2) {
				dto.setMemNo(cc.getMemNo());
				new UserDAO().deleteReq(dto);
				int rid = new ResDetailDAO().identify(cc.getMemNo());
				new DealDAO().freeze(rid);
				request.setAttribute("msg", "탈퇴 신청이 완료되었습니다.");
				request.setAttribute("goUrl", "index");
				request.setAttribute("mainUrl", "mypage/alert.jsp");
				return;
			}

		}

		request.setAttribute("mainUrl", "mypage/gogo.jsp");

	}

}
