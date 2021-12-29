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

public class HashKeyDAO {
	

	
	DBSet db = new  DBSet();
	String url=db.url;
	String id=db.id;
	String pw=db.pw;
	String sql;
	
	Connection con;
	PreparedStatement ptmt;
	ResultSet rs;
	
	
	public HashKeyDAO() { //CRUD에서 항상 실행

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con =  DriverManager.getConnection(url,id, pw );

		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public ArrayList<HashKeyDTO> recomm(String text) {
				
		ArrayList<HashKeyDTO> list = new ArrayList();
		
		sql = "select * from hashkey where hcontent like '%"+text+"%' and hashcode = 2 ";
		System.out.println(sql);

		try {
			
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
			HashKeyDTO dto = new HashKeyDTO();

			dto.setHashcode(rs.getInt("hashcode"));
			dto.setSrchnum(rs.getInt("srchnum"));			
			
			dto.setHcontent(rs.getString("hcontent"));
			
			list.add(dto);			
			
			}
			
			sql = "select * from hashkey where hcontent like '%"+text+"%' and hashcode = 1";
			System.out.println(sql);
			
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
			HashKeyDTO dto = new HashKeyDTO();

			dto.setHashcode(rs.getInt("hashcode"));
			dto.setSrchnum(rs.getInt("srchnum"));			
			
			dto.setHcontent(rs.getString("hcontent"));
			
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
