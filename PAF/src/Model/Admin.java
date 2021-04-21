
package Model;

public class Admin {
	
	int uid;
	String name;
	String company;
	String address;
	String email;
	String phone;
	String password;
	double amount;
	String lisencenum;
	public Admin() {
		
	}
	
	public Admin(int uid,String name , String company ,  String address,String email,String phone , String password,double amount,String lisencenum) {
		
		this.uid = uid;
		this.name = name;
		this.company = company;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.amount = amount;
		this.lisencenum = lisencenum;
		
	}
	
	

	public void setUid(int uid) {
		this.uid = uid;
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
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public void setlisencenum(String lisencenum) {
		this.lisencenum = lisencenum;
	}

	
	public int getUid() {
		return uid;
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
	
	public double getAmount() {
		return amount;
	}
	
	public String getLisencenum() {
		return lisencenum;
	}

	
}
