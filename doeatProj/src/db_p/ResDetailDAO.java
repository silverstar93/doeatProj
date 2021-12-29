package db_p;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import filepath_p.DBSet;

public class ResDetailDAO {
	
	DBSet set = new DBSet();
	
//	String url="jdbc:mysql://192.168.43.153:3306/doeat_db";
//	String id="doeat";
//	String pw="123456";
	String sql;
	
	Connection con;
	PreparedStatement ptmt;
	ResultSet rs;
	
	
	public ResDetailDAO() { //CRUD에서 항상 실행
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con =  DriverManager.getConnection(set.url, set.id, set.pw);

		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public Object list() {
		ArrayList<ResDetailDTO> list = new ArrayList();
		
		sql = "select * from resDetail";
			
		try {
			
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				
				ResDetailDTO dto = new ResDetailDTO();

				dto.setRid(rs.getInt("rid"));
				dto.setRno(rs.getInt("rno"));
				
				dto.setRchgdate(rs.getTimestamp("rchgdate"));
				dto.setRchgdateStr(rs.getString("rchgdate"));
				
				dto.setRhour(rs.getString("rhour"));
				
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
	
	
	// 1. mypage/resmine - 파트너 마이페이지에서 본인 식당으로 우회
	public int identify(int mem) {
		
		int i = 0;
		
		try {
			sql = "select * from resInf inner join resDetail on resInf.rno = resDetail.rno where memNo = ?";
			
			System.out.println(sql);
			
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, mem);
			rs = ptmt.executeQuery();			
			
			if(rs.next()) {
				i = rs.getInt("rid");
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}	
		
		return i;
	}
	
	public ResDetailDTO Detail(ResDetailDTO dto) {
		
		ResDetailDTO inf = null;
		
		try {
			sql = "select * from resDetail where rid = ? ";
			System.out.println(sql);
			
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.getRid());
			rs = ptmt.executeQuery();
			
			
			while(rs.next()) {
				
				inf = new ResDetailDTO();			
				
				inf.setRno(rs.getInt("rno"));
				
				inf.setRchgdate(rs.getTimestamp("rchgdate"));
				inf.setRchgdateStr(rs.getString("rchgdate"));
				
				inf.setRprice1(rs.getString("rprice1"));
				inf.setRprice2(rs.getString("rprice2"));
				
				inf.setRloc1(rs.getString("rloc1"));
				inf.setRloc2(rs.getString("rloc2"));
				
				inf.setRmenu1(rs.getString("rmenu1"));
				inf.setRmenu2(rs.getString("rmenu2"));		
				
				inf.setRmain1(rs.getString("rmain1"));
				inf.setRmain2(rs.getString("rmain2"));
				inf.setRmain3(rs.getString("rmain3"));
				
				inf.setMarked(rs.getInt("marked"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}	
		return inf;
	}
	
	// 1. restaurant_p/ResDetail - 식당 상세 정보
	// 2. restaurant_p/ResModify - 식당 정보 수정
	public Object DetailAll(RestaurantDTO dto) {
		
		DetailList(dto);
		
		RestaurantDTO inf = null;
		
		sql = "select * from resInf, (select * from resDetail where rid = ?)tt where tt.rno = resInf.rno";
		
		System.out.println(sql);
		
		try {
			
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.rid);
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				
				inf = new RestaurantDTO();			
				
				inf.setRid(rs.getInt("rid"));
				inf.setRno(rs.getInt("rno"));
				
				inf.setRchgdateStr(rs.getString("rchgdate"));
				inf.setRregdateStr(rs.getString("rregdate"));
				//inf.setRdeldateStr(rs.getString("rdeldate"));
				
				inf.setRchgdate(rs.getTimestamp("rchgdate"));
				inf.setRregdate(rs.getTimestamp("rregdate"));
				//inf.setRdeldate(rs.getTimestamp("rdeldate"));
				
				inf.setRprice1(rs.getString("rprice1"));
				inf.setRprice2(rs.getString("rprice2"));
				
				inf.setRloc1(rs.getString("rloc1"));
				inf.setRloc2(rs.getString("rloc2"));
				
				inf.setRmenu1(rs.getString("rmenu1"));
				inf.setRmenu2(rs.getString("rmenu2"));				
				
				inf.setRmain1(rs.getString("rmain1"));
				inf.setRmain2(rs.getString("rmain2"));
				inf.setRmain3(rs.getString("rmain3"));
				
				//inf.setRinfos(rs.getString("rinfos"));	
				inf.setMemNo(rs.getInt("memNo"));
				
				inf.setRname(rs.getString("rname"));
				inf.setRowner(rs.getString("rowner"));
				inf.setRaddr(rs.getString("raddr"));
				inf.setRtel(rs.getString("rtel"));
				inf.setRhour(rs.getString("rhour"));	
				
				inf.setMarked(rs.getInt("marked"));
				inf.setScore(rs.getFloat("score"));
				inf.setPic(rs.getString("pic"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}	
		return inf;
	}
	
	// 1. restaurant_p/ResPatModifyReg - 파트너 전용 리뷰 수정 처리
	public void modify(ResDetailDTO dto) {
		
		sql = "update resDetail set rhour = ?, rmenu1 = ?, rmenu2 = ?, "+
				"rprice1 = ?, rprice2 = ?, rmain1 = ?, rmain2 = ?, rmain3 = ?, rchgdate = sysdate() where rid = ?";
	
		System.out.println(sql);
		
		try {
			
			ptmt = con.prepareStatement(sql);
			
			ptmt.setString(1, dto.rhour);
			System.out.println(dto.rhour);
			ptmt.setString(2, dto.rmenu1);
			ptmt.setString(3, dto.rmenu2);
			ptmt.setString(4, dto.rprice1);
			ptmt.setString(5, dto.rprice2);
			ptmt.setString(6, dto.rmain1);
			ptmt.setString(7, dto.rmain2);
			ptmt.setString(8, dto.rmain3);
			ptmt.setInt(9, dto.rid);
			
			ptmt.executeUpdate();
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	// 1. restaurant/ResAdmModifyReg - 관리자 전용 식당 수정
	
	public void modifyAll(RestaurantDTO dto) {
		
		sql = "update resDetail set rhour = ?, rmenu1 = ?, rmenu2 = ?, "+
				"rprice1 = ?, rprice2 = ?, rmain1 = ?, rmain2 = ?, rmain3 = ?, rchgdate = sysdate(), "+
				"rloc2 = ? where rid = ?";
	
		System.out.println("1차테이블삽입"+sql);
		
		try {
			
			ptmt = con.prepareStatement(sql);
			
			ptmt.setString(1, dto.rhour);
			ptmt.setString(2, dto.rmenu1);
			ptmt.setString(3, dto.rmenu2);
			ptmt.setString(4, dto.rprice1);
			ptmt.setString(5, dto.rprice2);
			ptmt.setString(6, dto.rmain1);
			ptmt.setString(7, dto.rmain2);
			ptmt.setString(8, dto.rmain3);
			ptmt.setString(9, dto.rloc2);
			ptmt.setInt(10, dto.rid);
			
			ptmt.executeUpdate();
		
			sql = "update resInf set rno = ?, rname = ?, rowner = ?, raddr = ?, rtel = ?, "+
					"admNo = ? where memNo = ?";
			
			System.out.println("2차테이블삽입"+sql);
			
			ptmt = con.prepareStatement(sql);
			
			ptmt.setInt(1, dto.rno );
			ptmt.setString(2, dto.rname);
			ptmt.setString(3, dto.rowner);
			ptmt.setString(4, dto.raddr);
			ptmt.setString(5, dto.rtel);
			ptmt.setInt(6, 90001);
			ptmt.setInt(7, dto.memNo);
			ptmt.setInt(8, dto.rno );
			
			ptmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	// 1. mypage/resRegister - 관리자 식당 등록
	// 2. mypage/resAccept - 관리자 요청 식당 수락
	
	public int insert(ResDetailDTO dto) {
		
		int i = 0;
		
		sql = "insert into resDetail "+
				"(rno, rprice1, rprice2, rloc1, rloc2, rmenu1, rmenu2, rhour, "+
							"rmain1, rmain2, rmain3, rchgdate, marked, pic ) values "+
				"(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate(), 0, ?)";
		
		try {
			
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.rno);
			ptmt.setString(2, dto.rprice1);
			ptmt.setString(3, dto.rprice2) ;
			ptmt.setString(4, dto.rloc1);
			ptmt.setString(5, dto.rloc2);
			ptmt.setString(6, dto.rmenu1);
			ptmt.setString(7, dto.rmenu2);
			ptmt.setString(8, dto.rhour);
			ptmt.setString(9, dto.rmain1);
			ptmt.setString(10, dto.rmain2);
			ptmt.setString(11, dto.rmain3);
			ptmt.setString(12, dto.pic);
			ptmt.executeUpdate();
			
			sql = "select max(rid) from resDetail";
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
			rs.next();
			
			i = rs.getInt(1);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		
		return i;
		
	}
	   
	public ArrayList<RecommDTO>  search(String search,String str) {
        //search,str
        ArrayList<RecommDTO> list = new ArrayList<RecommDTO>();
        
     
        
        sql="select t1.*, bookmark from " + 
        		" ((select tt.rid,resInf.rname,resInf.memNo  from resInf ,( select * from resDetail where  "+str+")) t1 " + 
        		" left join " + 
        		" user " + 
        		" on " + 
        		" t1.memno = user.memno";
        /*sql="select pic,rname,rid from resDetail \n" + 
        		"inner join resInf\n" + 
        		"on resDetail.rno=resInf.rno\n" + 
        		"where rloc1 = '서울' and rmenu1='중식' or rname like '%고기%' or rmenu2='고기' or rloc2='서초' or rmain1='돼지갈비'";*/
       
        sql="select pic,rname,rid,score from resDetail \n" + 
        		"        inner join resInf\n" + 
        		"        on resDetail.rno=resInf.rno \n" + 
        		"        where " +str+ 
        		"        order by score desc";
        
        
        System.out.println("서치 메소드"+sql);
        
        try {
           
           ptmt = con.prepareStatement(sql);
           rs = ptmt.executeQuery();
           
           while(rs.next()) {
              
             RecommDTO dto = new RecommDTO();
     
              dto.setRid(rs.getInt("rid")); 
              dto.setRname(rs.getString("rname"));
              dto.setScore(rs.getFloat("score"));
              if(rs.getString("pic")!=null) {
              dto.setPic(rs.getString("pic"));
              }else {
            	  dto.setPic("basic_Img.png");
              }
              
              DetailList(dto);
              
              
              list.add(dto);         
           }
           
        } catch (SQLException e) {
           e.printStackTrace();
        } finally {
           close();
        }
        
        
        return list;
     }
	   
	
	   public void DetailList(RestaurantDTO dto) {
		      
		      sql ="update resdetail  A set score = (select (sum(tsrate*0.5 + mdrate*0.2 + svrate*0.3)/count(*)) "
		            + "from review B where A.rid=B.rid)";
		      try {  
		         
		         System.out.println(sql);
		         
		         ptmt = con.prepareStatement(sql);
		         
		         ptmt.executeUpdate();
		         
		         
		      } catch (Exception e) {
		         e.printStackTrace();
		      }  
		   }
	   
	   public void DetailList(RecommDTO dto) {
		      
		      sql ="update resdetail  A set score = (select (sum(tsrate*0.5 + mdrate*0.2 + svrate*0.3)/count(*)) "
		            + "from review B where A.rid=B.rid )";
		      try {  
		         
		         System.out.println(sql);
		         
		         ptmt = con.prepareStatement(sql);
		       
		         ptmt.executeUpdate();
		         
		         
		      } catch (Exception e) {
		         e.printStackTrace();
		      }  
		   }

	public void close() {
		if(rs!=null) try{ rs.close();}catch(Exception e) {}
		if(ptmt!=null) try{ rs.close();}catch(Exception e) {}
		if(con!=null) try{ rs.close();}catch(Exception e) {}
	}
	
}
