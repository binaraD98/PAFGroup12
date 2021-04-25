package Model;

public class appProposals {
	int propID;
	int researcherID;
	String title;
	Double expecBudge;
	
	

	public appProposals() {
		
	}
	
	
	
	
	
	public appProposals(int propID, int researcherID, String title, Double expecBudge) {
		super();
		this.propID = propID;
		this.researcherID = researcherID;
		this.title = title;
		this.expecBudge = expecBudge;
	}





	public int getPropID() {
		return propID;
	}





	public void setPropID(int propID) {
		this.propID = propID;
	}





	public int getResearcherID() {
		return researcherID;
	}





	public void setResearcherID(int researcherID) {
		this.researcherID = researcherID;
	}





	public String getTitle() {
		return title;
	}





	public void setTitle(String title) {
		this.title = title;
	}





	public Double getExpecBudge() {
		return expecBudge;
	}





	public void setExpecBudge(Double expecBudge) {
		this.expecBudge = expecBudge;
	}







}
	
	


