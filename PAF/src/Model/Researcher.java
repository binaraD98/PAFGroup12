package Model;

public class Researcher {
	
	int uid;
	String password;
	String address;
	String email;
	String phone;
	public Researcher() {
		
	}
	
	public Researcher(int uid, String password,String address,String email,String phone) {
		
		this.uid = uid;
		this.password = password;
		this.address = address;
		this.email = email;
		this.phone = phone;
		
	}
	
	
	public void setUid(int uid) {
		this.uid = uid;
	}

	

	public void setPassword(String password) {
		this.password = password;
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


	
	public int getUid() {
		return uid;
	}

	
	public String getPassword() {
		return password;
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

	
}

