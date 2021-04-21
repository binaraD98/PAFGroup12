package Model;

public class shoppingCart {
	int cid;
	int pid;
	int uid;
	double unitP;
	int qty;
	
	public shoppingCart() {
		
	}

	public shoppingCart(int cid, int pid, int uid, double unitP, int qty) {
		super();
		this.cid = cid;
		this.pid = pid;
		this.uid = uid;
		this.unitP = unitP;
		this.qty = qty;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public double getUnitP() {
		return unitP;
	}

	public void setUnitP(double unitP) {
		this.unitP = unitP;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
	
	
	

}
