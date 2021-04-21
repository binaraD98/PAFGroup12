package Model;

public class Order {
	int oid;
	int cid;
	int uid;
	String name;
	String address;
	String email;
	String phone;
	double total;
	
	public Order() {
		
	}
	
	public Order(int oid, int cid, int uid, String name, String address, String email, String phone, double total) {
		
		this.oid = oid;
		this.cid = cid;
		this.uid = uid;
		
		this.name = name;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.total = total;
	}
	
	

	public void setOid(int oid) {
		this.oid = oid;
	}
	
	public void setCid(int cid) {
		this.cid = cid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getOid() {
		return oid;
	}
	
	public int getCid() {
		return cid;
	}
	
	public int getUid() {
		return uid;
	}
	
	
	
	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public double getTotal() {
		return total;
	}
	
	
	

}