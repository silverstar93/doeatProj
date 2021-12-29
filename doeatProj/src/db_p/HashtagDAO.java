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

public class HashtagDAO {

	DBSet db = new  DBSet();
	String url=db.url;
	String id=db.id;
	String pw=db.pw;
	String sql;
	
	Connection con;
	PreparedStatement ptmt;
	ResultSet rs;
	int cnt;
	
	public HashtagDAO() { //CRUD에서 항상 실행

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con =  DriverManager.getConnection(url,id, pw );

		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public void insert(HashtagDTO dto) {
		
		
		
		sql ="insert into hashtag (memNo, hashcode,hcon) values (?,?,?)";
		
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, dto.getMemNo());
			ptmt.setInt(2, dto.getHashcode());
			ptmt.setString(3, dto.getHcon());
			
			ptmt.executeUpdate();
			System.out.println(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
public ArrayList<RecommDTO> hComm(UserDTO udto) {
		
		ArrayList<RecommDTO> list = new ArrayList<RecommDTO>();
		
		sql ="select bookmark, tt2.rname, tt2.rid from " + 
				" (select  rid,rname,memNo from resInf inner join " + 
				" (select * from resDetail ,(select  count(*) as tt ,hcon  from hashtag where memNo= "+udto.getMemNo()+" group by hcon )tt " + 
				" where rloc2 = tt.hcon or rmenu2 = tt.hcon order by tt desc) dd where dd.rno = resInf.rno)tt2 " + 
				" left join user " + 
				" on tt2.memNo = user.memNo";
				
		try {
			System.out.println(sql);
			ptmt = con.prepareStatement(sql);
			
					
			rs = ptmt.executeQuery();
			
			
			while(rs.next()&&cnt<5) {
				
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
				System.out.println(cnt);
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
