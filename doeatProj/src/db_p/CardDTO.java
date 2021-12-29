package db_p;

public class CardDTO {
	
	Integer mem, rid ;

	public Integer getMem() {
		return mem;
	}

	public void setMem(Integer mem) {
		this.mem = mem;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	@Override
	public String toString() {
		return "CardDTO [mem=" + mem + ", rid=" + rid + "]";
	}
	
	
	

}
