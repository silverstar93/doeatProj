package db_p;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import filepath_p.DBSet;

public class DealDAO {

	DBSet set = new DBSet();
//	String url="jdbc:mysql://localhost:3306/doeat_db";
//	String id="doeat";
//	String pw="123456";
	
	Connection con;
	PreparedStatement ptmt;
	ResultSet rs;
	String sql;
	
	public DealDAO() {
		super();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(set.url,set.id,set.pw);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int total(int memNo) {
		int i = 0;
		
		try {
			
			if(memNo==0) {
				
				sql = "select count(*) from deal";
				
				ptmt = con.prepareStatement(sql);
				rs = ptmt.executeQuery();
				
				rs.next();
				i = rs.getInt(1);
				System.out.println(i);
				
			}else {
			
			sql = "select * from resInf inner join resDetail on resInf.rno "
					+ "= resDetail.rno where memNo = ?";
			
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, memNo);
			rs = ptmt.executeQuery();			
			
			rs.next();			
			i = rs.getInt("rid");
			System.out.println(i);
			
			sql = "select count(*) from deal where rid = ?";
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
		}finally {
			close();
		}
		
		return i;
	}

	
	
	public Object mydeal(int memNo, int start,int end) { // deal 신청내역
		ArrayList<DealDTO> res = new ArrayList<DealDTO>();
		
		int i = 0;
		
		try {
			/*sql = "select deal.cmenu,t0.rid,t0.pno,t0.refund from deal , (select cno, rid,pno,refund from payment where memNo = ? ) t0 "+
					" where t0.cno = deal.cno and  t0.rid = deal.rid ";
			
			System.out.println(sql);
			
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, memNo);
			rs = ptmt.executeQuery();			
			
			rs.next();			
			i = rs.getInt("rid");
			System.out.println(i);*/
			
			sql = "select * from deal inner join"
					+ "(select rid from  resInf inner join resDetail where resInf.rno = resDetail.rno and memNo = ?)tt " + 
					" on deal.rid = tt.rid limit ?,?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, memNo);
			ptmt.setInt(2, start);
			ptmt.setInt(3, end);
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				DealDTO dto = new DealDTO();
				
				dto.setCno(rs.getInt("cno"));
				dto.setRid(rs.getInt("rid"));
				dto.setCprice(rs.getInt("cprice"));
				dto.setCdiscount(rs.getInt("cdiscount"));
				dto.setCstart(rs.getDate("cstart")); // 판매시작
				dto.setCend(rs.getDate("cend"));
				dto.setCpub(rs.getDate("cpub")); // 신청일
				dto.setPubstart(rs.getDate("pubstart")); // 개시예정일(신청)
				dto.setPubend(rs.getDate("pubend")); // 개시만료일(신청)
				dto.setCmenu(rs.getString("cmenu"));
				dto.setCcon(rs.getInt("ccon"));
				dto.setCvolume(rs.getInt("cvolume"));
				dto.setContent(rs.getString("content"));
				dto.setRimg(rs.getString("rimg"));
				dto.setSale(rs.getInt("paycnt"));
				
				res.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		
		return res;
	}
	
	
	public Object list(int start,int limit) {
		ArrayList<DealDTO> res = new ArrayList<DealDTO>();
		
		try {
			sql = "select * from deal limit ?,?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, start);
			ptmt.setInt(2, limit);
			rs = ptmt.executeQuery();
			System.out.println("dd"+start+"skdkd"+limit+ sql);
			while(rs.next()) {
				DealDTO dto = new DealDTO();
				dto.setCno(rs.getInt("cno"));
				dto.setRid(rs.getInt("rid"));
				dto.setCprice(rs.getInt("cprice"));
				dto.setOrgprice(rs.getInt("orgprice"));
				dto.setCdiscount(rs.getInt("cdiscount"));
				dto.setCstart(rs.getTimestamp("cstart"));
				dto.setCend(rs.getTimestamp("cend"));
				dto.setCpub(rs.getTimestamp("cpub"));
				dto.setPubend(rs.getTimestamp("pubend"));
				dto.setCmenu(rs.getString("cmenu"));
				dto.setCcon(rs.getInt("ccon"));
				dto.setCvolume(rs.getInt("cvolume"));
				dto.setContent(rs.getString("content"));
				dto.setRimg(rs.getString("rimg"));
				dto.setSale(-rs.getInt("paycnt"));
				
				
				res.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		
		return res;
	}
	
	public Object dealSearch(String str) {
		ArrayList<DealDTO> res = new ArrayList<DealDTO>();
		
		try {
			sql = "select * from deal inner join resDetail on resDetail.rid = deal.rid and deal.ccon =1 " + 
					" where cmenu like '%"+str+"%' or rloc1 like '%"+str+"%' or rloc2 like '%"+str+"%' "
							+ " and (TO_DAYS(pubstart)- to_days(sysdate()))<=0 ";
			
			ptmt = con.prepareStatement(sql);
		      
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				DealDTO dto = new DealDTO();
				dto.setCno(rs.getInt("cno"));
				dto.setRid(rs.getInt("rid"));
				dto.setCprice(rs.getInt("cprice"));
				dto.setOrgprice(rs.getInt("orgprice"));
				dto.setCdiscount(rs.getInt("cdiscount"));
				dto.setCstart(rs.getTimestamp("cstart"));
				dto.setCend(rs.getTimestamp("cend"));
				dto.setCpub(rs.getTimestamp("cpub"));
				dto.setPubend(rs.getTimestamp("pubend"));
				dto.setCmenu(rs.getString("cmenu"));
				dto.setCcon(rs.getInt("ccon"));
				dto.setCvolume(rs.getInt("cvolume"));
				dto.setContent(rs.getString("content"));
				dto.setRimg(rs.getString("rimg"));
				dto.setSale(rs.getInt("paycnt"));
				
				
				res.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		
		return res;
	}
	
	public Object mymenu(int rid, int ccode){
		DealDTO dto = null; //잘담아야지
		sql = "select rid, cmenu, ccode from deal where rid = ? and ccode = ? ";
		
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, rid);
			ptmt.setInt(2, ccode);
			rs = ptmt.executeQuery();
			System.out.println(ptmt);
			while(rs.next()) {
				dto = new DealDTO();
				dto.setCmenu(rs.getString("cmenu"));
				dto.setRid(rs.getInt("rid"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return dto;
		
	}
	public DealDTO menu(int cno) {
		DealDTO dto =null;
		sql="select * from deal where cno = ?";
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, cno);
			rs = ptmt.executeQuery();
			
			if(rs.next()) {
				dto = new DealDTO();
				dto.setRid(rs.getInt("rid"));
				dto.setCprice(rs.getInt("cprice"));
				dto.setCstart(rs.getTimestamp("cstart"));
				dto.setCend(rs.getTimestamp("cend"));
				dto.setCmenu(rs.getString("cmenu"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}
public void write(DealDTO dto, int mem) {
		
		int i = 0;
		
		try {
			sql = "select * from resInf inner join resDetail on resInf.rno = resDetail.rno where memNo = ?";
			
			System.out.println(sql);
			
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, mem);
			rs = ptmt.executeQuery();			
			
			rs.next();					
			i = rs.getInt("rid");

			sql = "insert into deal "
			+" (ccon, cpub, cvolume, rid, cprice, cdiscount, cstart, cend, cmenu, content, rimg, pubend, pubstart, orgprice, paycnt) values "+
			" (2, date_format(sysdate(),'%y-%m-%d'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0)";
			
			
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.cvolume);
			ptmt.setInt(2, i);
			ptmt.setInt(3, dto.cprice);
			ptmt.setInt(4, dto.cdiscount);
			ptmt.setString(5, dto.getCstartStr());
			ptmt.setString(6, dto.getCendStr());
			ptmt.setString(7, dto.cmenu );
			ptmt.setString(8, dto.content);
			ptmt.setString(9, dto.rimg);
			ptmt.setString(10, dto.getPubendStr());
			ptmt.setString(11, dto.getPubstartStr());
			ptmt.setInt(12, dto.orgprice);
			
			
			ptmt.executeUpdate();
			
			System.out.println("d애아아매ㅓ래ㅔㅓ");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("에러를 찍어라!");
			e.printStackTrace();
		}finally {
			close();
		}
		
	}
	
	   public ArrayList<DealDTO> plan(int memNo) {// myCoupon에서 쿠폰 메뉴보여줄때
		      ArrayList<DealDTO> res = new ArrayList<DealDTO>();
		      try {
		         sql = "select A.*, ccode from deal A, (select cno, ccode from payment inner join paycoup on paycoup.pno = payment.pno where payment.memNo ="+memNo+") B where A.cno=B.cno";

		         ptmt = con.prepareStatement(sql);
		         rs = ptmt.executeQuery();

		         while(rs.next()) {
		            DealDTO dto = new DealDTO();
		            dto.setCmenu(rs.getString("cmenu"));
		            dto.setCmenu(rs.getString("ccode"));
		            
		            
		            res.add(dto);
		         }
		      } catch (SQLException e) {
		         // TODO Auto-generated catch block
		         e.printStackTrace();
		      } finally {
		         close();
		      }

		      return res;
		   }
	   
	public Object planb(int memNo) {//myCoupon에서 쿠폰 메뉴보여줄때
		DealDTO dto = null;
		try {
			
			sql = "select A.* from deal A,"
				+ " (select rid from paycoup where memNo= "+memNo+") B where A.rid=B.rid";
			//select A.* from deal A, (select ccode from paycoup where memNo="+memNo+") B where A.ccode=B.ccode";
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("menumenu");
				dto = new DealDTO();
				dto.setCmenu(rs.getString("cmenu"));
				System.out.println("cmenu : "+dto.getCmenu());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}     
		
		return dto;    
	}
	
	   public ArrayList<DealDTO> codelist() {
		   
		      ArrayList<DealDTO> res =  new ArrayList<DealDTO>();
		      DealDTO dto= null;
		      try {
		    	  
		    	 sql = " update deal set ccon = 3 where (TO_DAYS(pubend)- to_days(sysdate()) ) < 0" ;
			    	 
		    	 ptmt = con.prepareStatement(sql);
	          	 ptmt.executeUpdate(); 
		    	 
	          	 
	          	 
		         sql = " select * from deal, (select rid, tt.rname, tt.raddr from resDetail,"
		               + " (select rname, rno, raddr from resInf)tt where tt.rno = resDetail.rno)bb "
		               + " where ccon = 1 and bb.rid = deal.rid" ;
		         
		         sql = " select * from deal, (select rid, tt.rname, tt.raddr from resDetail," + 
			         		"	(select rname, rno, raddr from resInf)tt where tt.rno = resDetail.rno)bb " + 
			         		"  where ccon = 1 and bb.rid = deal.rid and (TO_DAYS(pubstart)- to_days(sysdate()))<=0" ;
		         
		         ptmt = con.prepareStatement(sql);
		         rs = ptmt.executeQuery();
		         
		         while(rs.next()) {
		        	 
		        	 System.out.println(sql);
		            dto = new DealDTO();
		            
		            dto.setCno(rs.getInt("cno"));
		            dto.setRname(rs.getString("rname"));
		            dto.setRaddr(rs.getString("raddr"));
		            
		            dto.setRid(rs.getInt("rid"));
		            dto.setCprice(rs.getInt("cprice"));
		            dto.setOrgprice(rs.getInt("orgprice"));
		            dto.setCdiscount(rs.getInt("cdiscount"));
		            dto.setCstart(rs.getTimestamp("cstart"));
		            dto.setCend(rs.getTimestamp("cend"));
		            dto.setCpub(rs.getTimestamp("cpub"));
		            dto.setPubend(rs.getTimestamp("pubend"));
		            dto.setCmenu(rs.getString("cmenu"));
		            dto.setCcon(rs.getInt("ccon"));
		            dto.setCvolume(rs.getInt("cvolume"));
		            dto.setContent(rs.getString("content"));
		            dto.setRimg(rs.getString("rimg"));  
		           
		            System.out.println(dto.getRname()+"리스트");
		            
		            res.add(dto);
		            
		         }
		         
		      } catch (SQLException e) {
		         // TODO Auto-generated catch block
		         e.printStackTrace();
		      }
		      
		      return res;
		   }
	
	public void purchase(int volume, int rid) {//eatdeal 구매
		
		//DealDTO res = null;
		try {
			sql = "update deal set cvolume = cvolume ? where rid= ?";
			
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, -volume);
			ptmt.setInt(2, rid);

			ptmt.executeUpdate();
			System.out.println(ptmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void write(DealDTO dto) {
		
		try {
			sql = "insert into deal "
				+"(rid, cprice, ccon, cdiscount, cvolume, cstart, cend, cpub, pubend, cmenu, content, rimg) values "
				+"( ? ,   ?   ,  2  ,    ?    ,     ?   ,    ?  ,  ? ,sysdate(),sysdate()+14, ?,   ?, ?)";
			
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.getRid());
			ptmt.setInt(2, dto.getCprice());
			ptmt.setInt(3, dto.getCdiscount());
			ptmt.setInt(4, dto.getCvolume());
			ptmt.setString(5, dto.getCstartStr());
			ptmt.setString(6, dto.getCendStr());
			ptmt.setString(7, dto.getCmenu());
			ptmt.setString(8, dto.getContent());
			ptmt.setString(9, dto.getRimg());
			ptmt.executeUpdate();
		
			System.out.println(ptmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		
	}
	
	public DealDTO detail(DealDTO dto) {
		DealDTO res = null;
		
		
		try {
			
			sql = "select * from deal where cno = ?";
		
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.cno);
			rs = ptmt.executeQuery();
			
			if(rs.next()) {
				res = new DealDTO();
				res.setCno(rs.getInt("cno"));
				res.setRid(rs.getInt("rid"));
				res.setCprice(rs.getInt("cprice"));
				res.setCdiscount(rs.getInt("cdiscount"));
				res.setCcon(rs.getInt("ccon"));
				res.setCvolume(rs.getInt("cvolume"));
				res.setCstart(rs.getTimestamp("cstart"));
				res.setCend(rs.getTimestamp("cend"));
				res.setCpub(rs.getTimestamp("cpub"));
				res.setCmenu(rs.getString("cmenu"));
				res.setRimg(rs.getString("rimg"));
				res.setContent(rs.getString("content"));
				res.setPubend(rs.getTimestamp("pubend"));
				res.setPaycnt(rs.getInt("paycnt"));
				res.setOrgprice(rs.getInt("orgprice"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	
	public DealDTO dealdetail(DealDTO dto, int i) {
		DealDTO res = null;
		
		
		try {
			sql = "select * from deal where rid = ?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.rid);
			rs = ptmt.executeQuery();
			
			if(rs.next()) {
				res = new DealDTO();
				res.setCno(rs.getInt("cno"));
				res.setRid(rs.getInt("rid"));
				res.setCprice(rs.getInt("cprice"));
				res.setCdiscount(rs.getInt("cdiscount"));
				res.setCcon(rs.getInt("ccon"));
				res.setCvolume(rs.getInt("cvolume"));
				res.setCstart(rs.getTimestamp("cstart"));
				res.setCend(rs.getTimestamp("cend"));
				res.setCpub(rs.getTimestamp("cpub"));
				res.setCmenu(rs.getString("cmenu"));
				res.setRimg(rs.getString("rimg"));
				res.setContent(rs.getString("content"));
				res.setPubend(rs.getTimestamp("pubend"));
				res.setPaycnt(rs.getInt("paycnt"));
				res.setOrgprice(rs.getInt("orgprice"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public boolean refund(int pno) {
		boolean res = false;
	
		try {
			
			sql = "update deal set paycnt = paycnt+1 where cno = (select cno from payment where pno=? )";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1,pno);
			res = ptmt.executeUpdate() > 0;
			
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public boolean approval(int ccon, int cno) {
		boolean res = false;
		
		try {
			sql = "update deal set ccon = ? where cno = ?";
			
			ptmt = con.prepareStatement(sql);
			
			ptmt.setInt(1, ccon);
			ptmt.setInt(2, cno);
			
			res = ptmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		
		return res;
	}
	
	
	public void freeze(int rid){

		sql = "update deal set ccon = 3 where rid ="+rid;

			try {
				ptmt = con.prepareStatement(sql);			
				ptmt.executeUpdate();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close();
			}
	}
	
public String chkDeal(int rid) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String status = "진행 중인 잇딜이 없습니다";
		
		sql = "select * from deal where rid = "+rid;	
		
		try {
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
								
				if(new Date().after(rs.getDate("pubend"))==false && rs.getInt("ccon")!=3) {								
					
					status = "잇딜이 아직 개시중입니다";// \n("+sdf.format(rs.getDate("pubstart"))+"-"+sdf.format(rs.getDate("pubend"))+")";
										
				}else if(new Date().after(rs.getDate("pubstart"))==false && rs.getInt("ccon")!=3) {
					
					status = "개시 예정인 잇딜이 있습니다"; // \n("
								//+sdf.format(rs.getDate("pubstart"))+"-"+sdf.format(rs.getDate("pubend"))+")";					
				}else if(rs.getInt("ccon")==3) {
					status = "진행 중인 잇딜이 없습니다";
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		System.out.println(status);
		
		return status;		
	}
	
	
	
	public String chkCoup(int rid) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String status = "사용 기간이 있는 이용권이 없습니다";
		
		sql = "select cstart, cend from deal where rid = "+rid;		
		
		try {
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				
				if(new Date().after(rs.getDate("cend"))==false) {								
					
					status = "이용권의 사용기간이 남아있습니다";// \n("+sdf.format(rs.getDate("cstart"))+"-"+sdf.format(rs.getDate("cend"))+")";
					
				}else if((new Date().after(rs.getDate("cend"))==false) && (new Date().after(rs.getDate("cstart"))==false)) {
					
					status = "사용 기간이 시작되지 않은 이용권이 있습니다";// \n("
								//+sdf.format(rs.getDate("cstart"))+"-"+sdf.format(rs.getDate("cend"))+")";
					
				}				
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	
		return status;		
	}
	
	public void outChk(int rid, int memNo) {
		
		sql = "select * from deal where rid = "+rid;
		System.out.println(sql);
		try {
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
		
			while(rs.next()) {
				int i = 0;
				if(new Date().after(rs.getDate("cstart"))==false) {
					
					sql = "update paycoup set cond = 5 where cond = 2 and rid = "+rid;
					ptmt = con.prepareStatement(sql);
					ptmt.executeUpdate();					

					sql = "update payment set refund = 5 where cno = ?";
					ptmt = con.prepareStatement(sql);
					ptmt.setInt(1, rs.getInt("cno"));					
					ptmt.executeUpdate();

					sql = "update resInf set memNo = 0 where memNo = "+memNo;
					ptmt = con.prepareStatement(sql);
					ptmt.executeUpdate();
					
					sql = "delete from user where memNo = "+memNo;
					ptmt = con.prepareStatement(sql);
					ptmt.executeUpdate();										
					
					
				}else if((new Date().after(rs.getDate("cstart"))==true) && 
						(new Date().after(rs.getDate("cend"))==false)) {
					
					System.out.println("이용권있음");
					
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
					
					sql = "update resInf set memNo = 0 where memNo = "+memNo;
					ptmt = con.prepareStatement(sql);
					ptmt.executeUpdate();
					
					sql = "delete from user where memNo = "+memNo;
					ptmt = con.prepareStatement(sql);
					ptmt.executeUpdate();	
					
				}else {					
					
					sql = "update resInf set memNo = 0 where memNo = "+memNo;
					ptmt = con.prepareStatement(sql);
					ptmt.executeUpdate();
					
					sql = "delete from user where memNo = "+memNo;
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

	
	public void close() {
		if(rs!=null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
		if(ptmt!=null) {try {ptmt.close();} catch (SQLException e) {e.printStackTrace();}}
		if(con!=null) {try {con.close();} catch (SQLException e) {e.printStackTrace();}}
	}
}

