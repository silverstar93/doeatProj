package login_p;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import db_p.UserDAO;
import db_p.UserDTO;
import filepath_p.AllPaths;
import interface_p.Executor;

public class joinReg implements Executor {

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

		UserDTO dto = new UserDTO();

		String path = request.getRealPath("/img");

		path = all.srchpath;

		try {
			MultipartRequest mq = new MultipartRequest(request, path, 10 * 1024 * 1024, "utf-8",
					new DefaultFileRenamePolicy());

			dto.setId(mq.getParameter("id"));
			dto.setPw(mq.getParameter("pw"));
			dto.setName(mq.getParameter("name"));
			String dd = mq.getParameter("bdate");
			String mm = mq.getParameter("bmonth");
			if (dd.length() < 2) {
				dd = "0" + dd;
			}
			if (mm.length() < 2) {
				mm = "0" + mm;
			}
			dto.setBirth(mq.getParameter("byear") + "-" + mm + "-" + dd);
			dto.setGen(mq.getParameter("gen"));
			dto.setMail(mq.getParameter("mail1") + "@" + mq.getParameter("mail2"));
			dto.setPhone(mq.getParameter("tel1") + "-" + mq.getParameter("tel2") + "-" + mq.getParameter("tel3"));
			dto.setQuestion(mq.getParameter("question"));
			dto.setAnswer(mq.getParameter("answer"));

			int cc = Integer.parseInt(mq.getParameter("cate"));
			if (cc == 1) {
				dto.setCate(1);
				dto.setGrade(1);
				dto.setFavMenu(mq.getParameter("favMenu"));
				dto.setMyLocal(mq.getParameter("myLocal"));

				new UserDAO().insert(dto);

			} else if (cc == 2) {

				dto.setCate(2);
				dto.setGrade(9);
				dto.setFavMenu(mq.getParameter("rno") + "/" + mq.getParameter("rname") + "/" + mq.getParameter("rowner")
						+ "/" + mq.getParameter("raddr1") + " " + mq.getParameter("raddr2") + "/"
						+ mq.getParameter("rtel1") + "-" + mq.getParameter("rtel2") + "-" + mq.getParameter("rtel3"));
				dto.setMyLocal(mq.getParameter("rprice1") + "," + mq.getParameter("rprice2") + "/"
						+ mq.getParameter("rmenu1") + "," + mq.getParameter("rmenu2") + "/" + mq.getParameter("rhour1")
						+ "-" + mq.getParameter("rhour2") + "/" + mq.getParameter("rmain1") + ","
						+ mq.getParameter("rmain2") + "," + mq.getParameter("rmain3"));

				if (mq.getFilesystemName("img") != null) {

					dto.setBookmark(mq.getFilesystemName("img"));
				} else {
					dto.setBookmark("NA");
				}

				if (mq.getFilesystemName("offpic") != null) {
					dto.setOffpic(mq.getFilesystemName("offpic"));
				} else {
					dto.setOffpic("NA");
				}

				new UserDAO().insert(dto);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("msg", "가입이 완료되었습니다.");
		request.setAttribute("goUrl", "index");
		request.setAttribute("mainUrl", "mypage/alert.jsp");

	}

}
