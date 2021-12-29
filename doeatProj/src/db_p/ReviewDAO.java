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

public class ReviewDAO {

	DBSet set = new DBSet();
	
//	String url="jdbc:mysql://192.168.43.153:3306/doeat_db";
//	String id="doeat";
//	String pw="123456";
	String sql;
	
	Connection con;
	PreparedStatement ptmt;
	ResultSet rs;
	ResultSet rs2;
	
	
	public ReviewDAO() { //CRUD에서 항상 실행

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con =  DriverManager.getConnection(set.url, set.id, set.pw);

		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	// 1. restaurant_p/ResDetail - 식당 상세 정보
	
	public Object list(ReviewDTO dto, int no, int start, int end) {
		// 전체 리스트 뽑을 일은 없으니까 dto.setRid()해서 식당아이디로 리스트 뽑기! int no = 0
		// 또는 memNo로 자기 리뷰 볼 수 있도록! - int no = 1
		
		ArrayList<ReviewDTO> list = new ArrayList();
		
		sql = "select * from review where rid = ? order by likes desc limit ?, ?";
		//sql = "select * from review where rid = ?";
		
		if(no==1) {
			sql = "select * from review  where memNo = ? order by likes desc limit ?, ?";
		}
		
		System.out.println("리뷰 리스트 진입완료"+sql);
	
		try {
			
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.rid);
			ptmt.setInt(2, start);
			ptmt.setInt(3, end);
			
			if(no==1) {
				ptmt.setInt(1, dto.memNo);
				ptmt.setInt(2, start);
				ptmt.setInt(3, end);
			}
			
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				
				ReviewDTO rv = new ReviewDTO();
				
				rv.setcno(rs.getInt("cno"));
				rv.setRid(rs.getInt("rid"));
				rv.setMemNo(rs.getInt("memNo"));
				
				rv.setTsrate(rs.getInt("tsrate"));
				rv.setMdrate(rs.getInt("mdrate"));
				rv.setSvrate(rs.getInt("svrate"));
				
				rv.setLikes(rs.getInt("likes"));
				rv.setPic(rs.getString("pic"));
				
				rv.setRegdate(rs.getTimestamp("regdate"));
				
				rv.setContext(rs.getString("context"));
				
				if(rs.getString("who")!=null) {
					rv.setWho(rs.getString("who"));
					System.out.println(rs.getString("who"));
				}
			
				sql = "select rname from resInf inner join resDetail on resInf.rno = resDetail.rno where rid = ?";
				ptmt = con.prepareStatement(sql);
				ptmt.setInt(1, rv.getRid());
				rs2 = ptmt.executeQuery();
				
				rs2.next();
				
				rv.setRname(rs2.getString(1));
				
				list.add(rv);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}

		return list;
	}
	
