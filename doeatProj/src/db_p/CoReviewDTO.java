package db_p;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CoReviewDTO {

	int conum, rid, cno, memNo; // 신고번호, 식당아이디, 
	String crs, crsdt, chd, rname; //신고사유, 처리내용
	Date reqdate, prcdate;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public int getConum() {
		return conum;
	}

	public void setConum(int conum) {
		this.conum = conum;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public String getCrs() {
		return crs;
	}

	public void setCrs(String crs) {
		this.crs = crs;
	}

	public String getChd() {
		return chd;
	}

	public void setChd(String chd) {
		this.chd = chd;
	}

	public Date getReqdate() {
		return reqdate;
	}

	public void setReqdate(Date reqdate) {
		this.reqdate = reqdate;
	}

	public Date getPrcdate() {
		return prcdate;
	}

	public void setPrcdate(Date prcdate) {
		this.prcdate = prcdate;
	}
	public String getPrcdateStr() {
		return sdf.format(prcdate);
	}

	public void setPrcdateStr(String prcdateStr) {
		try {
			this.prcdate = sdf.parse(prcdateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getReqdateStr() {
		return sdf.format(reqdate);
	}

	public void setReqdateStr(String reqdateStr) {
		try {
			this.reqdate = sdf.parse(reqdateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getCrsdt() {
		return crsdt;
	}

	public void setCrsdt(String crsdt) {
		this.crsdt = crsdt;
	}
	
	public String getCrsdtSt() {
		return crsdt.replaceAll("\n", "<br>");
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}
	
}
