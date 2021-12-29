package db_p;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PayCoupDTO {

	Integer ccode, rid, memNo, cond, price, pno;
	String cmenu,id,rname;
	Date cstart, cend, usedate;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat detail = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	
	public String getCmenu() {
		return cmenu;
	}
	public void setCmenu(String cmenu) {
		this.cmenu = cmenu;
	}
	public Integer getCcode() {
		return ccode;
	}
	public void setCcode(Integer ccode) {
		this.ccode = ccode;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}
	public Integer getCond() {
		return cond;
	}
	public void setCond(Integer cond) {
		this.cond = cond;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getPno() {
		return pno;
	}
	public void setPno(Integer pno) {
		this.pno = pno;
	}
	public Date getCstart() {
		return cstart;
	}
	public void setCstart(Date cstart) {
		this.cstart = cstart;
	}
	public Date getCend() {
		return cend;
	}
	public void setCend(Date cend) {
		this.cend = cend;
	}
	public Date getUsedate() {
		return usedate;
	}
	public void setUsedate(Date usedate) {
		this.usedate = usedate;
	}
	public String getCstartStr() {
		return sdf.format(cstart);
	}
	public void setCstartStr(String cstartStr) {
		try {
			this.cstart = sdf.parse(cstartStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getCendStr() {
		return sdf.format(cend);
	}
	public void setCendStr(String cendStr) {
		try {
			this.cend = sdf.parse(cendStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getUsedateStr() {
		return detail.format(usedate);
	}
	public void setUsedateStr(String usedateStr) {
		try {
			this.usedate = detail.parse(usedateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	
	
	
}
