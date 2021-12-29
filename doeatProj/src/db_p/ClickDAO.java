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

public class ClickDAO {

	DBSet db = new  DBSet();
	String url=db.url;
	String id=db.id;
	String pw=db.pw;
	String sql;
	
	Connection con;
	PreparedStatement ptmt;
	ResultSet rs;
	int cnt;
	
	public ClickDAO() { //CRUD에서 항상 실행

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con =  DriverManager.getConnection(url,id, pw );
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	
	public void insert2(ClickDTO dto) {
		
		
		
		sql ="insert into click (memNo, rid) values (?,?)";
		
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.getMemNo());
			ptmt.setInt(2, dto.getRid());
			
			ptmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	public void insert(ClickDTO dto) {
		
		
		Boolean res = false;
		sql ="select * from click where rid =? and memNo=?";
		
		try {
			
			ptmt = con.prepareStatement(sql);			
			ptmt.setInt(1, dto.getRid());
			ptmt.setInt(2, dto.getMemNo());
			rs = ptmt.executeQuery();
			
			if(rs.next()) {
				sql = "update click set clkNo = clkNo+1 where rid =? and memNo=? ";
				
				ptmt = con.prepareStatement(sql);			
				ptmt.setInt(1, dto.getRid());
				ptmt.setInt(2, dto.getMemNo());
				
				ptmt.executeUpdate();
			}else {
				sql = "insert into click (rid,memNo) values (?,?)";
				
				ptmt = con.prepareStatement(sql);			
				ptmt.setInt(1, dto.getRid());
				ptmt.setInt(2, dto.getMemNo());
				
				ptmt.executeUpdate();
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	
	public ArrayList<RecommDTO> cComm(UserDTO udto) {
		
		ArrayList<RecommDTO> list = new ArrayList<RecommDTO>();
		
		sql = "select * from resInf," + 
				"(select resDetail.rid,resDetail.rno,clkNo from resDetail ," + 
				"(select * from click where memNo=? )t0 " + 
				"where t0.rid=resDetail.rid  )t1" + 
				"where t1.rno = resInf.rno order by clkNo desc";
		
		try {
			
			ptmt = con.prepareStatement(sql);			
			ptmt.setInt(1, udto.getMemNo());
			rs = ptmt.executeQuery();
			while(rs.next()&&cnt<3) {
				
			RecommDTO dto = new RecommDTO();

			dto.setRid(rs.getInt("rid"));
			dto.setRno(rs.getInt("rno"));
			dto.setRname(rs.getString("rname"));
			
			
			
			list.add(dto);
			
			cnt++;
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
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
