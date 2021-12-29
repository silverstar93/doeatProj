package db_p;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PaymentDTO {
	Integer pno, rid, memNo, pay, cno, volume, refund;
	String cardNum, cardFirm, valm, valy, cardPw, idenNum, phoneNum;
	Date pubd;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	
	public Integer getRefund() {
		return refund;
	}
	public void setRefund(Integer refund) {
		this.refund = refund;
	}
	public String getPubdStr() {
		return sdf.format(pubd);
	}
	public void setPubdStr(String pubdStr) {
		try {
			this.pubd = sdf.parse(pubdStr);
		} catch (ParseException e) {
			// TODO: handle exception
		}
	}
	public Date getPubd() {
		return pubd;
	}
	public void setPubd(Date pubd) {
		this.pubd = pubd;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public String getCardFirm() {
		return cardFirm;
	}
	public void setCardFirm(String cardFirm) {
		this.cardFirm = cardFirm;
	}
	public String getValm() {
		return valm;
	}
	public void setValm(String valm) {
		this.valm = valm;
	}
	public String getValy() {
		return valy;
	}
	public void setValy(String valy) {
		this.valy = valy;
	}
	public String getCardPw() {
		return cardPw;
	}
	public void setCardPw(String cardPw) {
		this.cardPw = cardPw;
	}
	public String getIdenNum() {
		return idenNum;
	}
	public void setIdenNum(String idenNum) {
		this.idenNum = idenNum;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public Integer getPno() {
		return pno;
	}
	public void setPno(Integer pno) {
		this.pno = pno;
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
	public Integer getPay() {
		return pay;
	}
	public void setPay(Integer pay) {
		this.pay = pay;
	}
	public Integer getCno() {
		return cno;
	}
	public void setCno(Integer cno) {
		this.cno = cno;
	}
	public Integer getVolume() {
		return volume;
	}
	public void setVolume(Integer volume) {
		this.volume = volume;
	}
}
