package Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import DBUtil.DBConnection;
import Model.appProposals;

public class propController {

	
	DBConnection dbObj = new DBConnection();
	
	
	//........................View Approved Proposals Function.....................................
	
	
		public String Viewprops() {
			
			String output = "";
			
			appProposals appProp = new appProposals();
			
			try {
				Connection con = dbObj.connect();
				if (con == null) {
					return "Error while connecting to the database for reading.";
				}
				//Table to display the Funds
				output = "<table border=\"1\"><tr><th>Proposal ID</th>"
						+ "<th>Researcher ID</th><th>Title</th>" + "<th>Expected Budget</th>"+"</tr>";
				
				String query = "select * from appproposals";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				while(rs.next()) {
					
					appProp.setPropID(rs.getInt("propID"));
					appProp.setResearcherID(rs.getInt("researcherID"));
					appProp.setTitle(rs.getString("title"));
					appProp.setExpecBudge(rs.getDouble("expecBudge"));

					
					
					output += "<tr><td>" + appProp.getPropID() + "</td>";
					output += "<td>" + appProp.getResearcherID() + "</td>";
					output += "<td>" + appProp.getTitle() + "</td>";
					output += "<td>" + appProp.getExpecBudge() + "</td>";
						
				}
				con.close();
				output += "</table>";
				
			}catch(Exception e){
				output = "Error while reading Details.";
				System.err.println(e.getMessage());
			}
			
			return output;
			
		}
	
		//......................................Search by ID.....................................................................
		
		public String viewPropsById(int propID) {

			String output = "";
			
			appProposals appProp = new appProposals();
			
			try {
				Connection con = dbObj.connect();
				if (con == null) {
					return "Error while connecting to the database for reading.";
				}
				
				output = "<table border=\"1\"><tr><th>Proposal ID</th>"
						+ "<th>Researcher ID</th> "+" <th></th> "+" <th> Title</th>" + "<th>Expected Budget </th></tr>";

				String query = "select * from appproposals where propID ='"+propID+"'  ";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				// iterate through the rows in the result set
				while (rs.next()) {

					appProp.setPropID(rs.getInt("propID"));
					appProp.setResearcherID(rs.getInt("researcherID"));
					appProp.setTitle(rs.getString("title"));
					appProp.setExpecBudge(rs.getDouble("expecBudge"));

					
					output += "<tr><td>" + appProp.getPropID() + "</td>";
					output += "<td>" + appProp.getResearcherID() + "</td>";
					output += "<td>" + appProp.getTitle() + "</td>";
					output += "<td>" + appProp.getExpecBudge() + "</td></tr>";
					
					
				}
				con.close();
				// Complete the html table
				output += "</table>";

			} catch (Exception e) {
				output = "Error while reading the Order Details By Id.";
				System.err.println(e.getMessage());
			}

			return output;
		}
}