	public int total(int rid) {
		
		int res = 0;
	
		try {
			
			sql = "select count(*) from review where rid = "+rid;
			
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
			
			rs.next();
			res = rs.getInt(1);			
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		// close하면 안됨. total 구하고 list도 구해야하기때문에!
		
		return res;
	}
	
	public int idvtotal(int memNo) {
		
		int res = 0;
	
		try {
			
			sql = "select count(*) from review where memNo = "+memNo;
			
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
			
			rs.next();
			res = rs.getInt(1);			
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		// close하면 안됨. total 구하고 list도 구해야하기때문에!
		
		return res;
	}
	
	// 1. mypage/reviewdetail - 리뷰 신고 처리
	
	public ReviewDTO oneReview(int no) {
		
		sql = "select * from review where cno = ?";
		
		ReviewDTO rv = null;
		
		System.out.println("리뷰 리스트 진입완료"+sql);
		
		try {
			
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, no);
			rs = ptmt.executeQuery();
			
			if(rs.next()) {
				
				rv = new ReviewDTO();
				
				rv.setcno(rs.getInt("cno"));
				System.out.println(rs.getInt("cno"));
				rv.setRid(rs.getInt("rid"));
				rv.setMemNo(rs.getInt("memNo"));
				
				rv.setTsrate(rs.getInt("tsrate"));
				rv.setMdrate(rs.getInt("tsrate"));
				rv.setSvrate(rs.getInt("tsrate"));
				
				rv.setLikes(rs.getInt("likes"));
				rv.setPic(rs.getString("pic"));
				
				rv.setRegdate(rs.getTimestamp("regdate"));
				
				rv.setContext(rs.getString("context"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		
		return rv;
	}
	
	// restaurant_p/reviewReg -  리뷰입력
	public void insert(ReviewDTO dto) {
		
		sql = "insert into review "+
			"(rid, memNo, tsrate, mdrate, svrate, likes, regdate, context, pic) "+
				"values "+
			"(?, ?, ?, ?, ?, 0, sysdate(), ?, ?)";
		
			System.out.println("review put db \n"+sql);
		
		try {
			
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.rid);
			ptmt.setInt(2, dto.memNo);
			ptmt.setInt(3, dto.tsrate);
			ptmt.setInt(4, dto.mdrate);
			ptmt.setInt(5, dto.svrate);
			ptmt.setString(6, dto.context);
			ptmt.setString(7, dto.pic);
			
			int i = ptmt.executeUpdate();
			System.out.println("처리 결과:"+i);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	// 1. restaurant/ReviewDelete -  리뷰삭제
	// 2. mypage/reviewReport - 신고 리뷰 처리(삭제)
	public int delete(ReviewDTO dto) {
	
		int i = 0;
		
		//sql = "delete from review where memNo = ? and cno = ?";
		sql = "delete from review where cno = ?";
		System.out.println(sql);
		
		try {
			
			ptmt = con.prepareStatement(sql);			
			//ptmt.setInt(1, dto.memNo);
			ptmt.setInt(1, dto.cno);
			i  = ptmt.executeUpdate();
			
			sql = "update coReview set chd = '삭제' where cno = ?";
			ptmt = con.prepareStatement(sql);			
			ptmt.setInt(1, dto.cno);
			i  += ptmt.executeUpdate();
			
			System.out.println("여기는?");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		
		return i;
	}

	// 1. restaurant_p/ReviewLike - 리뷰 좋아요
	
	public void addLike(ReviewDTO dto) {
		
		sql = "update review set likes = likes+1 where cno = ?";
		
	
		System.out.println(sql);
		
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.cno);
			ptmt.executeUpdate();
			
			sql = "update review inner join " + "(SELECT *, IFNULL(who, '') as pr "
					+ "FROM review where cno = ?)plz " + "on review.cno = plz.cno "
					+ "set review.who = concat(pr,?)";
			
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.cno);
			ptmt.setString(2, dto.memNo+",");
			ptmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		
	}
	
	
	public void unlike(ReviewDTO dto) {
		
		try {
			
			sql = "select who from review where cno = ?";

			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.cno);
			rs = ptmt.executeQuery();
			
			if(rs.next()) {
				
				String all = rs.getString("who").replaceAll(dto.getMemNo()+",", "");
				
				sql = "update review set who = ? where cno = ?";
				
				ptmt = con.prepareStatement(sql);				
				ptmt.setString(1, all);	
				ptmt.setInt(2, dto.cno);
				ptmt.executeUpdate();
				
				sql = "update review set likes = likes-1 where cno = ?";

				ptmt = con.prepareStatement(sql);
				ptmt.setInt(1, dto.cno);
				ptmt.executeUpdate();
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close();
		}
	}
	
	 public Object rComm(String memNo) {
	      
	      ArrayList<RecommDTO> list = new ArrayList();
	      
	      sql ="select * from resDetail ,( select  count(*) as tt , rid  from review where memNo="+memNo
	            +" group by rid)tt where resDetail.rid = tt.rid";
	      
	      try {
	         
	         ptmt = con.prepareStatement(sql);
	          
	                  
	         rs = ptmt.executeQuery();
	         System.out.println(sql);
	         
	         while(rs.next()) {
	            
	            RecommDTO dto = new RecommDTO();

	            dto.setRid(rs.getInt("rid"));
	            dto.setRno(rs.getInt("rno"));
	            
	            dto.setRprice1(rs.getString("rprice1"));
	            dto.setRprice2(rs.getString("rprice2"));
	            
	            dto.setRloc1(rs.getString("rloc1"));
	            dto.setRloc2(rs.getString("rloc2"));
	            
	            dto.setRmenu1(rs.getString("rmenu1"));
	            dto.setRmenu2(rs.getString("rmenu2"));
	            
	            dto.setRmain1(rs.getString("rmain1"));
	            dto.setRmain2(rs.getString("rmain2"));
	            dto.setRmain3(rs.getString("rmain3"));
	            
	            dto.setRinfos(rs.getString("rinfos"));
	            
	            list.add(dto);               
	         }
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close();
	      }   
	      
	      return list;
	   }
	
	public void close() {
		if(rs!=null) try{ rs.close();}catch(Exception e) {}
		if(ptmt!=null) try{ rs.close();}catch(Exception e) {}
		if(con!=null) try{ rs.close();}catch(Exception e) {}
	}
	
}
