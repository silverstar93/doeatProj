package db_p;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RestaurantDTO {

	Integer rno, memNo, rid, grade, marked;
	String rname, rowner, raddr, rtel, rhour, rprice1, rprice2, rloc1, rloc2, rmenu1, rmenu2, rinfos, rmain1, rmain2, rmain3,pic;
	Date rregdate, rchgdate, rdeldate;
	float score;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}
	
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
	public String getRhour() {
		return rhour;
	}
	public void setRhour(String rhour) {
		this.rhour = rhour;
	}
	public String getRprice1() {
		return rprice1;
	}

	public void setRprice1(String rprice1) {
		this.rprice1 = rprice1;
	}

	public String getRprice2() {
		return rprice2;
	}

	public void setRprice2(String rprice2) {
		this.rprice2 = rprice2;
	}

	public String getRloc1() {
		return rloc1;
	}

	public void setRloc1(String rloc1) {
		this.rloc1 = rloc1;
	}

	public String getRloc2() {
		return rloc2;
	}

	public void setRloc2(String rloc2) {
		this.rloc2 = rloc2;
	}

	public String getRmenu1() {
		return rmenu1;
	}

	public void setRmenu1(String rmenu1) {
		this.rmenu1 = rmenu1;
	}

	public String getRmenu2() {
		return rmenu2;
	}

	public void setRmenu2(String rmenu2) {
		this.rmenu2 = rmenu2;
	}

	public String getRinfos() {
		return rinfos;
	}

	public void setRinfos(String rinfos) {
		this.rinfos = rinfos;
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

	public Date getRchgdate() {
		return rchgdate;
	}

	public void setRchgdate(Date rchgdate) {
		this.rchgdate = rchgdate;
	}
	

	public String getRchgdateStr() {
		return sdf.format(rchgdate);
	}

	public void setRchgdateStr(String rchgdateStr) {
		try {
			this.rchgdate = sdf.parse(rchgdateStr);
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
	public String getRmain1() {
		return rmain1;
	}

	public void setRmain1(String rmain1) {
		this.rmain1 = rmain1;
	}

	public String getRmain2() {
		return rmain2;
	}

	public void setRmain2(String rmain2) {
		this.rmain2 = rmain2;
	}

	public String getRmain3() {
		return rmain3;
	}

	public void setRmain3(String rmain3) {
		this.rmain3 = rmain3;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
	public Integer getMarked() {
		return marked;
	}

	public void setMarked(Integer marked) {
		this.marked = marked;
	}
	
}
