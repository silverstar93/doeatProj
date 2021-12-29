package db_p;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import filepath_p.DBSet;

public class PayCoupDAO {

	DBSet set = new DBSet();

//	String url="jdbc:mysql://192.168.43.153:3306/doeat_db";
//	String id="doeat";
//	String pw="123456";
	String sql;

	Connection con;
	PreparedStatement ptmt;
	ResultSet rs, rs2;

	public PayCoupDAO() {
		super();

		try {

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(set.url, set.id, set.pw);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<PayCoupDTO> schCnum(String num) { // 판매내역
		ArrayList<PayCoupDTO> list = new ArrayList<PayCoupDTO>();

		try {
			sql = "select * from paycoup where ccode = ? ";
			System.out.println(sql);
			ptmt = con.prepareStatement(sql);

			ptmt.setInt(1, Integer.parseInt(num));
			rs = ptmt.executeQuery();

			while (rs.next()) {
				PayCoupDTO cto = new PayCoupDTO();

				cto.setRid(rs.getInt("rid"));
				cto.setMemNo(rs.getInt("memNo")); // 구매
				cto.setCcode(rs.getInt("ccode")); // 쿠폰번호(pk)

				cto.setPno(rs.getInt("pno")); // 판매번호
				cto.setPrice(rs.getInt("price")); // 금액

				cto.setCstart(rs.getTimestamp("cstart"));
				cto.setCend(rs.getTimestamp("cend"));

				cto.setCond(rs.getInt("cond"));
				System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" + rs.getInt("cond"));
				cto.setUsedate(new Date());
				// usedate(사용일) 가져올 때, null이면 오류나니까 안전장치해두고

				if (rs.getInt("cond") == 1) {
					// 사용한 경우에만 usedate를 가져온다
					cto.setUsedate(rs.getTimestamp("usedate"));
				}
				list.add(cto);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public ArrayList<PayCoupDTO> myeatdeal(int memNo, int start, int end) {
		ArrayList<PayCoupDTO> res = new ArrayList<PayCoupDTO>();

		try {
			sql = "select cmenu,t1.* from deal,(select t0.*,payment.cno "
					+ " from payment,(select  cstart,cend,memNo,ccode,pno,cond,usedate from paycoup where memNo = ? group by ccode)t0 "
					+ " where payment.pno = t0.pno)t1 " + " where deal.cno = t1.cno ";

			sql = "select cmenu,t1.* from deal,(select t0.*,payment.cno " + " from payment,"
					+ "(select  cstart,cend,memNo,ccode,pno,cond,usedate from paycoup where memNo = ? and cond != 5)t0 "
					+ " where payment.pno = t0.pno)t1 " + " where deal.cno = t1.cno limit ?, ?";
			
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, memNo);
			ptmt.setInt(2, start);
			ptmt.setInt(3, end);
			rs = ptmt.executeQuery();

			while (rs.next()) {
				PayCoupDTO cto = new PayCoupDTO();
				cto.setMemNo(rs.getInt("memNo"));
				cto.setCmenu(rs.getString("cmenu"));

				cto.setCcode(rs.getInt("ccode"));
				cto.setCond(rs.getInt("cond"));

				cto.setCstart(rs.getTimestamp("cstart"));
				cto.setCend(rs.getTimestamp("cend"));
				cto.setUsedate(rs.getTimestamp("usedate"));
				cto.setPno(rs.getInt("pno"));

				System.out.println(cto.getMemNo());
				res.add(cto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return res;
	}

	public ArrayList<PayCoupDTO> myrefund(int memNo, int start, int limit) {
		ArrayList<PayCoupDTO> res = new ArrayList<PayCoupDTO>();

		try {
			sql = "select * from paycoup where memNo = ? and cond = 5 " + "limit ?, ?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, memNo);
			ptmt.setInt(2, start);
			ptmt.setInt(3, memNo);
			rs = ptmt.executeQuery();

			while (rs.next()) {
				PayCoupDTO cto = new PayCoupDTO();
				cto.setMemNo(rs.getInt("memNo"));
				cto.setRid(rs.getInt("rid"));
				cto.setCcode(rs.getInt("ccode"));
				cto.setCond(rs.getInt("cond"));
				cto.setPrice(rs.getInt("price"));
				cto.setCstart(rs.getTimestamp("cstart"));
				cto.setCend(rs.getTimestamp("cend"));
				cto.setUsedate(rs.getTimestamp("usedate"));
				cto.setPno(rs.getInt("pno"));

				System.out.println(cto.getMemNo());
				res.add(cto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return res;
	}

	public int rftotal(int memNo) {

		int res = 0;

		try {
			sql = "select count(*) from paycoup where memNo = ? and cond = 5";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, memNo);
			rs = ptmt.executeQuery();

			rs.next();
			res = rs.getInt(1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return res;
	}

	public int memtotal(int memNo) {

		int res = 0;

		try {
			sql = "select count(*) from paycoup where cond != 5 and memNo = ?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, memNo);
			rs = ptmt.executeQuery();

			if(rs.next()) {
			res = rs.getInt(1);
			}else {
				res = 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 바로 내역 뽑아야하니까 닫지 않는다

		return res;
	}

	public void usedCoup(String num) {

		try {
			sql = "update paycoup set cond = 1, usedate = sysdate() where ccode = ? ";

			ptmt = con.prepareStatement(sql);

			ptmt.setInt(1, Integer.parseInt(num));
			ptmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}

	}

	public Object admlist(int start, int limit) { // 관리자 잇딜 쿠폰 사용내역 확인
		ArrayList<PayCoupDTO> list = new ArrayList<PayCoupDTO>();

		try {
			sql = "select * from paycoup limit ?, ?";

			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, start);
			ptmt.setInt(2, limit);
			rs = ptmt.executeQuery();

			while (rs.next()) {
				PayCoupDTO cto = new PayCoupDTO();

				cto.setRid(rs.getInt("rid"));
				cto.setMemNo(rs.getInt("memNo")); // 구매
				cto.setCcode(rs.getInt("ccode")); // 쿠폰번호(pk)
				cto.setPno(rs.getInt("pno")); // 판매번호
				cto.setPrice(rs.getInt("price")); // 금액
				cto.setCstart(rs.getTimestamp("cstart"));
				cto.setCend(rs.getTimestamp("cend"));
				cto.setCond(rs.getInt("cond"));
				cto.setUsedate(new Date()); // usedate(사용일) 가져올 때, null이면 오류나니까 안전장치해두고

				if (rs.getInt("cond") == 1) {
					// 사용한 경우에만 usedate를 가져온다
					cto.setUsedate(rs.getTimestamp("usedate"));
				}

				sql = "select id from user where memNo = ? ";
				ptmt = con.prepareStatement(sql);
				ptmt.setInt(1, cto.memNo);
				rs2 = ptmt.executeQuery();
				if(rs2.next()) {
					cto.setId(rs2.getString("id"));
				}else {
					cto.setId("탈퇴");
				}
				

				sql = "select rname from resInf, (select rno from resDetail where rid = ?) tt where tt.rno = resInf.rno";

				ptmt = con.prepareStatement(sql);
				ptmt.setInt(1, cto.rid);
				rs2 = ptmt.executeQuery();
				if(rs2.next()) {
					cto.setRname(rs2.getString("rname"));
				}else {
					System.out.println(cto.rid);
					cto.setRname("존재하지 않음");
				}
				

				list.add(cto);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}


	public Object salesList(int memNo, int start, int end) { // 판매내역
		ArrayList<PayCoupDTO> list = new ArrayList<PayCoupDTO>();

		int i = 0;

		try {
			sql = "select * from resInf inner join resDetail on resInf.rno = resDetail.rno where memNo = ?";

			System.out.println(sql);

			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, memNo);
			rs = ptmt.executeQuery();

			rs.next();
			i = rs.getInt("rid");
			System.out.println(i);

			sql = "select paycoup.*,payment.pay from paycoup inner join payment on " + 
					" payment.rid = paycoup.rid " + 
					"where paycoup.rid = ? limit ?, ?";

			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, i);
			ptmt.setInt(2, start);
			ptmt.setInt(3, end);
			rs = ptmt.executeQuery();

			while (rs.next()) {
				PayCoupDTO cto = new PayCoupDTO();

				cto.setRid(rs.getInt("rid"));
				cto.setMemNo(rs.getInt("memNo")); // 구매
				cto.setCcode(rs.getInt("ccode")); // 쿠폰번호(pk)

				cto.setPno(rs.getInt("pno")); // 판매번호
				cto.setPrice(rs.getInt("pay")); // 금액

				cto.setCstart(rs.getTimestamp("cstart"));
				cto.setCend(rs.getTimestamp("cend"));

				cto.setCond(rs.getInt("cond"));
				System.out.println(rs.getInt("cond"));

				cto.setUsedate(new Date());
				// usedate(사용일) 가져올 때, null이면 오류나니까 안전장치해두고

				if (rs.getInt("cond") == 1) {
					// 사용한 경우에만 usedate를 가져온다
					cto.setUsedate(rs.getTimestamp("usedate"));
				}

				list.add(cto);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}


	public Object list() {
		ArrayList<PayCoupDTO> res = new ArrayList<PayCoupDTO>();

		sql = "select * from paycoup where cond =1";
		try {
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();

			while (rs.next()) {
				PayCoupDTO cto = new PayCoupDTO();
				cto.setCcode(rs.getInt("ccode"));
				cto.setRid(rs.getInt("rid"));
				cto.setMemNo(rs.getInt("memNo"));

				res.add(cto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return res;
	}

	public int total(int memNo) {
		int i = 0;

		try {

			if (memNo == 0) {

				sql = "select count(*) from paycoup";
				ptmt = con.prepareStatement(sql);
				rs = ptmt.executeQuery();

				rs.next();
				i = rs.getInt(1);

			} else {

				sql = "select * from resInf inner join resDetail on resInf.rno " + "= resDetail.rno where memNo = ?";

				ptmt = con.prepareStatement(sql);
				ptmt.setInt(1, memNo);
				rs = ptmt.executeQuery();

				rs.next();
				i = rs.getInt("rid");
				System.out.println(i);

				sql = "select count(*) from paycoup where rid = ?";
				ptmt = con.prepareStatement(sql);
				ptmt.setInt(1, i);
				rs = ptmt.executeQuery();

				rs.next();
				i = rs.getInt(1);
				System.out.println(i);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}

		return i;
	}

	public Object compaylist(int pno) {
		ArrayList<PayCoupDTO> res = new ArrayList<PayCoupDTO>();

		sql = "select * from paycoup where pno= ?";
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, pno);
			rs = ptmt.executeQuery();

			while (rs.next()) {
				PayCoupDTO cto = new PayCoupDTO();
				cto.setCcode(rs.getInt("ccode"));
				cto.setRid(rs.getInt("rid"));
				cto.setMemNo(rs.getInt("memNo"));
				cto.setPrice(rs.getInt("price"));
				cto.setCstart(rs.getTimestamp("cstart"));
				cto.setCend(rs.getTimestamp("cend"));

				res.add(cto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return res;
	}

	public boolean deleteReq(int memNo) {
		boolean res = true;
		int rr = 0;

		sql = "select cond from paycoup where memNo= ?";
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, memNo);
			rs = ptmt.executeQuery();

			if (rs.next()) {
				rr = rs.getInt("cond");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (rr == 2)
			res = false;
		return res;
	}

	public void pur(PayCoupDTO cto) {

		sql = "insert into paycoup " + "(rid, memNo, cond, price, cstart, cend, pno) values " + "(?,?,?,?,?,?,?)";

		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, cto.getRid());
			ptmt.setInt(2, cto.getMemNo());
			ptmt.setInt(3, cto.getCond());
			ptmt.setInt(4, cto.getPrice());
			ptmt.setString(5, cto.getCstartStr());
			ptmt.setString(6, cto.getCendStr());
			ptmt.setInt(7, cto.getPno());
			ptmt.executeUpdate();
			System.out.println(ptmt);
			System.out.println(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean refund(int pno) {
		boolean res = false;
		try {
			sql = "update paycoup set cond = 5 where pno=?";

			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, pno);

			res = ptmt.executeUpdate() > 0;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public PayCoupDTO detail(PayCoupDTO pdto) {

		PayCoupDTO dto = new PayCoupDTO();
		sql = "SELECT * FROM doeat_db.paycoup where pno=?";

		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, pdto.pno);
			rs = ptmt.executeQuery();

			if (rs.next()) {
				dto.setCcode(rs.getInt("ccode"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dto;
	}

	public ArrayList<Integer> pnoSch(int memNo) {
		ArrayList<Integer> res = new ArrayList<Integer>();

		try {
			sql = "select cmenu,t1.* from deal,(select t0.*,payment.cno from payment,"
					+ "(select  cstart,cend,memNo,ccode,pno,cond,usedate from paycoup where memNo = ? and cond != 5) t0 "
					+ " where payment.pno = t0.pno)t1 where deal.cno = t1.cno ";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, memNo);
			rs = ptmt.executeQuery();

			while (rs.next()) {
				int i;
				i = rs.getInt("pno");

				res.add(i);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return res;
	}
	
	
	public ArrayList<Integer> refundPno(int memNo) {
		ArrayList<Integer> res = new ArrayList<Integer>();

		try {
			sql = "select cmenu,t1.* from deal,(select t0.*,payment.cno from payment,"
					+ "(select  cstart,cend,memNo,ccode,pno,cond,usedate from paycoup where memNo = ? and cond != 5) t0 "
					+ " where payment.pno = t0.pno)t1 where deal.cno = t1.cno ";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, memNo);
			rs = ptmt.executeQuery();

			while (rs.next()) {
				int i;
				if(new Date().after(rs.getDate("cend"))==true) {
				i = rs.getInt("pno");

				res.add(i);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return res;
	}
	
	public void refundAuto(int rid) {
		
		sql = "select * from paycoup where rid = "+rid;

		try {
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
		
			while(rs.next()) {
				int i = 0;
				if(new Date().after(rs.getDate("cend"))==true) {												
					
					sql = "select paycoup.pno from paycoup inner join payment on payment.pno = paycoup.pno "+
									"where paycoup.rid = "+rid+" and cond = 2";
					
					ptmt = con.prepareStatement(sql);
					rs = ptmt.executeQuery();									
					
					while(rs.next()) {
						
						i = rs.getInt("pno");
						
						sql = "update payment set refund = 5 where pno = "+i;
						ptmt = con.prepareStatement(sql);
						ptmt.executeUpdate();
						
					}
					
					sql = "update paycoup set cond = 5 where cond = 2 and rid = "+rid;
					ptmt = con.prepareStatement(sql);
					ptmt.executeUpdate();	
					
				}
			}	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		
	}

	/*
	 * public RefundDTO refund(int pno, int memNo) { RefundDTO rfo = null;
	 * 
	 * try {
	 * 
	 * sql =
	 * "select * from paycoup inner join payment on paycoup.pno=payment.pno where payment.pno= ? and payment.memNo = ?"
	 * ; ptmt = con.prepareStatement(sql);
	 * 
	 * ptmt.setInt(1, pno); ptmt.setInt(2, memNo); rs = ptmt.executeQuery();
	 * 
	 * if(rs.next()) { rfo = new RefundDTO();
	 * rfo.setCardFirm(rs.getString("cardFirm"));
	 * rfo.setCardNum(rs.getString("cardNum"));
	 * rfo.setCardPw(rs.getString("cardPw")); rfo.setCcode(rs.getInt("ccode"));
	 * rfo.setCond(rs.getInt("cond")); rfo.setMemNo(rs.getInt("memNo"));
	 * rfo.setPrice(rs.getInt("price")); rfo.setRid(rs.getInt("rid"));
	 * rfo.setVolume(rs.getInt("volume")); } } catch (SQLException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } return rfo; }
	 * 
	 * public Object myeatdeal(int memNo) { ArrayList<PayCoupDTO> res = new
	 * ArrayList<PayCoupDTO>();
	 * 
	 * try { sql = "select * from paycoup where memNo="+memNo; ptmt =
	 * con.prepareStatement(sql);
	 * 
	 * rs = ptmt.executeQuery();
	 * 
	 * while (rs.next()) { PayCoupDTO cto = new PayCoupDTO();
	 * cto.setMemNo(rs.getInt("memNo")); cto.setRid(rs.getInt("rid"));
	 * cto.setCcode(rs.getInt("ccode")); cto.setVolume(rs.getInt("volume"));
	 * cto.setCstart(rs.getTimestamp("cstart"));
	 * cto.setCend(rs.getTimestamp("cend")); cto.setPubd(rs.getTimestamp("pubd"));
	 * cto.setPay(rs.getInt("pay")); cto.setCardFirm(rs.getString("cardFirm"));
	 * cto.setCardNum(rs.getString("cardNum"));
	 * 
	 * res.add(cto); } } catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }finally { close(); } return res; }
	 */

	public void close() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (rs2 != null) {
			try {
				rs2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (ptmt != null) {
			try {
				ptmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
