package db_p;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DealDTO {

	Integer cno, rid,orgprice, cprice, cdiscount, ccon, cvolume, paycnt,sale;
	String cmenu, content, rimg , rname,raddr;
	Date cstart, cend, cpub, pubend, pubstart;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	//return (int)((double)cprice*(1-(double)cdiscount/100));
	
	
	
	public String getContentBr() {
		return content.replaceAll("\n", "<br>");
	}

	public Integer getSale() {
		return sale;
	}

	public void setSale(Integer sale) {
		this.sale = sale;
	}

	public String getRaddr() {
		return raddr;
	}

	public void setRaddr(String raddr) {
		this.raddr = raddr;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public Integer getPaycnt() {
		return paycnt;
	}

	public void setPaycnt(Integer paycnt) {
		this.paycnt = paycnt;
	}

	public Date getPubstart() {
		return pubstart;
	}
	public void setPubstart(Date pubstart) {
		this.pubstart = pubstart;
	}
	public Date getPubend() {
		return pubend;
	}
	public void setPubend(Date pubend) {
		this.pubend = pubend;
	}
	//orgprice: 원가, price: 할인가
	public Integer getOrgprice() {
		return orgprice;
	}
	public void setOrgprice(Integer orgprice) {
		this.orgprice = orgprice;
	}
	public String getRimg() {
		return rimg;
	}
	public void setRimg(String rimg) {
		this.rimg = rimg;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getCno() {
		return cno;
	}
	public void setCno(Integer cno) {
		this.cno = cno;
	}
	public String getCmenu() {
		return cmenu;
	}
	public void setCmenu(String cmenu) {
		this.cmenu = cmenu;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public Integer getCprice() {
		return cprice;
	}
	public void setCprice(Integer cprice) {
		this.cprice = cprice;
	}
	public Integer getCdiscount() {
		return cdiscount;
	}
	public void setCdiscount(Integer cdiscount) {
		this.cdiscount = cdiscount;
	}
	public Integer getCcon() {
		return ccon;
	}
	public void setCcon(Integer ccon) {
		this.ccon = ccon;
	}
	public Integer getCvolume() {
		return cvolume;
	}
	public void setCvolume(Integer cvolume) {
		this.cvolume = cvolume;
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
	public Date getCpub() {
		return cpub;
	}
	public void setCpub(Date cpub) {
		this.cpub = cpub;
	}
	
	public String getCstartStr() {
		return sdf.format(cstart);
	}
	public void setCstartStr(String cstartStr) {
		try {
			this.cstart = sdf.parse(cstartStr);
		} catch (ParseException e) {
			// TODO: handle exception
		}
	}
	public String getCendStr() {
		return sdf.format(cend);
	}
	public void setCendStr(String cendStr) {
		try {
			this.cend = sdf.parse(cendStr);
		} catch (ParseException e) {
			// TODO: handle exception
		}
	}
	public String getCpubStr() {
		return sdf.format(cpub);
	}
	public void setCpubStr(String cpubStr) {
		try {
			this.cpub = sdf.parse(cpubStr);
		} catch (ParseException e) {
			// TODO: handle exception
		}
	}
	public String getPubendStr() {
		return sdf.format(pubend);
	}
	public void setPubendStr(String pubendStr) {
		try {
			this.pubend = sdf.parse(pubendStr);
		} catch (ParseException e) {
			// TODO: handle exception
		}
	}
	public String getPubstartStr() {
		return sdf.format(pubstart);
	}
	public void setPubstartStr(String pubstartStr) {
		try {
			this.pubstart = sdf.parse(pubstartStr);
		} catch (ParseException e) {
			// TODO: handle exception
		}
	}
}
