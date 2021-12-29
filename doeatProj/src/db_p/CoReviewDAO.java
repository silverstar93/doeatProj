package db_p;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import filepath_p.DBSet;

public class CoReviewDAO {
	
	DBSet set = new DBSet();
	
//	String url="jdbc:mysql://192.168.43.153:3306/doeat_db";
//	String id="doeat";
//	String pw="123456";
	String sql;
	
	Connection con;
	PreparedStatement ptmt;
	ResultSet rs;
	ResultSet rs2;
	
	public CoReviewDAO() { //CRUD에서 항상 실행

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con =  DriverManager.getConnection(set.url, set.id, set.pw);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	
	public Object list() {
		
		ArrayList<CoReviewDTO> list = new ArrayList();
		
		sql = "select * from CoReview order by conum desc";
		System.out.println(sql);
		
		try {
			
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
			int chk = 0;
			while(rs.next()) {
				chk++;
				
				CoReviewDTO cr = new CoReviewDTO();
				
				cr.setConum(rs.getInt("conum"));				
				cr.setRid(rs.getInt("rid"));
				cr.setCno(rs.getInt("cno"));
				cr.setMemNo(rs.getInt("memNo"));
				cr.setCrs(rs.getString("crs"));
				cr.setCrsdt(rs.getString("crsdt"));
				cr.setChd(rs.getString("chd"));
				
				cr.setReqdate(rs.getTimestamp("reqdate"));
				cr.setPrcdate(rs.getTimestamp("prcdate"));
				
				sql = "select rname from resInf inner join resDetail on resInf.rno = resDetail.rno "
						+"where rid = "+cr.getRid();
				
				ptmt = con.prepareStatement(sql);
				rs2 = ptmt.executeQuery();
				
				rs2.next();
				cr.setRname(rs2.getString(1));
				
				list.add(cr);
				
			}
			System.out.println(chk);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//close();
		}
		return list;		
	}
	
	public String cnoChk() {
		String cno = "";
		
		sql = "select cno from coReview";
			
		try {
			
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				cno += rs.getInt("cno")+",";		
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}		
		
		return cno;
	}
	
	public int total(int rid) {
		
		int res = 0;
	
		try {
			
			if(rid==0){
				
				sql = "select count(*) from coReview";
				
				ptmt = con.prepareStatement(sql);
				rs = ptmt.executeQuery();
				
				rs.next();
				res = rs.getInt(1);	
				
			}else {
			
			sql = "select count(*) from coReview where rid = "+rid;
			
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
			
			rs.next();
			res = rs.getInt(1);	
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		// close하면 안됨. total 구하고 list도 구해야하기때문에!
		
		return res;
	}
	
	public Object onelist(int rid, int start, int limit) {	
		
		ArrayList<CoReviewDTO> list = new ArrayList();
		
		sql = "select * from CoReview where rid = ? limit ?, ?";
		
		System.out.println(sql);
		
		try {
			
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, rid);
			ptmt.setInt(2, start);
			ptmt.setInt(3, limit);
			rs = ptmt.executeQuery();
	
			while(rs.next()) {

				CoReviewDTO cr = new CoReviewDTO();
				
				cr.setConum(rs.getInt("conum"));				
				cr.setRid(rs.getInt("rid"));
				cr.setCno(rs.getInt("cno"));
				cr.setMemNo(rs.getInt("memNo"));
				
				cr.setCrs(rs.getString("crs"));
				cr.setChd(rs.getString("chd"));				
				
				cr.setReqdate(rs.getTimestamp("reqdate"));
				cr.setPrcdate(rs.getTimestamp("prcdate"));

				list.add(cr);
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return list;		
	}

	
	//1. restaurant/RvReportReg - 신고된 리뷰 DB등록 
	
	public void insert(CoReviewDTO dto) {
		
		sql = "insert into CoReview "+
				"(chd, prcdate, reqdate, rid, cno, memNo, crs, crsdt) values "+
				"('yet', sysdate(), sysdate(), ?, ?, ?, ?, ?)";
		
		try {
			
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.rid);
			ptmt.setInt(2, dto.cno);
			ptmt.setInt(3, dto.memNo);
			ptmt.setString(4, dto.crs);
			ptmt.setString(5, dto.crsdt);
			ptmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	// 1. mypage/reviewReport - 신고 리뷰 처리(반려)
	
	public void reject(CoReviewDTO dto) {
		
		sql = "update CoReview set "
				+ "chd = '반려', prcdate = sysdate() where conum = ? ";
		
		try {
			
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.conum);
			ptmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public void accept(CoReviewDTO dto) {
		
		sql = "update CoReview set "
				+ "chd = '삭제', prcdate = sysdate() where conum = ? ";
		
		try {
			
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.conum);
			ptmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public void close() {
		if(rs!=null) try{ rs.close();}catch(Exception e) {}
		if(ptmt!=null) try{ rs.close();}catch(Exception e) {}
		if(con!=null) try{ rs.close();}catch(Exception e) {}
	}
	
	
}
