package db_p;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import filepath_p.DBSet;

public class RecommDAO {
	
	DBSet set = new DBSet();
	
//	String url="jdbc:mysql://192.168.43.153:3306/doeat_db";
//	String id="doeat";
//	String pw="123456";
	String sql;
	
	Connection con;
	PreparedStatement ptmt;
	ResultSet rs;
	int cnt;
	
	public RecommDAO() { //CRUD에서 항상 실행

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con =  DriverManager.getConnection(set.url, set.id, set.pw);

		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	//리뷰 추천
	public ArrayList<RecommDTO> rComm(String memNo) {
		
		ArrayList<RecommDTO> list = new ArrayList();
		
		sql ="select * from resDetail ,( select  count(*) as tt , rid  from review where memNo="+memNo
				+" group by rid)tt where resDetail.rid = tt.rid order by tt desc";
		
		try {
			
			ptmt = con.prepareStatement(sql);
			
						
			rs = ptmt.executeQuery();
			System.out.println(sql);
			
			while(rs.next()) {
				
				RecommDTO dto = new RecommDTO();

				dto.setRid(rs.getInt("rid"));
	            dto.setRno(rs.getInt("rno"));         
	            dto.setRname(rs.getString("rname"));
	            
	            dto.setRprice1(rs.getString("rprice1"));
	            dto.setRprice2(rs.getString("rprice2"));
	            
	            dto.setRloc1(rs.getString("rloc1"));
	            dto.setRloc2(rs.getString("rloc2"));
	            
	            dto.setRmenu1(rs.getString("rmenu1"));
	            dto.setRmenu2(rs.getString("rmenu2"));
	            
				
				list.add(dto);					
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}	
		
		return list;
	}
	
	//qbookmark
	public ArrayList<RecommDTO> bComm(UserDTO udto) {
		
		ArrayList<RecommDTO> list = new ArrayList();
		
		sql =" select * from resinf," + 
				"(select * from resdetail," + 
				"(select " + 
				"  user.memNo, " + 
				"  SUBSTRING_INDEX(SUBSTRING_INDEX(user.bookmark, ',', numbers.n), ',', -1) rr " + 
				"from " + 
				"  (select 1 n union all " + 
				"   select 2 union all select 3 union all " + 
				"   select 4 union all select 5) numbers INNER JOIN user " + 
				"  on CHAR_LENGTH(user.bookmark) " + 
				"     -CHAR_LENGTH(REPLACE(user.bookmark, ',', '')) >= numbers.n-1 " + 
				" where user.memNo = ?" + 
				" order by " + 
				"  id, n ) t0 " + 
				"  where t0.rr = resdetail.rid " + 
				"  )t1 " + 
				"  where t1.rno = resinf.rno ";
		
		sql="select ff.rid,rname,user.bookmark from resinf inner join user on user.memNo = resinf.memNo, " + 
				"("+
				"select * from resdetail ,(select  count(*) as tt , " + 
				" rloc1 as reccom from ( select rmenu1,rmenu2,rloc1 from resdetail,"
				+ " (select   user.memNo,   SUBSTRING_INDEX(SUBSTRING_INDEX(user.bookmark, ',', numbers.n), ',', -1) rr " + 
				" from   (select 1 n union all    select 2 union all select 3 union all    select 4 union all select 5) numbers " + 
				" INNER JOIN user   on CHAR_LENGTH(user.bookmark)      -CHAR_LENGTH(REPLACE(user.bookmark, ',', '')) >= numbers.n-1  "
				+ " where user.memNo = ? order by   id, n ) t0 " + 
				" where t0.rr = resdetail.rid ) res group by rloc1) tt where tt.reccom = resdetail.rloc1 group by rid" + 
				" union " + 
				" select * from resdetail ,( select  count(*) as tt ," + 
				" rmenu1 as reccom from ( select rmenu1,rmenu2,rloc1 from resdetail,"
				+ " (select   user.memNo,   SUBSTRING_INDEX(SUBSTRING_INDEX(user.bookmark, ',', numbers.n), ',', -1) rr " + 
				" from   (select 1 n union all    select 2 union all select 3 union all    select 4 union all select 5) numbers " + 
				" INNER JOIN user   on CHAR_LENGTH(user.bookmark)      -CHAR_LENGTH(REPLACE(user.bookmark, ',', '')) >= numbers.n-1  "
				+ " where user.memNo = ? order by   id, n ) t0 " + 
				" where t0.rr = resdetail.rid ) res group by rmenu1)tt where tt.reccom = resdetail.rmenu1 " + 
				" group by rid and reccom " + 
				" order by tt desc ) ff where ff.rno = resinf.rno group by rid";
		
		sql=" select ff.rid,rname,user.bookmark,ff.rmenu1,ff.rloc1 from resinf inner join user on user.memNo = resinf.memNo, " + 
				"(select * from resdetail , " + 
				"(select  * from (select  count(*) as tt , " + 
				"rloc1 as reccom from " + 
				"( select rmenu1,rmenu2,rloc1 from resdetail, " + 
				"(select   user.memNo,   SUBSTRING_INDEX(SUBSTRING_INDEX(user.bookmark, ',', numbers.n), ',', -1) rr " + 
				" from   (select 1 n union all    select 2 union all select 3 union all    select 4 union all select 5) numbers " + 
				" INNER JOIN user   on CHAR_LENGTH(user.bookmark)      -CHAR_LENGTH(REPLACE(user.bookmark, ',', '')) >= numbers.n-1  where user.memNo = ? order by   id, n ) t0 " + 
				" where t0.rr = resdetail.rid ) res group by rloc1) tt ) tt2 where tt2.reccom = resdetail.rloc1 " + 
				"union " + 
				"select * from resdetail , " + 
				"(select * from ( select  count(*) as tt , " + 
				"rmenu1 as reccom from ( select rmenu1,rmenu2,rloc1 from resdetail,(select   user.memNo,   SUBSTRING_INDEX(SUBSTRING_INDEX(user.bookmark, ',', numbers.n), ',', -1) rr  " + 
				" from   (select 1 n union all    select 2 union all select 3 union all    select 4 union all select 5) numbers " + 
				" INNER JOIN user   on CHAR_LENGTH(user.bookmark)      -CHAR_LENGTH(REPLACE(user.bookmark, ',', '')) >= numbers.n-1  where user.memNo = ? order by   id, n ) t0  " + 
				" where t0.rr = resdetail.rid ) res group by rmenu1) tt ) tt3 where tt3.reccom = resdetail.rmenu1 " + 
				" and reccom " + 
				"order by tt desc) ff where ff.rno = resinf.rno";
		
		try {
			
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, udto.memNo);
			ptmt.setInt(2, udto.memNo);		
			
			rs = ptmt.executeQuery();
			System.out.println(sql);
			
			while(rs.next()&&cnt<3) {
				
				RecommDTO dto = new RecommDTO();

				dto.setRid(rs.getInt("rid"));				
				dto.setRname(rs.getString("rname"));
				
				if(rs.getString("bookmark")!=null) {
	              dto.setBookmark(rs.getString("bookmark"));
	              }else {
	            	  dto.setBookmark("basic_Img.png");
	              }
				
				list.add(dto);		
				cnt++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}	
		
		return list;
	}
	
	public ArrayList<RecommDTO> realRComm(UserDTO udto) {
		
		ArrayList<RecommDTO> list = new ArrayList();
		ResDetailDAO rdo = new ResDetailDAO();
		
		sql ="select distinct * from \r\n" + 
				"((select ff.rid,rname,user.bookmark,tt from resinf inner join user on user.memNo = resinf.memNo,\r\n" + 
				"(select * from resdetail ,\r\n" + 
				"(select  * from (select  count(*)*0.2 as tt ,\r\n" + 
				"rloc1 as reccom from \r\n" + 
				"( select rmenu1,rmenu2,rloc1 from resdetail,\r\n" + 
				"(select   user.memNo,   SUBSTRING_INDEX(SUBSTRING_INDEX(user.bookmark, ',', numbers.n), ',', -1) rr \r\n" + 
				" from   (select 1 n union all    select 2 union all select 3 union all    select 4 union all select 5) numbers \r\n" + 
				" INNER JOIN user   on CHAR_LENGTH(user.bookmark)      -CHAR_LENGTH(REPLACE(user.bookmark, ',', '')) >= numbers.n-1  where user.memNo = ? order by   id, n ) t0 \r\n" + 
				" where t0.rr = resdetail.rid ) res group by rloc1) tt ) tt2 where tt2.reccom = resdetail.rloc1 \r\n" + 
				"union \r\n" + 
				"select * from resdetail ,\r\n" + 
				"(select * from ( select  count(*)*0.2 as tt ,\r\n" + 
				"rmenu1 as reccom from ( select rmenu1,rmenu2,rloc1 from resdetail,(select   user.memNo,   SUBSTRING_INDEX(SUBSTRING_INDEX(user.bookmark, ',', numbers.n), ',', -1) rr \r\n" + 
				" from   (select 1 n union all    select 2 union all select 3 union all    select 4 union all select 5) numbers \r\n" + 
				" INNER JOIN user   on CHAR_LENGTH(user.bookmark)      -CHAR_LENGTH(REPLACE(user.bookmark, ',', '')) >= numbers.n-1  where user.memNo = ? order by   id, n ) t0 \r\n" + 
				" where t0.rr = resdetail.rid ) res group by rmenu1) tt ) tt3 where tt3.reccom = resdetail.rmenu1\r\n" + 
				" and reccom\r\n" + 
				"order by tt desc) ff where ff.rno = resinf.rno)\r\n" + 
				"union\r\n" + 
				"select   tt2.rid,tt2.rname,bookmark,tt from\r\n" + 
				" (select  rid,rname,memNo,tt from resInf inner join \r\n" + 
				"(select * from resDetail ,(select  count(*)*0.3 as tt ,hcon  from hashtag where memNo= "+udto.getMemNo()+" group by hcon )tt \r\n" + 
				"where rloc2 = tt.hcon or rmenu2 = tt.hcon order by tt desc) dd where dd.rno = resInf.rno)tt2 \r\n" + 
				"left join user \r\n" + 
				"on tt2.memNo = user.memNo)res2\r\n" + 
				"order by tt desc";
		
		
		sql="select rid,rname,tt,pic from resInf inner join (select rid,rno,rloc1,rmenu1,tt,pic from resDetail,(select  * from (select  count(*)*0.2 as tt ," + 
				"  rloc1 as reccom from " + 
				"  ( select rmenu1,rmenu2,rloc1 from resdetail," + 
				" (select   user.memNo,   SUBSTRING_INDEX(SUBSTRING_INDEX(user.bookmark, ',', numbers.n), ',', -1) rr " + 
				" from   (select 1 n union all    select 2 union all select 3 union all    select 4 union all select 5) numbers " + 
				" INNER JOIN user   on CHAR_LENGTH(user.bookmark)      -CHAR_LENGTH(REPLACE(user.bookmark, ',', '')) >= numbers.n-1  where user.memNo = ? "
				+ " order by   id, n ) t0 " + 
				" where t0.rr = resdetail.rid ) res group by rloc1)rr)rloc " + 
				" where rloc.reccom = resDetail.rloc1  " + 
				" and rid in(\r\n" + 
				" select rid from resDetail,( select  * from (select  count(*)*0.2 as tt , " + 
				" rmenu1 as reccom from " + 
				" ( select rmenu1,rmenu2,rloc1 from resdetail, " + 
				" (select   user.memNo,   SUBSTRING_INDEX(SUBSTRING_INDEX(user.bookmark, ',', numbers.n), ',', -1) rr " + 
				" from   (select 1 n union all    select 2 union all select 3 union all    select 4 union all select 5) numbers " + 
				" INNER JOIN user   on CHAR_LENGTH(user.bookmark)      -CHAR_LENGTH(REPLACE(user.bookmark, ',', '')) >= numbers.n-1  where user.memNo = ? order by   id, n ) t0 " + 
				" where t0.rr = resdetail.rid ) res group by rmenu1) rr) rmenu " + 
				" where rmenu.reccom = resDetail.rmenu1 " + 
				" )) bmrkT " + 
				" on bmrkT.rno=resInf.rno " + 
				" union " + 
				" select   tt2.rid,tt2.rname,tt,pic from " + 
				" (select  rid,rname,memNo,tt,pic from resInf inner join " + 
				" (select * from resDetail ,(select  count(*)*0.3 as tt ,hcon  from hashtag where memNo= "+udto.getMemNo()+" group by hcon )tt " + 
				" where rloc2 = tt.hcon or rmenu2 = tt.hcon order by tt desc) dd where dd.rno = resInf.rno)tt2  " + 
				" left join user \r\n" + 
				"on tt2.memNo = user.memNo\r\n" + 
				"order by tt desc";
		try {
			
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, udto.memNo);
			ptmt.setInt(2, udto.memNo);		
			
			rs = ptmt.executeQuery();
			System.out.println(sql);
			
			while(rs.next()&&cnt<4) {
				
				RecommDTO dto = new RecommDTO();

				dto.setRid(rs.getInt("rid"));				
				dto.setRname(rs.getString("rname"));
				
				if(rs.getString("pic")!=null) {
	              dto.setPic(rs.getString("pic"));
	              }else {
	            	  dto.setPic("basic_Img.png");
	              }
				
				rdo.DetailList(dto);
				list.add(dto);		
				cnt++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}	
		
		return list;
	}
	
	public ArrayList<RecommDTO>  search(String loc,String menu,String str) {
        //search,str
        ArrayList<RecommDTO> list = new ArrayList<RecommDTO>();      
      sql="select rid,rname,pic from resDetail inner join resInf \r\n" + 
      		" on resInf.rno=resDetail.rno \r\n" + 
      		" where (rmenu2= ? or rmain1=? or rmain2=? or rname like ?) and rloc1=? and rmenu1 =?";
        // select * from resDetail inner join resInf on resDetail.rno=resInf.rno  where rname like '%그린%';
      sql="select rid,rname,pic,score from resDetail inner join resInf \r\n" + 
      		"on resInf.rno=resDetail.rno \r\n" + 
      		"where  rid in (select rid from resDetail where rloc1='"+loc+"' and rmenu1 ='"+menu+"'  ) and rmenu2= '"+str+"' " + 
      		"union\r\n" + 
      		"select rid,rname,pic,score from resDetail inner join resInf \r\n" + 
      		"on resInf.rno=resDetail.rno \r\n" + 
      		"where  rid in (select rid from resDetail where rloc1='"+loc+"' and rmenu1 ='"+menu+"'  ) and rmain1= '"+str+"' " + 
      		"union\r\n" + 
      		"select rid,rname,pic,score from resDetail inner join resInf \r\n" + 
      		"on resInf.rno=resDetail.rno \r\n" + 
      		"where  rid in (select rid from resDetail where rloc1 ='"+loc+"' and rmenu1 ='"+menu+"'  ) and rmain2='"+str+"' " + 
      		"union\r\n" + 
      		"select rid,rname,pic,score from resDetail inner join resInf \r\n" + 
      		"on resInf.rno=resDetail.rno \r\n" + 
      		"where  rid in (select rid from resDetail where rloc1='"+loc+"' and rmenu1 ='"+menu+"'  ) and rname like '%"+str+"%'"
      		+"  order by score desc";
        System.out.println("메뉴"+loc+menu+str+sql);
        
        try {
           
           ptmt = con.prepareStatement(sql);

           rs = ptmt.executeQuery();
           
           while(rs.next()) {
              
             RecommDTO dto = new RecommDTO();
             dto.setRid(rs.getInt("rid"));        
             dto.setRname(rs.getString("rname"));
             dto.setScore(rs.getFloat("score"));
             if(rs.getString("pic")!="NA" ||!rs.getString("pic").equals("NA")||rs.getString("pic")!=null) {
             dto.setPic(rs.getString("pic"));
             }else {
           	  dto.setPic("basic_Img.png");
             }
             if(rs.getString("pic")!=null) {
             dto.setPic(rs.getString("pic"));
             }else {
           	  dto.setPic("basic_Img.png");
             }
             System.out.println(dto.getPic());        
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
