package db_p;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import filepath_p.DBSet;

public class UserDAO {

	DBSet set = new DBSet();

//	String url="jdbc:mysql://192.168.43.153:3306/doeat_db";
//	String id="doeat";
//	String pw="123456";
	String sql;

	Connection con;
	PreparedStatement ptmt;
	ResultSet rs;

	public UserDAO() { // CRUD에서 항상 실행

		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(set.url, set.id, set.pw);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean chk(UserDTO dto) {
		boolean res = false;

		String pp = "";

		sql = "select pw from user where memNo = ?";

		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.memNo);
			rs = ptmt.executeQuery();
			rs.next();
			pp = rs.getString("pw");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close();
		}

		if (dto.getPw().equals(pp)) {
			res = true;
		}

		return res;

	}

	public ArrayList<UserDTO> list() {
		ArrayList<UserDTO> res = new ArrayList<UserDTO>();

		try {

			sql = "select * from user";

			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();

			while (rs.next()) {
				UserDTO dto = new UserDTO();
				dto.setCate(rs.getInt("cate"));
				dto.setGrade(rs.getInt("grade"));
				dto.setMemNo(rs.getInt("memNo"));
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setBirth(rs.getString("birth"));
				dto.setGen(rs.getString("gen"));
				dto.setPhone(rs.getString("phone"));
				dto.setMail(rs.getString("mail"));
				dto.setFavMenu(rs.getString("favMenu"));
				dto.setMyLocal(rs.getString("myLocal"));
				dto.setQuestion(rs.getString("question"));
				dto.setAnswer(rs.getString("answer"));
				dto.setBookmark(rs.getString("bookmark"));
				dto.setPoint(rs.getInt("point"));
				// dto.setGg(rs.getInt("gg"));
				res.add(dto);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close();
		}

		return res;
	}

	public ArrayList<UserDTO> userList(int start, int limit) {
		ArrayList<UserDTO> res = new ArrayList<UserDTO>();

		try {
			if (limit == 0) {

				sql = "select * from user where cate = 1";

				ptmt = con.prepareStatement(sql);

			} else {
				sql = "select * from user where cate = 1 limit ?, ?";

				ptmt = con.prepareStatement(sql);
				ptmt.setInt(1, start);
				ptmt.setInt(2, limit);
			}

			rs = ptmt.executeQuery();

			while (rs.next()) {
				UserDTO dto = new UserDTO();
				dto.setCate(rs.getInt("cate"));
				dto.setGrade(rs.getInt("grade"));
				dto.setMemNo(rs.getInt("memNo"));
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setBirth(rs.getString("birth"));
				dto.setGen(rs.getString("gen"));
				dto.setPhone(rs.getString("phone"));
				dto.setMail(rs.getString("mail"));
				dto.setFavMenu(rs.getString("favMenu"));
				dto.setMyLocal(rs.getString("myLocal"));
				dto.setQuestion(rs.getString("question"));
				dto.setAnswer(rs.getString("answer"));
				dto.setBookmark(rs.getString("bookmark"));
				dto.setPoint(rs.getInt("point"));
				dto.setGg(rs.getInt("gg"));

				res.add(dto);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close();
		}

		return res;
	}

	public ArrayList<UserDTO> userList2(int start, int limit) {
		ArrayList<UserDTO> res = new ArrayList<UserDTO>();

		try {

			sql = "select * from user where cate = 2 limit ?, ?";

			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, start);
			ptmt.setInt(2, limit);
			rs = ptmt.executeQuery();

			while (rs.next()) {
				UserDTO dto = new UserDTO();
				dto.setCate(rs.getInt("cate"));
				dto.setGrade(rs.getInt("grade"));
				dto.setMemNo(rs.getInt("memNo"));
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setBirth(rs.getString("birth"));
				dto.setGen(rs.getString("gen"));
				dto.setPhone(rs.getString("phone"));
				dto.setMail(rs.getString("mail"));
				dto.setFavMenu(rs.getString("favMenu"));
				dto.setMyLocal(rs.getString("myLocal"));
				dto.setQuestion(rs.getString("question"));
				dto.setAnswer(rs.getString("answer"));
				dto.setBookmark(rs.getString("bookmark"));
				dto.setPoint(rs.getInt("point"));
				dto.setGg(rs.getInt("gg"));

				res.add(dto);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close();
		}

		return res;
	}

	public UserDTO detail(UserDTO dto) {
		UserDTO res = null;

		sql = "select * from user where  memNo = ?";

		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.memNo);
			rs = ptmt.executeQuery();

			if (rs.next()) {
				res = new UserDTO();
				res.setCate(rs.getInt("cate"));
				res.setGrade(rs.getInt("grade"));
				res.setMemNo(rs.getInt("memNo"));
				res.setId(rs.getString("id"));
				res.setPw(rs.getString("pw"));
				res.setName(rs.getString("name"));
				res.setBirth(rs.getString("birth"));
				res.setGen(rs.getString("gen"));
				res.setPhone(rs.getString("phone"));
				res.setMail(rs.getString("mail"));
				res.setFavMenu(rs.getString("favMenu"));
				res.setMyLocal(rs.getString("myLocal"));
				res.setQuestion(rs.getString("question"));
				res.setAnswer(rs.getString("answer"));
				res.setBookmark(rs.getString("bookmark"));
				res.setPoint(rs.getInt("point"));
				res.setGg(rs.getInt("gg"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close();
		}

		return res;
	}

	public String idSearch(UserDTO dto) {
		String res = "";

		sql = "select id from user where name = ? and birth = ? and mail = ? ";

		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, dto.name);
			ptmt.setString(2, dto.birth);
			ptmt.setString(3, dto.mail);
			rs = ptmt.executeQuery();
			if (rs.next())
				res = rs.getString("id");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close();
		}

		return res;
	}

	public String pwSearch(UserDTO dto) {
		String res = "";

		sql = "select pw from user where name = ? and birth = ? and mail = ? and id = ? and question = ? and answer = ? ";

		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, dto.name);
			ptmt.setString(2, dto.birth);
			ptmt.setString(3, dto.mail);
			ptmt.setString(4, dto.id);
			ptmt.setString(5, dto.question);
			ptmt.setString(6, dto.answer);
			rs = ptmt.executeQuery();
			if (rs.next())
				res = rs.getString("pw");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close();
		}

		return res;
	}

	public ArrayList<UserDTO> schName(String name) {
		ArrayList<UserDTO> res = new ArrayList<UserDTO>();

		try {

			sql = "select * from user where name like ? and cate = 1";

			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, '%' + name + '%');
			rs = ptmt.executeQuery();

			while (rs.next()) {
				UserDTO dto = new UserDTO();
				dto.setCate(rs.getInt("cate"));
				dto.setGrade(rs.getInt("grade"));
				dto.setMemNo(rs.getInt("memNo"));
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setBirth(rs.getString("birth"));
				dto.setGen(rs.getString("gen"));
				dto.setPhone(rs.getString("phone"));
				dto.setMail(rs.getString("mail"));
				dto.setFavMenu(rs.getString("favMenu"));
				dto.setMyLocal(rs.getString("myLocal"));
				dto.setQuestion(rs.getString("question"));
				dto.setAnswer(rs.getString("answer"));
				dto.setBookmark(rs.getString("bookmark"));
				dto.setPoint(rs.getInt("point"));

				res.add(dto);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close();
		}

		return res;
	}

	public ArrayList<UserDTO> schName2(String name) {
		ArrayList<UserDTO> res = new ArrayList<UserDTO>();

		try {

			sql = "select * from user where name like ? and cate = 2";

			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, '%' + name + '%');
			rs = ptmt.executeQuery();

			while (rs.next()) {
				UserDTO dto = new UserDTO();
				dto.setCate(rs.getInt("cate"));
				dto.setGrade(rs.getInt("grade"));
				dto.setMemNo(rs.getInt("memNo"));
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setBirth(rs.getString("birth"));
				dto.setGen(rs.getString("gen"));
				dto.setPhone(rs.getString("phone"));
				dto.setMail(rs.getString("mail"));
				dto.setFavMenu(rs.getString("favMenu"));
				dto.setMyLocal(rs.getString("myLocal"));
				dto.setQuestion(rs.getString("question"));
				dto.setAnswer(rs.getString("answer"));
				dto.setBookmark(rs.getString("bookmark"));
				dto.setPoint(rs.getInt("point"));

				res.add(dto);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close();
		}

		return res;
	}

	public void insert(UserDTO dto) {

		try {

			sql = "insert into user "
					+ "(cate, grade, id, pw, name, birth, gen, phone, mail, favMenu, myLocal, question, answer, bookmark, point, offpic) values "
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,0,?)";

			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.getCate());
			ptmt.setInt(2, dto.getGrade());
			ptmt.setString(3, dto.getId());

			ptmt.setString(4, dto.getPw());
			ptmt.setString(5, dto.getName());
			ptmt.setString(6, dto.getBirth());
			ptmt.setString(7, dto.getGen());

			ptmt.setString(8, dto.getPhone());
			ptmt.setString(9, dto.getMail());
			ptmt.setString(10, dto.getFavMenu());
			ptmt.setString(11, dto.getMyLocal());
			ptmt.setString(12, dto.getQuestion());
			ptmt.setString(13, dto.getAnswer());
			ptmt.setString(14, dto.getBookmark());
			ptmt.setString(15, dto.getOffpic());
			ptmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close();
		}

	}

	public void modify(UserDTO dto) {

		sql = "update user set name = ?, birth = ?, gen = ?, phone = ?, "
				+ "favMenu = ?, myLocal = ?, question = ?, answer = ?, point = ? where memNo = ?";

		try {
			ptmt = con.prepareStatement(sql);

			ptmt.setString(1, dto.getName());

			ptmt.setString(2, dto.getBirth());
			ptmt.setString(3, dto.getGen());

			ptmt.setString(4, dto.getPhone());
			ptmt.setString(5, dto.getFavMenu());
			ptmt.setString(6, dto.getMyLocal());
			ptmt.setString(7, dto.getQuestion());
			ptmt.setString(8, dto.getAnswer());
			ptmt.setInt(9, dto.getPoint());
			ptmt.setInt(10, dto.getMemNo());

			ptmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close();
		}

	}

	public void userModify(UserDTO dto) {

		sql = "update user set phone = ?, favMenu = ?, myLocal = ?, question = ?, answer = ? where memNo = ?";

		try {
			ptmt = con.prepareStatement(sql);

			ptmt.setString(1, dto.getPhone());
			ptmt.setString(2, dto.getFavMenu());
			ptmt.setString(3, dto.getMyLocal());
			ptmt.setString(4, dto.getQuestion());
			ptmt.setString(5, dto.getAnswer());
			ptmt.setInt(6, dto.getMemNo());

			ptmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close();
		}

	}

	public void pwChange(UserDTO dto) {
		sql = "update user set pw = ? where memNo = ?";

		try {
			ptmt = con.prepareStatement(sql);

			ptmt.setString(1, dto.getPw());
			ptmt.setInt(2, dto.getMemNo());

			ptmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close();
		}

	}

	public void modifyImg(UserDTO dto) {

		sql = "update user set bookmark = ? where memNo = ?";

		try {
			ptmt = con.prepareStatement(sql);

			ptmt.setString(1, dto.getBookmark());
			ptmt.setInt(2, dto.getMemNo());
			System.out.println(sql + dto.getBookmark());
			ptmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close();
		}
	}

	public void delete(UserDTO dto) {

		try {

			sql = "delete from user where memNo = ? and pw = ? ";

			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.getMemNo());
			ptmt.setString(2, dto.getPw());

			ptmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close();
		}
	}
	
	public void delete2(int mem) {

		try {

			sql = "delete from user where memNo = ? ";

			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, mem);

			ptmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close();
		}
	}
	

	public ArrayList<UserDTO> waitList(int start, int limit) {
		ArrayList<UserDTO> res = new ArrayList<UserDTO>();

		try {

			sql = "select * from user where grade = 9 and cate = 2 " + "limit ?, ?";

			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, start);
			ptmt.setInt(2, limit);
			rs = ptmt.executeQuery();

			while (rs.next()) {
				UserDTO dto = new UserDTO();

				dto.setGrade(rs.getInt("grade"));
				dto.setMemNo(rs.getInt("memNo"));
				dto.setFavMenu(rs.getString("favMenu"));
				// dto.setMyLocal(rs.getString("myLocal"));
				System.out.println("DB" + rs.getString("favMenu"));
				res.add(dto);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close();
		}

		return res;
	}

	public UserDTO handle(int memno) {

		UserDTO res = null;

		sql = "select * from user where memNo = ?";

		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, memno);
			rs = ptmt.executeQuery();

			if (rs.next()) {

				res = new UserDTO();

				res.setMemNo(rs.getInt("memNo"));
				res.setFavMenu(rs.getString("favMenu"));
				res.setMyLocal(rs.getString("myLocal"));
				res.setBookmark(rs.getString("bookmark"));
				res.setOffpic(rs.getString("offpic"));

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close();
		}

		return res;
	}

	public void reqReject(int memNo) {

		try {

			sql = "update user set favMenu = null, " + "myLocal = null, cate = 1, grade = 1 where memNo = ?";

			System.out.println(sql);

			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, memNo);
			ptmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close();
		}
	}

	public void bookmark(String rid, int memNo) {

		int i = 0;
		
		try {

			String input = rid + ",";

			sql = "update user inner join " + "(SELECT memNo, IFNULL(bookmark, '') as pr "
					+ "FROM user where memNo = ?)plz " + "on user.memNo = plz.memNo set bookmark = concat(pr,?)";

			System.out.println(sql);

			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, memNo);
			ptmt.setString(2, input);
			i = ptmt.executeUpdate();
			
			if(i<0) {
				sql = "update user set bookmark = ? where memNo = ?";
				ptmt.setString(1, input);
				ptmt.setInt(2, memNo);
				ptmt.executeUpdate();
			}

			sql = "update resDetail set marked = marked+1 where rid= " + rid;

			ptmt = con.prepareStatement(sql);
			ptmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close();
		}
	}

	public void markout(String rid, int memNo) {

		try {

			String input = rid + ",";

			sql = "select bookmark from user where memNo = " + memNo;

			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();

			if (rs.next()) {

				String all = rs.getString("bookmark").replaceAll(input, "");

				sql = "update user set bookmark = ? where memNo = ?";

				ptmt = con.prepareStatement(sql);
				ptmt.setString(1, all);
				ptmt.setInt(2, memNo);
				ptmt.executeUpdate();

				sql = "update resDetail set marked = marked-1 where rid= " + rid;

				ptmt = con.prepareStatement(sql);
				ptmt.executeUpdate();

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close();
		}
	}

	public void audd() {

		try {

			sql = "update user  A  set gg = (select (sum(likes)) "
					+ "from review B where A.memNo=B.memNo and (TO_DAYS(sysdate())-TO_DAYS(regdate)<30)) ";

			System.out.println(sql);

			ptmt = con.prepareStatement(sql);

			ptmt.executeUpdate();

			sql = "update user set gg = 0 where gg is null";

			ptmt = con.prepareStatement(sql);

			ptmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close();
		}
	}

	public void point(int likeG, UserDTO dto) {

		try {

			sql = "update user set grade =  ?, gg = ? where memNo=?";

			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.grade);
			ptmt.setInt(2, 0);
			ptmt.setInt(3, dto.memNo);
			ptmt.executeUpdate();

			int pointG[] = { 20000, 5000, 15000 };
			sql = "update user set point = point+? where memNo = ?";

			System.out.println(sql);

			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, pointG[likeG % 3]);
			ptmt.setInt(2, dto.memNo);
			ptmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close();
		}
	}

	public void downG(UserDTO dto) {

		try {

			sql = "update user set grade =  ?, gg = ? where memNo=?";

			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.grade);
			ptmt.setInt(2, 0);
			ptmt.setInt(3, dto.memNo);
			ptmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close();
		}
	}

	public ArrayList<UserDTO> partnerList(int start, int limit) {
		ArrayList<UserDTO> res = new ArrayList<UserDTO>();

		try {

			sql = "select * from user where cate = 2 and grade = 8 limit ?, ?";

			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, start);
			ptmt.setInt(2, limit);
			rs = ptmt.executeQuery();

			while (rs.next()) {
				UserDTO dto = new UserDTO();
				dto.setCate(rs.getInt("cate"));
				dto.setGrade(rs.getInt("grade"));
				dto.setMemNo(rs.getInt("memNo"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setBirth(rs.getString("birth"));
				dto.setGen(rs.getString("gen"));
				dto.setPhone(rs.getString("phone"));
				dto.setMail(rs.getString("mail"));

				res.add(dto);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close();
		}

		return res;
	}

	public int pttotal(int num) {

		int i = 0;

		try {

			if (num == 2) {

				sql = "select count(*) from user where cate = 2";

				ptmt = con.prepareStatement(sql);
				rs = ptmt.executeQuery();

				rs.next();
				i = rs.getInt(1);

			} else if (num == 1) {

				sql = "select count(*) from user where cate = 1";

				ptmt = con.prepareStatement(sql);
				rs = ptmt.executeQuery();

				rs.next();
				i = rs.getInt(1);

			} else if (num == 92) {

				sql = "select count(*) from user where grade = 9 and cate = 2";

				ptmt = con.prepareStatement(sql);
				rs = ptmt.executeQuery();

				rs.next();
				i = rs.getInt(1);

			} else {

				sql = "select count(*) from user where cate = 2 and grade = 8";

				ptmt = con.prepareStatement(sql);
				rs = ptmt.executeQuery();

				rs.next();
				i = rs.getInt(1);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close();
		}

		return i;
	}

	public void deleteReq(UserDTO dto) {

		sql = "update user set grade = 8 where memNo = ?";

		try {
			ptmt = con.prepareStatement(sql);

			ptmt.setInt(1, dto.memNo);

			ptmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close();
		}

	}

	public void useP(UserDTO dto) {
		sql = "update user set point = ? where memNo = ?";

		try {
			ptmt = con.prepareStatement(sql);

			ptmt.setInt(1, dto.getPoint());
			ptmt.setInt(2, dto.getMemNo());

			ptmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close();
		}

	}

	public String[] markedChk(int memNo) {

		String[] div = null;

		sql = "select * from user where memNo = " + memNo;

		try {

			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();

			if (rs.next()) {
				if (rs.getString("bookmark").length() == 5) {
					div = (rs.getString("bookmark") + rs.getString("bookmark")).split(",");
				} else {
					div = rs.getString("bookmark").split(",");
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close();
		}
		return div;
	}

	public Boolean idChk(String id) {
		Boolean res = false;

		sql = "select * from user where id = ? ";

		try {

			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, id);
			rs = ptmt.executeQuery();

			if (rs.next()) {
				res = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return res;
	}

	public Boolean mailChk(String mail) {
		Boolean res = false;

		sql = "select * from user where mail = ? ";

		try {

			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, mail);
			rs = ptmt.executeQuery();

			if (rs.next()) {
				res = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return res;
	}

	public void close() {
		if (rs != null)
			try {
				rs.close();
			} catch (Exception e) {
			}
		if (ptmt != null)
			try {
				rs.close();
			} catch (Exception e) {
			}
		if (con != null)
			try {
				rs.close();
			} catch (Exception e) {
			}
	}

}
