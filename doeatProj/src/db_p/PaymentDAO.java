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

public class PaymentDAO {

	DBSet set = new DBSet();
	
//	String url="jdbc:mysql://192.168.43.153:3306/doeat_db";
//	String id="doeat";
//	String pw="123456";
	String sql;
	
	Connection con;
	PreparedStatement ptmt;
	ResultSet rs;
	ResultSet rs2;
	
	
	public PaymentDAO() { //CRUD에서 항상 실행

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con =  DriverManager.getConnection(set.url, set.id, set.pw);

		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public int total(int memNo) { // deal 신청내역
		int i = 0;
		
		try {
			sql = "select * from resInf inner join resDetail on resInf.rno "
					+ "= resDetail.rno where memNo = ?";
			
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, memNo);
			rs = ptmt.executeQuery();			
			
			rs.next();			
			i = rs.getInt("rid");
			System.out.println(i);
			
			sql = "select count(*) from payment where rid = ?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, i);
			rs = ptmt.executeQuery();
			
			rs.next();
			i = rs.getInt(1);
			System.out.println(i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		
		return i;
	}
	
	public PaymentDTO detail(PaymentDTO pto) {
		
		PaymentDTO dto = null;
		
		sql = "select * from payment where pno = ?";
		
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, pto.getPno());
			
			rs = ptmt.executeQuery();
			
			if(rs.next()) {
				dto = new PaymentDTO();
				dto.setCardFirm(rs.getString("cardFirm"));
				dto.setPay(rs.getInt("pay"));
				dto.setVolume(rs.getInt("volume"));
				dto.setMemNo(rs.getInt("memNo"));
				dto.setPubd(rs.getTimestamp("pubd"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}
	
	public int purcharse(PaymentDTO dto) {
		
		int res = -1;
		try {
			sql = "insert into payment "
					+"(rid, pubd, pay, cardNum, cardFirm, valm, valy, cardPw, idenNum, phoneNum, memNo, cno, volume) values"
					+"( ? ,sysdate(),?,    ?,        ?,    ?   ,   ?  ,  ?  ,   ?   ,    ?   , ? , ?, 1)";
			
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.getRid());
			ptmt.setInt(2, dto.getPay());
			ptmt.setString(3, dto.getCardNum());
			ptmt.setString(4, dto.getCardFirm());
			ptmt.setString(5, dto.getValm());
			ptmt.setString(6, dto.getValy());
			ptmt.setString(7, dto.getCardPw());
			ptmt.setString(8, dto.getIdenNum());
			ptmt.setString(9, dto.getPhoneNum());
			ptmt.setInt(10, dto.getMemNo());
			ptmt.setInt(11, dto.getCno());
			ptmt.executeUpdate();
			
			System.out.println("purcharse : "+ptmt);
			System.out.println(sql);
			sql = "select max(pno) from payment";
			
			rs = ptmt.executeQuery(sql);
			
			rs.next();
			
			res = rs.getInt(1);
			
			
			sql = "update deal set paycnt = paycnt - 1 where cno = ? ";
			
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.getCno());
			
			ptmt.executeUpdate();
			System.out.println(ptmt);
			
			sql = "select max(pno) as pno from payment";
			rs = ptmt.executeQuery(sql);
			rs.next();
			res = rs.getInt("pno");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		
		return res;
		
	}
	
	
	
/*
	public RefundDTO refund(PaymentDTO pto) {
		RefundDTO rfo = null;
		try {
			sql = "select * from paycoup inner join payment on paycoup.pno=payment.pno where payment.pno= ? and payment.memNo = ?";
			ptmt = con.prepareStatement(sql);
			
			ptmt.setInt(1, pto.getPno());
			ptmt.setInt(2, pto.getMemNo());
			rs = ptmt.executeQuery();
			
			if(rs.next()) {
				rfo = new RefundDTO();
				rfo.setCardFirm(rs.getString("cardFirm"));
				rfo.setCardNum(rs.getString("cardNum"));
				rfo.setCardPw(rs.getString("cardPw"));
				rfo.setCcode(rs.getInt("ccode"));
				rfo.setCond(rs.getInt("cond"));
				rfo.setMemNo(rs.getInt("memNo"));
				rfo.setPrice(rs.getInt("price"));
				rfo.setRid(rs.getInt("rid"));
				rfo.setVolume(rs.getInt("volume"));
				
				
				sql = "update payment set pay=?, volume=? where pno= ?";
				
				ptmt = con.prepareStatement(sql);
				ptmt.setInt(1, 0);//0
				ptmt.setInt(2, 0);//0
				ptmt.setInt(3, pto.getPno());
				rs = ptmt.executeQuery();
				
				ptmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rfo;
		
	}
	
	
*/
	public PaymentDTO refund(int pno) {
		PaymentDTO pto = null;
		try {
			//sql = "update payment set refund = 5, pubd = sysdate() where pno=?";
			
			sql = "update payment set refund = 5 where pno=?";
			
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, pno);
			
			ptmt.executeUpdate();
			
			sql ="select * from payment where pno=?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, pno);
			rs = ptmt.executeQuery();
			if(rs.next()) {
				pto = new PaymentDTO();
				pto.setPay(rs.getInt("pay"));
				pto.setPhoneNum(rs.getString("phoneNum"));
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pto;
	}
	
	   public ArrayList<PayCalDTO> cal(UserDTO udto, int start, int limit) {
		      
		      ArrayList<PayCalDTO> res = new ArrayList<>() ;
		      float pay =0;
		      sql = "select rid, pubdff, sum(price) as pay from (select paycoup.*, date_format(usedate,'%Y-%m') " + 
		            " as pubdff from paycoup, "
		            + " (select rid from  resInf inner join resDetail where resInf.rno = resDetail.rno and memNo = ? ) t1 " + 
		            " where usedate is not null and t1.rid = paycoup.rid) t2 group by rid, pubdff "+
		            "limit ?, ?";
		      
		      try {
		         System.out.println(udto.memNo);
		         ptmt = con.prepareStatement(sql);
		         ptmt.setInt(1, udto.memNo);
		         ptmt.setInt(2, start);
		         ptmt.setInt(3, limit);
		         
		         rs = ptmt.executeQuery();
		         
		         while(rs.next()) {
		            PayCalDTO dto = new PayCalDTO();
		            
		            dto.setPay(rs.getInt("pay"));
		            dto.setPubdff(rs.getString("pubdff"));
		            res.add(dto);
		         }
		         
		      } catch (SQLException e) {
		         // TODO Auto-generated catch block
		         e.printStackTrace();
		      }
		      return res;
		   }
		   
		   public ArrayList<PayCalDTO> pcal(int start, int limit) {
		      
		      ArrayList<PayCalDTO> res = new ArrayList<>() ;
		      float pay =0;
		      sql = " select rid, pubdff, sum(price) as pay from (select paycoup.*, date_format(usedate,'%Y-%m') " + 
		            " as pubdff from paycoup " + 
		            " where usedate is not null ) t2 group by rid, pubdff limit ?, ?";
		      
		      try {
		         ptmt = con.prepareStatement(sql);
		         ptmt.setInt(1, start);
		         ptmt.setInt(2, limit);
		         System.out.println(sql);		   
		         
		         rs = ptmt.executeQuery();
		         
		         while(rs.next()) {
		         //   pay = rs.getFloat("pay");
		            PayCalDTO dto = new PayCalDTO();
		            
		            dto.setRid(rs.getInt("rid"));
		            dto.setPay(rs.getInt("pay"));
		            dto.setPubdff(rs.getString("pubdff"));
		            System.out.println(rs.getInt("rid"));
		            
		            
		            sql = "select rname from resInf, (select rno from resDetail where rid = ?) tt where tt.rno = resInf.rno";
		            
		            ptmt = con.prepareStatement(sql);
		            ptmt.setInt(1, dto.rid);
		            rs2 = ptmt.executeQuery();
		            rs2.next();
		            dto.setRname(rs2.getString("rname"));
		            
		            
		            res.add(dto);
		         }   
		         
		      } catch (SQLException e) {
		         // TODO Auto-generated catch block
		         e.printStackTrace();
		      }
		      return res;
		   }
		   
		   	public int ptotal() {
			     
		   		int i = 0;
		   			
			   ArrayList<PayCalDTO> res = new ArrayList<>() ;
			   float pay =0;
			      
			      sql = " select rid, pubdff, sum(price) as pay from (select paycoup.*, date_format(usedate,'%Y-%m') " + 
			            " as pubdff from paycoup " + 
			            " where usedate is not null ) t2 group by rid, pubdff";
			      
			      try {
			         ptmt = con.prepareStatement(sql);
			         System.out.println(sql);
			   
			         
			         rs = ptmt.executeQuery();
			         
			         while(rs.next()) {
			         //   pay = rs.getFloat("pay");
			            PayCalDTO dto = new PayCalDTO();
			            
			            dto.setRid(rs.getInt("rid"));
			            dto.setPay(rs.getInt("pay"));
			            dto.setPubdff(rs.getString("pubdff"));
			            System.out.println(rs.getInt("rid"));
			            res.add(dto);
			            
			            i++;
			            
			         }   
			         
			      } catch (SQLException e) {
			         // TODO Auto-generated catch block
			         e.printStackTrace();
			      }
			      return i;
			   }
	
	public void close() {
		if(rs!=null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
		if(rs2!=null) {try {rs2.close();} catch (SQLException e) {e.printStackTrace();}}
		if(ptmt!=null) {try {ptmt.close();} catch (SQLException e) {e.printStackTrace();}}
		if(con!=null) {try {con.close();} catch (SQLException e) {e.printStackTrace();}}
	}
}
