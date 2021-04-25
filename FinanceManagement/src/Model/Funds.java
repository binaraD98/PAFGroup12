package Model;


public class Funds {
	int fundID;
	int propID;
	double amount;
	String description;
	
	
	
	
	
	public Funds() {
		
	}


	public Funds(int fundID, int propID, double amount, String description) {
		this.fundID = fundID;
		this.propID = propID;
		this.amount = amount;
		this.description = description;
	}


	public int getFundID() {
		return fundID;
	}


	public void setFundID(int fundID) {
		this.fundID = fundID;
	}


	public int getPropID() {
		return propID;
	}


	public void setPropID(int propID) {
		this.propID = propID;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}



		
	
	
}