package Model;

public class Buyer {

	int bid;
	String name;
	String company;
	String address;
	String email;
	String phone;
	String password;
	public Buyer() {
		
	}
	
	public Buyer(int bid,String name , String company ,  String address,String email,String phone , String password) {
		
		this.bid = bid;
		this.name = name;
		this.company = company;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.password = password;
		
	}
	
	

	public void setUid(int bid) {
		this.bid = bid;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public void setCompany(String company) {
		this.company = company;
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

	public void setPassword(String password) {
		this.password = password;
	}

	
	public int getBid() {
		return bid;
	}

	
	public String getName() {
		return name;
	}
	
	public String getCompany() {
		return company;
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

	public String getPassword() {
		return password;
	}

	
	
}

