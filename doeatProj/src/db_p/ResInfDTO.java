package db_p;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResInfDTO {

	Integer rno, memNo;
	String rname, rowner, raddr, rtel;
	Date rregdate, rdeldate;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public Integer getRno() {
		return rno;
	}
	public void setRno(Integer rno) {
		this.rno = rno;
	}
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getRowner() {
		return rowner;
	}
	public void setRowner(String rowner) {
		this.rowner = rowner;
	}
	public String getRaddr() {
		return raddr;
	}
	public void setRaddr(String raddr) {
		this.raddr = raddr;
	}
	public String getRtel() {
		return rtel;
	}
	public void setRtel(String rtel) {
		this.rtel = rtel;
	}
	public Date getRregdate() {
		return rregdate;
	}

	public void setRregdate(Date rregdate) {
		this.rregdate = rregdate;
	}
	
	
	public String getRregdateStr() {
		return sdf.format(rregdate);
	}

	public void setRregdateStr(String rregdateStr) {
		try {
			this.rregdate = sdf.parse(rregdateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Date getRdeldate() {
		return rdeldate;
	}

	public void setRdeldate(Date rdeldate) {
		this.rdeldate = rdeldate;
	}
	
	
	public String getRdeldateStr() {
		return sdf.format(rdeldate);
	}

	public void setRdeldateStr(String rdeldateStr) {
		try {
			this.rregdate = sdf.parse(rdeldateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
