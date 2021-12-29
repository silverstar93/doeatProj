package db_p;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReviewDTO {

	int cno, rid, memNo, tsrate, mdrate, svrate, likes;
	Date regdate, chgdate;
	String context, pic, who, lkchk, rname;
	

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


	public int getcno() {
		return cno;
	}

	public void setcno(int cno) {
		this.cno = cno;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public int getTsrate() {
		return tsrate;
	}

	public void setTsrate(int tsrate) {
		this.tsrate = tsrate;
	}

	public int getMdrate() {
		return mdrate;
	}

	public void setMdrate(int mdrate) {
		this.mdrate = mdrate;
	}

	public int getSvrate() {
		return svrate;
	}

	public void setSvrate(int svrate) {
		this.svrate = svrate;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public Date getChgdate() {
		return chgdate;
	}

	public void setChgdate(Date chgdate) {
		this.chgdate = chgdate;
	}

	public String getContext() {
		return context;
	}
	
	public String getContextBr() {
		return context.replaceAll("\n", "<br>");
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getRegdateStr() {
		return sdf.format(regdate);
	}

	public void setRegdateStr(String regdateStr) {
		try {
			this.regdate = sdf.parse(regdateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getChgdateStr() {
		return sdf.format(chgdate);
	}

	public void setChgdateStr(String chgdateStr) {
		try {
			this.regdate = sdf.parse(chgdateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getWho() {
		return who;
	}

	public void setWho(String who) {
		this.who = who;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

//	public String getLkchk() {
//		return lkchk;
//	}
//
//	public void setLkchk(String lkchk) {
//		this.lkchk = lkchk;
//	}	
	
}
