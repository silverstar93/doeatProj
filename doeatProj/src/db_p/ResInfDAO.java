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

public class ResInfDAO {

	DBSet set = new DBSet();
	
//	String url="jdbc:mysql://192.168.43.153:3306/doeat_db";
//	String id="doeat";
//	String pw="123456";
	String sql;
	
	Connection con;
	PreparedStatement ptmt;
	ResultSet rs;
	
	
	public ResInfDAO() { //CRUD에서 항상 실행

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con =  DriverManager.getConnection(set.url, set.id, set.pw);


		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	// 1. mypage/resMng - 식당 이동
	public Object list(int start, int limit) {
		ArrayList<RestaurantDTO> list = new ArrayList();
		
		sql = "select * from resInf inner join resDetail on "+
				"resInf.rno = resDetail.rno limit ?, ?";
			
		try {
			
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, start);
			ptmt.setInt(2, limit);
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				
				RestaurantDTO dto = new RestaurantDTO();
				
				dto.setRno(rs.getInt("rno"));
				dto.setMemNo(rs.getInt("memNo"));
				dto.setRid(rs.getInt("rid"));
				
				dto.setRname(rs.getString("rname"));
				dto.setRowner(rs.getString("rowner"));
				dto.setRaddr(rs.getString("raddr"));
				dto.setRtel(rs.getString("rtel"));
				
				dto.setRregdate(rs.getTimestamp("rregdate"));
				//dto.setRdeldate(rs.getTimestamp("rdeldate"));
				
				dto.setRregdateStr(rs.getString("rregdate"));
				//dto.setRdeldateStr(rs.getString("rdeldate"));
				
				list.add(dto);			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		
		return list;
	}
	
	public String rnoChk() {
		String rno = "";
		
		sql = "select rno from resInf";
			
		try {
			
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				rno += rs.getInt("rno")+",";		
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}		
		
		return rno;
	}
	
	public int total() {
		int i = 0;
		
		sql = "select count(*) from resInf";
			
		try {
			
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
			rs.next();
			i = rs.getInt(1);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}				
		return i;
	}
	
	// 1. mypage/resRegister - 관리자 식당 등록
	// 2. mypage/resAccept - 관리자 요청 식당 수락
	
	public void insert(ResInfDTO dto, String admin) {
		
		sql = "insert into resInf "+
				"(rno, rname, rowner, raddr, rtel, memNo, admNo, rregdate) values "+
				"(?, ?, ?, ?, ?, ?, ?, sysdate())";
		
		try {
			
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.rno);
			ptmt.setString(2, dto.rname);
			ptmt.setString(3, admin);
			ptmt.setString(4, dto.raddr);
			ptmt.setString(5, dto.rtel);			
			ptmt.setInt(6, dto.memNo);
			ptmt.setInt(7, 900001);
			ptmt.executeUpdate();
			
			if(!admin.equals("admin")) {
				
				sql = "update user set cate = 2, grade = 1, favMenu = null, "+
						"myLocal = null where memNo = ?";
				
				ptmt = con.prepareStatement(sql);
				ptmt.setInt(1, dto.memNo);
				ptmt.executeUpdate();
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	// 1. restaurant/ResAdmDelReg - 관리자 전용 식당 삭제
	
	public void delete(ResInfDTO dto, int rid) {		
		
		try {

			sql = "delete from resInf where rno = ?";
			
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.rno);
			ptmt.executeUpdate();

			// foreign key 를 cascade로 잡아서, 
			// 삭제하면 같이 삭제된다
			if(dto.getMemNo()==0) {
				
				return;
				
			}else {
			
			sql = "update user set cate = 1, grade = 1 where memNo = ?";
			
			System.out.println(sql);
			
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.memNo);
			ptmt.executeUpdate();
			
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		
	}
	
	
//	public Object Detail(ResInfDTO dto) {
//		
//		ResInfDTO inf = null;
//		
//		sql = "select * from resInf where rno = ?";
//			
//		try {
//			
//			ptmt = con.prepareStatement(sql);
//			ptmt.setInt(1, dto.rno);
//			rs = ptmt.executeQuery();
//			
//			while(rs.next()) {
//				
//				inf = new ResInfDTO();
//				
//				inf.setRno(rs.getInt("rno"));
//				inf.setMemNo(rs.getInt("memNo"));
//				
//				inf.setRname(rs.getString("rname"));
//				inf.setRowner(rs.getString("rowner"));
//				inf.setRaddr(rs.getString("raddr"));
//				inf.setRtel(rs.getString("rtel"));		
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close();
//		}	
//		return inf;
//	}
	
	
	public RecommDTO detail(RecommDTO rdto) {
        
        
        RecommDTO dto = null;
           
           sql = "select * from resInf where rno = ?";
              
           try {
              
              ptmt = con.prepareStatement(sql);
                ptmt.setInt(1, rdto.rno);
              rs = ptmt.executeQuery();
              System.out.println(sql);
              
              while(rs.next()) {

                 
                 dto = new RecommDTO();
                 
                 dto.setRno(rs.getInt("rno"));
                 dto.setMemNo(rs.getInt("memNo"));
                 
                 dto.setRname(rs.getString("rname"));
                 dto.setRowner(rs.getString("rowner"));
                 dto.setRaddr(rs.getString("raddr"));
                 dto.setRtel(rs.getString("rtel"));
                 
                 
              }
              
           } catch (SQLException e) {
              e.printStackTrace();
           } finally {
              close();
           }
           
           
           return dto;
        }

	   public String srchName(Integer text) {
		      
		      
		      ResInfDTO dto = null;
		      
		      sql = "select * from resInf inner join resDetail on resInf.rno = resDetail.rno where rid = "+text;
		         
		      try {
		         
		         ptmt = con.prepareStatement(sql);
		         rs = ptmt.executeQuery();
		         System.out.println(sql);
		         
		         while(rs.next()) {

		            
		            dto = new ResInfDTO();
		            
		            dto.setRno(rs.getInt("rno"));
		            dto.setMemNo(rs.getInt("memNo"));
		            
		            dto.setRname(rs.getString("rname"));
		               
		            
		            
		         }
		         
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } finally {
		         close();
		      }
		      
		      
		      return dto.getRname();
		   }
		   
		   public String srch(String text) {
		      
		      
		      ResInfDTO dto = null;
		      
		      sql = "select * from resInf where rname like '%"+text+"%'";
		         
		      try {
		         
		         ptmt = con.prepareStatement(sql);
		         rs = ptmt.executeQuery();
		         System.out.println(sql);
		         
		         while(rs.next()) {

		            
		            dto = new ResInfDTO();
		            
		            dto.setRno(rs.getInt("rno"));
		            dto.setMemNo(rs.getInt("memNo"));
		            
		            dto.setRname(rs.getString("rname"));
		            dto.setRowner(rs.getString("rowner"));
		            dto.setRaddr(rs.getString("raddr"));
		            dto.setRtel(rs.getString("rtel"));
		            
		            dto.setRregdate(rs.getTimestamp("rregdate"));
		            dto.setRdeldate(rs.getTimestamp("rdeldate"));
		            
		            dto.setRregdateStr(rs.getString("rregdate"));
		            dto.setRdeldateStr(rs.getString("rdeldate"));
		               
		            
		            
		         }
		         
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } finally {
		         close();
		      }
		      
		      
		      return dto.getRname();
		   }
		   
		   
		   
		   
		   
		   
		   public ResInfDTO getAddr(String text) {
			      
			      
			      ResInfDTO dto = null;
			      
			      sql = "select resInf.* from resInf,(select rno from resDetail where rid = "+text+" ) tt where resInf.rno=tt.rno";
			         
			      try {
			         
			         ptmt = con.prepareStatement(sql);
			         rs = ptmt.executeQuery();
			         
			         while(rs.next()) {

			            
			            dto = new ResInfDTO();
			            
			            dto.setRname(rs.getString("rname"));
			            dto.setRaddr(rs.getString("raddr"));
			               
			            
			         }
			         
			      } catch (SQLException e) {
			         e.printStackTrace();
			      } finally {
			         close();
			      }
			      
			      
			      return dto;
			   }
		
		      
		   
		   
			public void deleteMem(int mem) {
				
				sql = "update resInf set memNo = 0 where memNo = ? ";
				
				try {
					
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
	
	public void close() {
		if(rs!=null) try{ rs.close();}catch(Exception e) {}
		if(ptmt!=null) try{ rs.close();}catch(Exception e) {}
		if(con!=null) try{ rs.close();}catch(Exception e) {}
	}
	
}
